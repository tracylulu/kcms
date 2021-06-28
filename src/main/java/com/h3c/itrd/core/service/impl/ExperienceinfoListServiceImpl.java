package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ExperienceinfoListDO;
import com.h3c.itrd.core.mapper.ExperienceinfoListMapper;
import com.h3c.itrd.core.service.ExperienceinfoListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 经验案例信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class ExperienceinfoListServiceImpl extends ServiceImpl<ExperienceinfoListMapper, ExperienceinfoListDO> implements ExperienceinfoListService {
	@Autowired
    private ExperienceinfoListMapper experienceinfoListMapper;
	
    @Override
    public List<ExperienceinfoListDO> getExperienceinfoList(String account, String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("CaseAuthor",account);
        queryWrapper.between("ApplicatEndTime",start,end);
        queryWrapper.select("CaseName","CaseValueEvaluation","DcMainCategory","DcSubCategory","DocumentCode"
                ,"ApplicatEndTime","SummaryAndkeyword");
        return experienceinfoListMapper.selectList(queryWrapper);
    }
    
    @Override
    @Transactional
    public void syncExperienceinfo(Elements elements) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		List<ExperienceinfoListDO> experienceInfoList = new ArrayList<ExperienceinfoListDO>();
		for (Element element : elements) {
			ExperienceinfoListDO experienceInfo = new ExperienceinfoListDO();
			Elements rows=element.select("content").select("m|properties");
			String authName = rows.select("d|Applicant").text();
			if(authName.contains("i:0#.w|h3c"))
			{
				authName = authName.substring(authName.indexOf("\\")+1, authName.indexOf("^"));
			}
			experienceInfo.setDocumentCode(rows.select("d|DocumentCode").text());
			experienceInfo.setCaseName(rows.select("d|CaseName").text());
			experienceInfo.setCaseAuthor(authName);
			experienceInfo.setChineseName(rows.select("d|ChineseName").text());
			String applicatEndTimeString = rows.select("d|ApplicatEndTime").text();
			String applicatTimeString = rows.select("d|ApplicatTime").text();
			Date applicatEndTime =null;
			Date applicatTime =null;
			try {
				applicatEndTime = sdf.parse(applicatEndTimeString);
				applicatTime = sdf.parse(applicatTimeString);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			experienceInfo.setApplicatEndTime(applicatEndTime);
			experienceInfo.setDepartmentLevel1(rows.select("d|DepartmentLevel1").text());
			experienceInfo.setDepartmentLevel2(rows.select("d|DepartmentLevel2").text());
			experienceInfo.setDepartmentLevel3(rows.select("d|DepartmentLevel3").text());
			experienceInfo.setCaseValueEvaluation(rows.select("d|CaseValueEvaluation").text());
			experienceInfo.setApplicatTime(applicatTime);
			experienceInfo.setSummaryAndkeyword(rows.select("d|SummaryAndkeyword").text());
			experienceInfo.setDcMainCategory(rows.select("d|DcMainCategory").text());
			experienceInfo.setDcSubCategory(rows.select("d|DcSubCategory").text());
			experienceInfo.setSyncTime(new Date());
			experienceInfoList.add(experienceInfo);
		}
    	experienceinfoListMapper.batchUpdateExperienceInfo(experienceInfoList);
    	experienceinfoListMapper.batchInsertExperienceInfo(experienceInfoList);
    }

	@Override
	public List<ExperienceinfoListDO> getListForPlatform(String start, String end) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("ApplicatEndTime",start,end);
        return experienceinfoListMapper.selectList(queryWrapper);
	}
}
