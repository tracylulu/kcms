package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.DptconfigListDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.IdmsinfoListDO;
import com.h3c.itrd.core.mapper.IdmsinfoListMapper;
import com.h3c.itrd.core.service.DptconfigListService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.IdmsinfoListService;
import com.h3c.itrd.core.vo.IdmsResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 问题单备份表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class IdmsinfoListServiceImpl extends ServiceImpl<IdmsinfoListMapper, IdmsinfoListDO> implements IdmsinfoListService {
	@Autowired
    private IdmsinfoListMapper idmsinfoListMapper;
	@Autowired
    private EmployeeListService employeeListService;
	@Autowired
    private DptconfigListService dptconfigListService;


    @Override
    public List<IdmsinfoListDO> getList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (param.get("start") != null && param.get("end") != null) {
            queryWrapper.between("DefectModifiedTime",param.get("start").toString(),param.get("end").toString());
        }
        if (param.get("currentNode") != null) {
            queryWrapper.in("CurrentNode","D版本归档","E组织测试","F回归测试","G问题确认","0关闭");
        }
        return idmsinfoListMapper.selectList(queryWrapper);
    }

	@Override
	public List<IdmsinfoListDO> getListForModelV() {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.select("DefectNO","ProjectID","ODC_Severity","SubmitBy","Name_B","Name_C","ProjectSerialNum");
		return idmsinfoListMapper.selectList(queryWrapper);
	}
	@Transactional
	public void syncDefectInfo(List<Map<String, Object>> defectDetailList) {
		String prefix = "http://idms.h3c.com/";
		List<IdmsinfoListDO> idmsinfoList = new ArrayList<IdmsinfoListDO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			for (Map<String, Object> defectDetail : defectDetailList) {
				String defectType = (String) defectDetail.get("DefectType");
				String fixer = (String) defectDetail.get("Name_B");
				String reviewer = (String) defectDetail.get("Name_C");
				String dptName = "";
				String dptCode = "";
				String bug_syncprefix = "";
				String bug_asyncprefix = "";
				String idmsType = "普通单";
				String batchCopyFromNo = (String) defectDetail.get("BatchCopyFromNo");
				String defectUrl = (String) defectDetail.get("DefectUrl");
				String defectNO = (String) defectDetail.get("DefectNO");
				String summery = (String) defectDetail.get("Summery");
				summery = summery==null?"":summery;
				String projectID = (String) defectDetail.get("ProjectID");
				String reportQuality = (String) defectDetail.get("ReportQuality");
				String currentPerson = (String) defectDetail.get("CurrentPerson");
				String currentNode = (String) defectDetail.get("CurrentNode");
				String product = (String) defectDetail.get("PRODUCT");
				String release1 = (String) defectDetail.get("RELEASE");
				String baseline = (String) defectDetail.get("BASELINE");
				String baseline1 = (String) defectDetail.get("BASELINE_1");
				String team = (String) defectDetail.get("TEAM");
				String odcAge = (String) defectDetail.get("ODC_Age");
				String submitDateString = (String) defectDetail.get("SubmitDate");
				Date submitDate = submitDateString==null?null:sdf.parse(submitDateString);
				String submitBy = (String) defectDetail.get("SubmitBy");
				String odcSeverity = (String) defectDetail.get("ODC_Severity");
				odcSeverity = odcSeverity==null?"":odcSeverity;
				String locationer = (String) defectDetail.get("Locationer");
				String defectModifiedTimeString = (String) defectDetail.get("DefectModifiedTime");
				Date defectModifiedTime = defectModifiedTimeString==null?null:sdf.parse(defectModifiedTimeString);
				String modifyAuthorizedBy = (String) defectDetail.get("ModifyAuthorizedBy");
				String archiveBy = (String) defectDetail.get("ArchiveBy");
				String syNo = (String) defectDetail.get("SyNo");
				String cmoArchiveTimeString = (String) defectDetail.get("CMOArchiveTime");
				Date cmoArchiveTime = cmoArchiveTimeString==null? null:sdf.parse(cmoArchiveTimeString);
				String testArrangedTimeString = (String) defectDetail.get("TestArrangedTime");
				Date testArrangedTime = testArrangedTimeString==null?null:sdf.parse(testArrangedTimeString);
				String testBy = (String) defectDetail.get("TestBy");
				String testTimeString = (String) defectDetail.get("TestTime");
				Date testTime = testTimeString==null?null:sdf.parse(testTimeString);
				Integer testNotPass = (Integer) defectDetail.get("TestNotPass");
				String testApprover = (String) defectDetail.get("TestApprover");
				String tmApproveTimeString = (String) defectDetail.get("TMApproveTime");
				Date tmApproveTime = tmApproveTimeString==null?null:sdf.parse(tmApproveTimeString);
				if (defectType == null || !"硬件问题单".equals(defectType)) {
					EmployeeListDO employeeByfixer = new EmployeeListDO();
					employeeByfixer = employeeListService.getEmployeeByAccount(fixer);
					if (employeeByfixer == null) {
						EmployeeListDO employeeByReviewer = new EmployeeListDO();
						employeeByReviewer = employeeListService.getEmployeeByAccount(reviewer);
						if (employeeByReviewer != null) {
							dptName = employeeByReviewer.getSeconddpt();
							dptCode = employeeByReviewer.getSeconddptcode();
						}
					} else {
						dptName = employeeByfixer.getSeconddpt();
						dptCode = employeeByfixer.getSeconddptcode();
					}
					if (batchCopyFromNo != null && "".equals(batchCopyFromNo)) {
						idmsType = "同步单";
					} else {
						DptconfigListDO dptconfig = new DptconfigListDO();
						dptconfig = dptconfigListService.getDptconfigListByDptcode(dptCode);
						if (dptconfig != null) {
							bug_syncprefix = dptconfig.getBugSyncprefix();
							bug_asyncprefix = dptconfig.getBugAsyncprefix();
							if (!"".equals(bug_syncprefix)) {
								if (checkSpecifyContent(summery, bug_syncprefix)) {
									if (!"".equals(bug_asyncprefix)) {
										if (checkSpecifyContent(summery, bug_asyncprefix)) {
											idmsType = "普通单";
										} else {
											idmsType = "同步单";
										}
									} else {
										idmsType = "同步单";
									}
								}
							}
							
						}
					}
				}
				defectUrl = prefix + defectUrl;
				fixer = fixer==null?"":fixer;
				reviewer = reviewer==null?"":reviewer;
				baseline = baseline==null?"":baseline;
				baseline1 = baseline1==null?"":baseline1;
				locationer = locationer==null?"":locationer;
				submitBy = submitBy==null?"":submitBy;
				IdmsinfoListDO idmsinfo = new IdmsinfoListDO();
				idmsinfo.setDefectNO(defectNO);
				idmsinfo.setSummery(summery);
				idmsinfo.setProjectID(projectID);
				idmsinfo.setDefectType(defectType);
				idmsinfo.setReportQuality(reportQuality);
				idmsinfo.setCurrentPerson(currentPerson);
				idmsinfo.setCurrentNode(currentNode);
				idmsinfo.setPlipmt(dptName);
				idmsinfo.setProduct(product);
				idmsinfo.setRelease1(release1);
				idmsinfo.setBaseline(baseline);
				idmsinfo.setBaseline1(baseline1);
				idmsinfo.setTeam(team);
				idmsinfo.setOdcAge(odcAge);
				idmsinfo.setSubmitDate(submitDate);
				idmsinfo.setSubmitBy(submitBy);
				idmsinfo.setOdcSeverity(odcSeverity);
				idmsinfo.setLocationer(locationer);
				idmsinfo.setNameB(fixer);
				idmsinfo.setDefectModifiedTime(defectModifiedTime);
				idmsinfo.setNameC(reviewer);
				idmsinfo.setModifyAuthorizedBy(modifyAuthorizedBy);
				idmsinfo.setArchiveBy(archiveBy);
				idmsinfo.setBatchCopyFromNo(batchCopyFromNo);
				idmsinfo.setSyNo(syNo);
				idmsinfo.setCMOArchiveTime(cmoArchiveTime);
				idmsinfo.setTestArrangedTime(testArrangedTime);
				idmsinfo.setTestBy(testBy);
				idmsinfo.setTestTime(testTime);
				idmsinfo.setTestNotPass(testNotPass);
				idmsinfo.setTestApprover(testApprover);
				idmsinfo.setTMApproveTime(tmApproveTime);
				idmsinfo.setDefectUrl(defectUrl);
				idmsinfo.setIdmsType(idmsType);
				idmsinfo.setSyncTime(new Date());
				idmsinfoList.add(idmsinfo);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		batchInsertDefectInfo(idmsinfoList);
		batchUpdateDefectInfo(idmsinfoList);
	}
    @Override
    public void batchInsertDefectInfo(List<IdmsinfoListDO>idmsinfoList) {
    	idmsinfoListMapper.batchInsertDefectInfo(idmsinfoList);
    }
    @Override
    public void batchUpdateDefectInfo(List<IdmsinfoListDO>idmsinfoList) {
    	idmsinfoListMapper.batchUpdateDefectInfo(idmsinfoList);
    }
    
    public Boolean checkSpecifyContent(String sourceDara,String targetData) {
    	String[] dataArray =targetData.split(",");
    	for (String data : dataArray) {
    		int index=sourceDara.indexOf(data.trim());
    		if (index>-1) {
    			return true;
    		}
		}
    	return false;
    }

	@Override
	public List<IdmsResVO> getIdmsPT1(Map<String, Object> param) {
		return idmsinfoListMapper.getIdmsPT1(param);
	}

	@Override
	public List<IdmsResVO> getIdmsPT2(Map<String, Object> param) {
		return idmsinfoListMapper.getIdmsPT2(param);
	}

	@Override
	public List<IdmsResVO> getIdmsPT3(Map<String, Object> param) {
		return idmsinfoListMapper.getIdmsPT3(param);
	}

	@Override
	public List<IdmsResVO> getIdmsPT4(Map<String, Object> param) {
		return idmsinfoListMapper.getIdmsPT4(param);
	}

	@Override
	public List<IdmsResVO> getIdmsTB1(Map<String, Object> param) {
		return idmsinfoListMapper.getIdmsTB1(param);
	}

	@Override
	public List<IdmsResVO> getIdmsTB2(Map<String, Object> param) {
		return idmsinfoListMapper.getIdmsTB2(param);
	}
}
