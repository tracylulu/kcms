package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ExperienceinfoListDO;
import com.h3c.itrd.core.entity.SharedocinfoListDO;
import com.h3c.itrd.core.mapper.SharedocinfoListMapper;
import com.h3c.itrd.core.service.SharedocinfoListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 共享技术信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class SharedocinfoListServiceImpl extends ServiceImpl<SharedocinfoListMapper, SharedocinfoListDO> implements SharedocinfoListService {
	@Autowired
    private SharedocinfoListMapper sharedocinfoListMapper;
    @Override
    public List<SharedocinfoListDO> getSharedocinfoList(String account, String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("Applicant",account);
        queryWrapper.between("LastModifyDate",start,end);
        queryWrapper.select("DocumentMainType","EvaluationLevel","MainBusiness","SubBusiness","DocumentCode","LastModifyDate",
                "DocumentSubType","TechContributionScore");
        return sharedocinfoListMapper.selectList(queryWrapper);
    }
    
    @Override
    @Transactional
    public void syncShareDocInfo(Elements elements) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		List<SharedocinfoListDO> shareDocInfoList = new ArrayList<SharedocinfoListDO>();
		for (Element element : elements) {
			SharedocinfoListDO shareDocInfo = new SharedocinfoListDO();
			Elements rows=element.select("content").select("m|properties");
			String authName = rows.select("d|Applicant").text();
			if(authName.contains("CN="))
			{
				String rgex="^CN=(.*?)/O=h3c";
				Pattern pattern = Pattern.compile(rgex);
				Matcher m = pattern.matcher(authName);
				while(m.find()) {
					authName =m.group(1);
				}
			}
			shareDocInfo.setDocumentCode(rows.select("d|DocumentCode").text());
			shareDocInfo.setDocumentMainType(rows.select("d|DocumentMainType").text());
			shareDocInfo.setApplicant(authName);
			String lastModifyDateString = rows.select("d|LastModifyDate").text();
			Date lastModifyDate =null;
			try {
				lastModifyDate = sdf.parse(lastModifyDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			shareDocInfo.setLastModifyDate(lastModifyDate);
			shareDocInfo.setDepartmentLevel1(rows.select("d|DepartmentLevel1").text());
			shareDocInfo.setDepartmentLevel2(rows.select("d|DepartmentLevel2").text());
			shareDocInfo.setDepartmentLevel3(rows.select("d|DepartmentLevel3").text());
			shareDocInfo.setEvaluationLevel(rows.select("d|EvaluationLevel").text());
			String techContributionScore = rows.select("d|TechContributionScore").text();
			shareDocInfo.setTechContributionScore("".equals(techContributionScore)?new BigDecimal(0):new BigDecimal(techContributionScore));
			shareDocInfo.setMainBusiness(rows.select("d|MainBusiness").text());
			shareDocInfo.setSubBusiness(rows.select("d|SubBusiness").text());
			shareDocInfo.setDocumentSubType(rows.select("d|DocumentSubType").text());			
			shareDocInfo.setSyncTime(new Date());
			shareDocInfoList.add(shareDocInfo);
		}
		sharedocinfoListMapper.batchUpdateShareDocInfo(shareDocInfoList);
		sharedocinfoListMapper.batchInsertShareDocInfo(shareDocInfoList);
    }

	@Override
	public List<SharedocinfoListDO> getListForPlatform(String start, String end) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("LastModifyDate",start,end);
        return sharedocinfoListMapper.selectList(queryWrapper);
	}
}
