package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.common.consts.SystemConstant;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.controller.SoftwareController;
import com.h3c.itrd.core.entity.AnalyzeinfoListDO;
import com.h3c.itrd.core.entity.CbbinfoListDO;
import com.h3c.itrd.core.entity.DptconfigListDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.ExperienceinfoListDO;
import com.h3c.itrd.core.entity.IdmEmployeeDO;
import com.h3c.itrd.core.entity.IdmsinfoListDO;
import com.h3c.itrd.core.entity.IsoftContributionListDO;
import com.h3c.itrd.core.entity.IsoftProjectCloudListDO;
import com.h3c.itrd.core.entity.IsoftProjectListDO;
import com.h3c.itrd.core.entity.IsoftWorkscheduleDO;
import com.h3c.itrd.core.entity.PatentinfoListDO;
import com.h3c.itrd.core.entity.PlatformSummaryDO;
import com.h3c.itrd.core.entity.ProjectAcceptanceProblemsListDO;
import com.h3c.itrd.core.entity.ProjectProblemsDiDO;
import com.h3c.itrd.core.entity.ProjectQuarterWorkListDO;
import com.h3c.itrd.core.entity.ProjectSuDO;
import com.h3c.itrd.core.entity.ProjectSuVDO;
import com.h3c.itrd.core.entity.ProjectSummaryDO;
import com.h3c.itrd.core.entity.QuarterScoreDO;
import com.h3c.itrd.core.entity.ReviewSummaryDO;
import com.h3c.itrd.core.entity.ReviewinfoCloudListDO;
import com.h3c.itrd.core.entity.ReviewinfoListDO;
import com.h3c.itrd.core.entity.SharedocinfoListDO;
import com.h3c.itrd.core.entity.SpecinfoListDO;
import com.h3c.itrd.core.entity.YnfxinfoListDO;
import com.h3c.itrd.core.mapper.AnalyzeinfoListMapper;
import com.h3c.itrd.core.service.AddScoreService;
import com.h3c.itrd.core.service.AnalyzeinfoListService;
import com.h3c.itrd.core.service.CbbinfoListService;
import com.h3c.itrd.core.service.DptconfigListService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.ExperienceinfoListService;
import com.h3c.itrd.core.service.IdmEmployeeService;
import com.h3c.itrd.core.service.IdmsinfoListService;
import com.h3c.itrd.core.service.IsoftContributionListService;
import com.h3c.itrd.core.service.IsoftProjectCloudListService;
import com.h3c.itrd.core.service.IsoftProjectListService;
import com.h3c.itrd.core.service.IsoftWorkscheduleService;
import com.h3c.itrd.core.service.NoticeListService;
import com.h3c.itrd.core.service.PatentinfoListService;
import com.h3c.itrd.core.service.PlatformSummaryService;
import com.h3c.itrd.core.service.ProjectAcceptanceProblemsListService;
import com.h3c.itrd.core.service.ProjectProblemsDiService;
import com.h3c.itrd.core.service.ProjectQuarterWorkListService;
import com.h3c.itrd.core.service.ProjectSuService;
import com.h3c.itrd.core.service.ProjectSuVService;
import com.h3c.itrd.core.service.ProjectSummaryService;
import com.h3c.itrd.core.service.QuarterScoreService;
import com.h3c.itrd.core.service.ReviewSummaryService;
import com.h3c.itrd.core.service.ReviewinfoCloudListService;
import com.h3c.itrd.core.service.ReviewinfoListService;
import com.h3c.itrd.core.service.SharedocinfoListService;
import com.h3c.itrd.core.service.SoftwareListService;
import com.h3c.itrd.core.service.SpecinfoListService;
import com.h3c.itrd.core.service.YnfxinfoListService;
import com.h3c.itrd.core.vo.AddScoreResVO;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.DateUtil;
import com.h3c.itrd.util.SystemCommonUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 软件开发定时汇算任务汇总类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-31
 */
@Service
public class SoftwareListServiceImpl implements SoftwareListService {
	
	private static Logger logger = LoggerFactory.getLogger(SoftwareListServiceImpl.class);
    @Autowired
    private EmployeeListService employeeListService;
    @Autowired
    private QuarterScoreService quarterScoreService;
    @Autowired
    private ProjectSummaryService projectSummaryService;
    @Autowired
    private IsoftProjectCloudListService isoftProjectCloudListService;
    @Autowired
    private NoticeListService noticeListService;
    @Autowired
    private ReviewSummaryService reviewSummaryService;
    @Autowired
    private DptconfigListService dptconfigListService;
    @Autowired
    private ReviewinfoListService reviewinfoListService;
    @Autowired
    private ReviewinfoCloudListService reviewinfoCloudListService;
    @Autowired
    private IdmEmployeeService idmEmployeeService;
    @Autowired
    private IdmsinfoListService idmsinfoListService;
    @Autowired
    private AddScoreService addScoreService;
    @Autowired
    private PlatformSummaryService platformSummaryService;
    @Autowired
    private ProjectSuService projectSuService;
    @Autowired
    private IsoftContributionListService isoftContributionListService;
    @Autowired
    private IsoftWorkscheduleService isoftWorkscheduleService;
    @Autowired
    private IsoftProjectListService isoftProjectListService;
    @Autowired
    private ProjectQuarterWorkListService projectQuarterWorkListService;
    @Autowired
    private ProjectAcceptanceProblemsListService projectAcceptanceProblemsListService;
    @Autowired
    private ProjectSuVService projectSuVService;
    @Autowired
    private ProjectProblemsDiService projectProblemsDiService;
    @Autowired
    private CbbinfoListService cbbinfoListService;
    @Autowired
    private ExperienceinfoListService experienceinfoListService;
    @Autowired
    private PatentinfoListService patentinfoListService;
    @Autowired
    private SharedocinfoListService sharedocinfoListService;
    @Autowired
    private SpecinfoListService specinfoListService;
    @Autowired
    private YnfxinfoListService ynfxinfoListService;
    
	@Override
	public Boolean devSu(String quarter) throws Exception {
		//String quarter = DateTimeUtils.getQuarter();
		String a1 = quarter.substring(0, quarter.indexOf("Q"));
    	String a2 = quarter.substring(a1.length(), quarter.length());
    	//贡献度查询需要传这种形式：2021-Q1
    	String quarter1 =a1+"-"+a2;
    	//String quarter="2021Q1";
    	//删除历史数据的代码已经置后了。(置后有问题)(先备份表数据)
    	//把定时计算软件开发得分的删除逻辑移到汇算成功后再删除，以免删除数据了但是汇算报异常了没有数据。
    	int row = projectSuService.deleteByQuarter(quarter);
    	//删除可能存在的，下个季度的项目数据
    	String nextQuarter=this.getNextQuarter(quarter);
    	int row1 = projectSuService.deleteByQuarter(nextQuarter);
    	int row2 = projectSummaryService.deleteByQuarterAndTypeForV(quarter, SystemConstant.TYPE_NORMAL,SystemConstant.TYPE_NOT_V);
    	
    	//获取软件开发config信息  map(dptcode,DptconfigListDO)形式
    	List<DptconfigListDO> dptconfigList = dptconfigListService.getList(new HashMap<>());
        Map<String, DptconfigListDO> dptconfigMap = dptconfigList.stream().filter(t->StringUtils.isNotBlank(t.getDptcode())).
                collect(Collectors.toMap(DptconfigListDO::getDptcode, dptconfigListDO -> dptconfigListDO));
        //获取贡献度  map(projectId,List(IsoftContributionListDO))形式
        List<IsoftContributionListDO> contributionList = isoftContributionListService.getContributionListByQuarter(quarter1);
        Map<String, List<IsoftContributionListDO>> contributionMap = contributionList.stream().filter(t->StringUtils.isNoneBlank(t.getProjectId())).
        	//collect(Collectors.toMap(IsoftContributionListDO::getProjectId, isoftContributionListDO -> isoftContributionListDO));
        	collect(Collectors.groupingBy(IsoftContributionListDO::getProjectId));
        //查询历史天数与分数  map(projectId,List(ProjectSuDO))形式
        List<ProjectSuDO> projectSulist = projectSuService.getList();
        Map<String, List<ProjectSuDO>> projectSuMap = projectSulist.stream().filter(t->StringUtils.isNoneBlank(t.getProjectId())).
            	//collect(Collectors.toMap(ProjectSuDO::getProjectId, projectSuDO -> projectSuDO));
        		collect(Collectors.groupingBy(ProjectSuDO::getProjectId));
    	
        //初始化循环的list
        List<ProjectSuDO> projectSu=new ArrayList<>();
        List<ProjectSummaryDO> projectSummary=new ArrayList<>();
        //以5000条数据为一组
        int isoftProjectMax = 5000;
        //ProType为2表示V模型项目,不为2表示其他项目
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("ProTypeNot2", "2");
        List<IsoftProjectListDO> isoftProjectList=isoftProjectListService.getIsoftListByParam(paramMap);
        List<List<IsoftProjectListDO>> splitList1 = SystemCommonUtil.splitList(isoftProjectList, isoftProjectMax);
        for (int i = 0; i <splitList1.size() ; i++) {
            List<IsoftProjectListDO> limitList = splitList1.get(i);
            for (int j = 0; j < limitList.size(); j++) {
            	//if(limitList.get(j).getProjectId().equals("1BDB570D-FABA-4F6D-AE78-68E820629BDD")) {
            	
            	String projectId=limitList.get(j).getProjectId();
            	String seconddptcode=limitList.get(j).getSeconddptcode();
            	//wangjunze折算规模那块的新逻辑不加这个条件报错，因为数据的问题
            	if(limitList.get(j).getFactNewScale().doubleValue()==0 && limitList.get(j).getFactMoveScale().doubleValue()==0
            		&& limitList.get(j).getPlanNewScale().doubleValue()==0 && limitList.get(j).getPlanMoveScale().doubleValue()==0) {
            		continue;
            	}
            	//因为上游数据Seconddptcode传过来可能为空，litongxin说这个人在employee_list查不到，那这个人不存在，这个单子过滤掉
            	if(StringUtils.isBlank(limitList.get(j).getSeconddptcode())) {
            		continue;
            	}
            	List<IsoftContributionListDO> kx=contributionMap.get(projectId);
            	//因为上游数据贡献度表要是没有这个项目的话直接过滤掉
            	if(kx==null) {
            		continue;
            	}
            	List<ProjectSuDO> su=projectSuMap.get(projectId);
            	DptconfigListDO config = dptconfigMap.get(seconddptcode);
                if(config==null){
                	config = dptconfigMap.get("50041348");
                }
            	Map<String, List> dev = this.dev(limitList.get(j),kx,config,quarter,su);
            	projectSu.addAll(dev.get("projectSulist"));
            	projectSummary.addAll(dev.get("projectSummaryList"));
            }
        //}
        }
        boolean a = false;
        boolean b = false;
        //分批往project_su表中增加数据
        if (projectSu.size() > 1000) {
            //按每max个一组分割
            int insertMax = 1000;
            List<List<ProjectSuDO>> splitList = SystemCommonUtil.splitList(projectSu, insertMax);
            for (int i = 0; i < splitList.size(); i++) {
                a = projectSuService.saveBatch(splitList.get(i));
            }
        }else{
            a = projectSuService.saveBatch(projectSu);
        }
        //分批往project_summary表中增加数据
        if (projectSummary.size() > 1000) {
            //按每max个一组分割
            int insertMax = 1000;
            List<List<ProjectSummaryDO>> splitList = SystemCommonUtil.splitList(projectSummary, insertMax);
            for (int i = 0; i < splitList.size(); i++) {
                b = projectSummaryService.saveBatch(splitList.get(i));
            }
        }else{
            b = projectSummaryService.saveBatch(projectSummary);
        }
        if(a && b){
            return true;
        }else{
            return false;
        }
	}

	private Map<String,List> dev(IsoftProjectListDO v, List<IsoftContributionListDO> kx, DptconfigListDO config,
			String quarter, List<ProjectSuDO> su) throws Exception {
		List<ProjectSuDO> projectSulist=new ArrayList<>();
    	List<ProjectSummaryDO> projectSummaryList=new ArrayList<>();
    	//处理脏数据
    	String endTime="";
    	//litongxin反馈这个字段给个默认值为0001-01-01 00:00:00 ，但是查询的时候会报错，统一成1900-01-01 00:00:000 这个值
    	if(DateUtil.getDateStrForDefault(v.getEndTime()).equals("1900-01-01 00:00:00")) {
    		endTime="0000-00-00 00:00:00";
    	}else {
    		endTime=DateUtil.getDateStrForDefault(v.getEndTime());
    	}
    	//代码量折算 综合代码量
    	double difficult=0;
    	if(v.getDifficult().doubleValue()<=0 || v.getDifficult().doubleValue()>10) {
    		difficult=1;
    	}else {
    		difficult=v.getDifficult().doubleValue();
    	}
    	//modify start by chenlulu on 20210619
    	double newScaleA=0;
    	double moveScaleB=0;
    	if(v.getFactNewScale().doubleValue()>0 || v.getFactMoveScale().doubleValue()>0) {
    		newScaleA=v.getFactNewScale().doubleValue();
    		moveScaleB=v.getFactMoveScale().doubleValue();		
    	}else {
    		newScaleA=v.getPlanNewScale().doubleValue();
    		moveScaleB=v.getPlanMoveScale().doubleValue();
    	}
    	double scaleC=0;
    	scaleC = this.getdifficultByScale(moveScaleB);
    	if(StringUtils.isNotBlank(v.getPCBType()) && v.getPCBType().contains("移植")) {
        	difficult = (newScaleA+scaleC) / (newScaleA+moveScaleB);
    	}else if(((newScaleA+scaleC) / (newScaleA+moveScaleB))<difficult*0.9){
    		scaleC = this.getdifficultByScale(moveScaleB)*difficult;
    		difficult = (newScaleA*difficult+scaleC) / (newScaleA*difficult+moveScaleB*difficult);
    	}else {
    	}
    	if(StringUtils.isNotBlank(v.getPCBType()) && v.getPCBType().contains("简单") && difficult>0.3) {
    		difficult=Math.pow(1.7,(0.35-difficult))*difficult;
    	}
    	
    	//基础规模的变化，原基础规模是个获取值   假设等于GS：项目得分的基础规模
        //现获取后需要一个小折算  实际用作计算的基础规模为BS
    	double prjBasecode = config.getPrjBasecode().doubleValue();
    	double basecode=0;
        if((newScaleA+moveScaleB)<0.75) {
        	basecode=prjBasecode*(newScaleA+moveScaleB)+0.25*prjBasecode;
        }else {
        	basecode=prjBasecode;
        }
    	
    	//折算后的规模
        double actualScale=0;
        if(v.getActualScale().doubleValue()>0) {
        	actualScale=v.getActualScale().doubleValue()*difficult;
        }else {
        	actualScale=v.getPlanScale().doubleValue()*difficult;
        }
//    	//项目类型为移植项目（包含分支内移植和跨分支移植）时，项目规模会做折算
//        if(StringUtils.isNotBlank(v.getPCBType())) {
//	        if(v.getPCBType().contains("移植")) {
//	        	if (actualScale <= 1) {
//	        		difficult=0.36;
//	            } else if (actualScale <= 2) {
//	            	difficult=((0.36 * 1) + (0.25 * (actualScale - 1))) / actualScale;
//	            } else if (actualScale <= 4) {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * (actualScale - 2))) / actualScale;
//	            } else if (actualScale <= 8) {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * (actualScale - 4))) / actualScale;
//	            } else if (actualScale <= 20) {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * 4) + (0.1 * (actualScale - 8))) / actualScale;
//	            } else {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * 4) + (0.1 * 12) + (0.07 * (actualScale - 20))) / actualScale;
//	            }
//	        }
//	    }
        //没有实际生产率则取计划生产率 (7/17修改，统一按照计划生产率计算)
        double proRate =v.getPlanProRate().doubleValue();
        double manHours =v.getPlanManHours().doubleValue();
        //实际生产率大于200，则对项目规模进行折算；
        if (difficult*proRate > 200){
        	actualScale = (200/difficult)*(manHours/8)/1000;
        	if(StringUtils.isNotBlank(v.getPCBType()) && v.getPCBType().contains("移植")) {
        		actualScale=actualScale*((newScaleA+scaleC) / (newScaleA+moveScaleB));
        	}
        }
        //计算综合代码量CX
        double CX = (actualScale + basecode * difficult);
        //CX=SystemCommonUtil.doubleFormat3(CX);
    	//计算时间交集RX
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String startDateStr=timeSpaceMap.get("start").toString();
        String endDateStr=timeSpaceMap.get("end").toString();
        Date startDate=DateUtil.getDateForDefault(startDateStr);
        Date endDate=DateUtil.getDateForDefault(endDateStr);
        //本季开发比例Rx = 时间交集比例
        //开始时间以“实际启动时间”为准；实际结束时间若没有(没有在当季完成也算没有结束)，则取“计划结束日期”和“考评结束日期”的最大值
        String start=DateUtil.getDateStrForDefault(v.getStartTime());
        String end="";
        int isOver=0;
        int days=0;
        //判断是否在当季结束，是否为未结束项目
        if(endTime.equals("0000-00-00 00:00:00") || (v.getEndTime().getTime()>endDate.getTime())) {
        	end=DateUtil.getDateStrForDefault(v.getPlanEndTime());
        	isOver=0;
        }else {
        	//考评结束日期
        	end=DateUtil.getDateStrForDefault(v.getEndTime());
        	isOver=1;
        }
        String secondRecoverTime=(v.getSecondRecoverTime().getTime()<endDate.getTime())?DateUtil.getDateStrForDefault(v.getSecondRecoverTime()):endDateStr;
        String firstRecoverTime=(v.getFirstRecoverTime().getTime()<endDate.getTime())?DateUtil.getDateStrForDefault(v.getFirstRecoverTime()):endDateStr;
        //查询该项目之前季度工作天数
        String nextQuarter=this.getNextQuarter(quarter);
        //这块会出现projectSu这个表对应的项目没有数据，会报空指针异常。做下容错处理
        if(su==null) {
        	days=0;
        }else {
        	for (int i = 0; i < su.size(); i++) {
        		//由于把删除的条件置后了，所以这块得改下,把当前季度的，下一季度的都去除---删除置前了，这块维持原样
            	//if(!su.get(i).getQuarter().equals(quarter) && !su.get(i).getQuarter().equals(nextQuarter)) {
            		days=days+su.get(i).getDaysD();
            	//}
    		}
        }
        
        Map<String, Double> worktimeMap = this.getWorktime(start,end,quarter,DateUtil.getDateStrForDefault(v.getFirstPauseTime()),firstRecoverTime,
        		DateUtil.getDateStrForDefault(v.getSecondPauseTime()),secondRecoverTime,isOver,days);
        //当季时间交集
        double RX=worktimeMap.get("RX");
        //RX=SystemCommonUtil.doubleFormat3(RX);
        //当季度实际开发天数
        double daysd=worktimeMap.get("daysd");	
        //验收得分A： 100 * 2^(-项目DI密度/15)   项目DI密度= ATDI/实际规模
        //如果没有验收，则取上季平均验收得分。其中DI密度按实际规模（移植类按折算规模）
        double A=0;
        double Asu=0;
        //Atdi这个值，litongxin那边为空的话默认给的是-0.01
        if(endTime.equals("0000-00-00 00:00:00") || v.getAtdi().equals("-99") || v.getAtdi().equals("-0.01") ||
        		StringUtils.isBlank(v.getAtdi()) || v.getActualScale().doubleValue()==0) {
        	A=config.getPrjAtaveragequarter().doubleValue();
        }else if((v.getEndTime().getTime()>endDate.getTime()) || (v.getEndTime().getTime()<startDate.getTime())) {
        	A=config.getPrjAtaveragequarter().doubleValue();
        }else {
        	A=Math.pow(2,(-(Double.valueOf(v.getAtdi())/v.getActualScale().doubleValue())/config.getPrjAtdiprefix().doubleValue()))*100;
        	A = (A > 100) ? 100 : A; //防止数据超出
        	Asu = A;
        }
        //进度得分B： 按[0, 100%]依次线性得[100,0]分
        // 问题单项目不计算进度得分，无进度偏差数据的也不计算进度得分
        double B=0;
        double scheduleForAll=0;
        if(v.getPCBType().equals("问题单特性")) {
        	B = 100;
        }else {
        	//未完成的项目，B直接算80分
        	//ScheduleForAll这个值，litongxin那边为空的话默认给的是-0.01
        	if(v.getScheduleForAll().equals("-0.01") || endTime.equals("0000-00-00 00:00:00")) {
        		B = 80;
        	}else if((v.getEndTime().getTime()>endDate.getTime()) || (v.getEndTime().getTime()<startDate.getTime())) {
        		B = 80;
        	}else {
        		if (Double.valueOf(v.getScheduleForAll()) > 100){
                    scheduleForAll=100;
                    B = (100-scheduleForAll);
                }else if (Double.valueOf(v.getScheduleForAll()) < 0){
                	scheduleForAll=0;
                	B = (100-scheduleForAll);
                }else {
                	B = (100-Double.valueOf(v.getScheduleForAll()));
                }
                
        	}
        }
        //鉴定得分C： 按鉴定评价的9个等级依次给100到10分
        double C=0;
        boolean noCodeAuth=false;
        if(v.getAppraiseEva().equals("None") || StringUtils.isBlank(v.getAppraiseEva())) {
        	C=0;
        	//无鉴定成绩不计算得分
        	noCodeAuth=true;
        }else {
        	C=SystemCommonUtil.getAppraiseEva(v.getAppraiseEva());
        }
        //审计得分D： 按QA提供的审计分，百分制
        double D=0;
        if(v.getAuditScore().doubleValue()==0) {
        	D=100;
        }else {
        	D=v.getAuditScore().doubleValue();
        }

//        if ($v['AuditScore'] == null){  //待确认这个字段回传会有null值吗，确认为0的话，给100分
//            $D = 100;//没有审计得分计算为满分
//        }else{
//            $D = $v['AuditScore'];
//        }
        
        //开发质量得分Q1x：A:B:C:D按50:25:15:10得分（不计算得分的项不计入比例，未结束的项目取本季平均质量得分）
        double Q1X = A*config.getPrjAtprefix().doubleValue() + B*config.getPrjScheduleprefix().doubleValue() + 
        		C*config.getPrjCodeauthprefix().doubleValue() + D*config.getPrjAuditprefix().doubleValue();
        //Q1X=SystemCommonUtil.doubleFormat3(Q1X);
        //如果该项目的结束时间为当季度，且算出来的时间交集小于1，则证明这个项目是跨季度项目，需要另行计算
        boolean isTrue=false;
        double S1X=0;
        double Sa=0;
        double num=0;
        if(RX<1 && (v.getEndTime().getTime()<endDate.getTime()) && (v.getEndTime().getTime()>startDate.getTime())) {
        	isTrue=false;
        	if(su==null) {
        		num=0;
        	}else {
        		for (int i = 0; i < su.size(); i++) {
            		num=su.get(i).getSa().doubleValue()+num;
    			}
        	}
        	//开发规模得分S1x  = 27 * [Cx^(0.9)] * Rx     
        	// 27为目前使用的开发权重，0.9为规模效应因子，开发综合算1K代码量
        	S1X = config.getPrjDevscaleprefix1().doubleValue()*Math.pow(CX,config.getPrjDevscaleprefix2().doubleValue())*1;
        	Sa = ((Q1X/(100*(config.getPrjAtprefix().doubleValue()+config.getPrjScheduleprefix().doubleValue()+
        			config.getPrjCodeauthprefix().doubleValue()+config.getPrjAuditprefix().doubleValue()))) * S1X);
        	Sa = Sa - num;	
        }else {
        	isTrue=true;
        	S1X = config.getPrjDevscaleprefix1().doubleValue()*Math.pow(CX,config.getPrjDevscaleprefix2().doubleValue())*RX;
        	//开发总得分Sa = ((Q1x/100) * S1x)
        	Sa = ((Q1X/(100*(config.getPrjAtprefix().doubleValue()+config.getPrjScheduleprefix().doubleValue()+
        			config.getPrjCodeauthprefix().doubleValue()+config.getPrjAuditprefix().doubleValue()))) * S1X);
        }
        
        //往project_su表中增加数据
        ProjectSuDO projectSuDO=new ProjectSuDO();
        projectSuDO.setProjectName(v.getProjectName());
        projectSuDO.setProjectId(v.getProjectId());
        //当季度开发总得分
        projectSuDO.setSa(BigDecimal.valueOf(Sa));
        projectSuDO.setQuarter(quarter);
        projectSuDO.setASu(BigDecimal.valueOf(Asu));
        projectSuDO.setSeconddptcode(v.getSeconddptcode());
        //当季度实际开发天数
        projectSuDO.setDaysD((int)daysd);
        projectSulist.add(projectSuDO);
        
        // Kx为分摊比例，((Q1x/100) * S2x)即为开发综合得分，设计负责人也按分摊比例计算。
        for (int i = 0; i < kx.size(); i++) {
        	int contributevalue=kx.get(i).getContributevalue();
        	//计算每一个成员的得分
        	double Amin = (1-A/100)*config.getPrjAtprefix().doubleValue() * S1X;
        	double Bmin = (1-B/100)*config.getPrjScheduleprefix().doubleValue() *S1X;
        	//当鉴定得分不计算时，为NA
        	double Cmin = noCodeAuth ? -1:(1-C/100)*config.getPrjCodeauthprefix().doubleValue() *S1X;  
        	double Dmin = (1-D/100)*config.getPrjAuditprefix().doubleValue() *S1X;
        	String scale="";
        	if(v.getActualScale().doubleValue()!=0d) {
        		scale=v.getActualScale().toString();
        	}else {
        		scale=v.getPlanScale().toString();
        	}
        	ProjectSummaryDO projectSummaryDO=new ProjectSummaryDO();
        	projectSummaryDO.setProjectName(v.getProjectName());
        	projectSummaryDO.setScale(scale);
        	projectSummaryDO.setProjectId(v.getProjectId());
        	projectSummaryDO.setAccount(kx.get(i).getPerson());
        	projectSummaryDO.setSaCount(BigDecimal.valueOf(Sa));
        	projectSummaryDO.setSa(BigDecimal.valueOf(Sa*((double)contributevalue/100)));
        	projectSummaryDO.setAMin(BigDecimal.valueOf(Amin));
        	projectSummaryDO.setBMin(BigDecimal.valueOf(Bmin));
        	projectSummaryDO.setCMin(BigDecimal.valueOf(Cmin));
        	projectSummaryDO.setDMin(BigDecimal.valueOf(Dmin));
        	projectSummaryDO.setS1x(BigDecimal.valueOf(isTrue?S1X : (S1X-num)));
        	projectSummaryDO.setContributevalue(BigDecimal.valueOf(contributevalue));
        	projectSummaryDO.setQuarter(quarter);
        	projectSummaryDO.setType(SystemConstant.TYPE_NORMAL);
        	projectSummaryDO.setProjectType("普通项目");
        	projectSummaryDO.setCountDate(new Date());
        	projectSummaryList.add(projectSummaryDO);
		}
        Map<String,List> map=new HashMap<>();
        map.put("projectSulist", projectSulist);
        map.put("projectSummaryList", projectSummaryList);
		return map;
	}
    
	//阶梯折算函数
    public double getdifficultByScale(double actualScale) {
    	double difficult=0;
    	if (actualScale <= 1) {
    		difficult=0.36*actualScale;
        } else if (actualScale <= 2) {
        	difficult=(0.36 * 1) + (0.25 * (actualScale - 1));
        } else if (actualScale <= 4) {
        	difficult=(0.36 * 1) + (0.25 * 1) + (0.15 * (actualScale - 2));
        } else if (actualScale <= 8) {
        	difficult=(0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * (actualScale - 4));
        } else if (actualScale <= 20) {
        	difficult=(0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * 4) + (0.1 * (actualScale - 8));
        } else {
        	difficult=(0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * 4) + (0.1 * 12) + (0.07 * (actualScale - 20));
        }
    	return difficult;
	}
    
    //获取时间交集
	private Map<String,Double> getWorktime(String start, String end, String quarter, String pauseStart1,
			String pauseEnd1, String pauseStart2, String pauseEnd2, int isOver, int daysCount) throws Exception {
		//举例：项目开始时间start为3.20号，结束时间为6.29号。统计的是Q2季度（start_d为4.1号   end_d为6.30号），当前时间为5.12号
		//当季时间交集
		double RX=0;
		//当季度实际开发天数
		int daysd=0;
		Map<String, Double> map = new HashMap<>();	
		//处理数据，取年月日,舍弃时分秒
        start = start.split(" ")[0];
        end = end.split(" ")[0];
        pauseStart1 =pauseStart1.split(" ")[0];
        pauseEnd1 = pauseEnd1.split(" ")[0];
        pauseStart2 =pauseStart2.split(" ")[0];
        pauseEnd2 = pauseEnd2.split(" ")[0];
        //查询项目有多少个有效工作日
        List<IsoftWorkscheduleDO> workscheList=isoftWorkscheduleService.getListByDate(start, end);
        int days=workscheList.size();
        //2021.4.13   在某些特殊情况下，项目没有结束时间，取计划结束时间，而计划结束时间小于开始时间，这时工时天数为0
      	//查询暂停总时长
        int daysPause=this.getTimes(pauseStart1,pauseEnd1)+this.getTimes(pauseStart2,pauseEnd2);
        //项目总时长=有效工作日-暂停工作日(未结束的项目不需要去除暂停时间)
  		if (isOver == 1){
  			days = days-daysPause;
  		}else{
  			//项目没有结束，那么可以认为在这个季度项目一直干到了季度结束
  			Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
  			end=timeSpaceMap.get("end1").toString();
  		}
  		if (days <= 0){
			RX = 0;
			daysd = 0;
		}else{
			//未结算的项目查询当季度交集了多少个工作日（不包括定时算分的今天），结束的项目不减1
			//if(isOver == 1) {
				daysd = this.getTimesd(start,end,quarter);
//			}else {
//				daysd = this.getTimesd(start,end,quarter)-1;
//			}
			//如果暂停结束时间大于项目结束时间，那么先取项目结束时间，这样才能算出当季度工作日
			if(DateUtil.getDateForDefaultymd(end).getTime()<= DateUtil.getDateForDefaultymd(pauseEnd1).getTime()) {
				pauseEnd1 = end;
			}
			if(DateUtil.getDateForDefaultymd(end).getTime()<= DateUtil.getDateForDefaultymd(pauseEnd2).getTime()) {
				pauseEnd2 = end;
			}
			//当季暂停的交集日
			int days_pause_d=this.getTimesd(pauseStart1,pauseEnd2,quarter)+this.getTimesd(pauseStart2,pauseEnd2,quarter);
			//当季度交集了多少个有效工作日(总的交集日减去暂停的交集日)
			daysd = ((daysd-days_pause_d) > 0) ? (daysd-days_pause_d) : 0;
			//当本月工作天数大于总天数-之前季度工作天数时，取他们的差值
			int daysLeft = ((days-daysCount) > 0) ? days-daysCount : 0;
			if (daysd > daysLeft){
				RX = (double)daysLeft/(double)days;
			}else{
				RX = (double)daysd/(double)days;
			}
			if (RX < 0){
				RX = 0;
			}
		}
		map.put("RX", RX);
		map.put("daysd", Double.valueOf(daysd));
		return map;
		
	}

	private int getTimesd(String start, String end, String quarter) throws Exception {
		//举例：项目开始时间start为3.20号，结束时间为6.29号。统计的是Q2季度（start_d为4.1号   end_d为6.30号），当前时间为5.12号
		if (start.equals("0000-00-00")){
			return 0;   //没有开始时间，则证明没有暂停
		}
		//当前季度的起始时间（不加时分秒的，只有年月日）
		Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
		String start_d = timeSpaceMap.get("start1").toString();
		String end_d = timeSpaceMap.get("end1").toString();
		//查询当前时间，如果当前时间小于季度结束时间，则只算当前时间的交集与工作日
		String now=DateUtil.getDateFormat(new Date());
		if(DateUtil.getDateForDefaultymd(now).getTime()<DateUtil.getDateForDefaultymd(end_d).getTime()) {
			end_d=now;
		}
		//开发开始时间取季度开始时间与项目开始时间的最晚 结束时间相反
		if(DateUtil.getDateForDefaultymd(start_d).getTime()<= DateUtil.getDateForDefaultymd(start).getTime()) {
			start_d = start;
		}
		//当暂停结束时间没有时，认为一直暂停到了季度结束
		end = (end .equals("0000-00-00")) ? end_d : end;  
		if(DateUtil.getDateForDefaultymd(end_d).getTime()>= DateUtil.getDateForDefaultymd(end).getTime()) {
			end_d = end;
		}
		List<IsoftWorkscheduleDO> workscheList=isoftWorkscheduleService.getListByDate(start_d, end_d);
        int daysd=workscheList.size();
		return daysd;
	}

	private int getTimes(String start, String end) throws Exception{
		//项目暂停没有开始或者结束时间，都算作不影响项目的总体开发时长
		if (start.equals("0000-00-00") || end.equals("0000-00-00")){
			return 0;
		}
		if (start.equals("1900-01-01") || end.equals("1900-01-01")){
			return 0;
		}
		List<IsoftWorkscheduleDO> workscheList=isoftWorkscheduleService.getListByDate(start, end);
	    int days=workscheList.size();
		return days;
	}

	public String getNextQuarter(String quarter) throws Exception{
    	Map<Integer, String> param = new HashMap<>();
    	param.put(0, "2020Q1");
    	param.put(1, "2020Q2");
    	param.put(2, "2020Q3");
    	param.put(3, "2020Q4");
    	param.put(4, "2021Q1");
    	param.put(5, "2021Q2");
    	param.put(6, "2021Q3");
    	param.put(7, "2021Q4");
    	param.put(8, "2022Q1");
    	param.put(9, "2022Q2");
    	param.put(10, "2022Q3");
    	param.put(11, "2022Q4");
    	param.put(12, "2023Q1");
    	param.put(13, "2023Q2");
    	param.put(14, "2023Q3");
    	param.put(15, "2023Q4");
    	
    	Integer key=0;
    	String value="";
    	for (Map.Entry<Integer, String> entry : param.entrySet()) {
    		if(entry.getValue().equals(quarter)) {
    			key=entry.getKey()+1;
    			break;
    		}
        }
    	for (Map.Entry<Integer, String> entry : param.entrySet()) {
    		if(entry.getKey().equals(key)) {
    			value=entry.getValue();
    			break;
    		}
        }
		return value;
    }

	@Override
	public Boolean develop(String quarter) throws Exception {
		//String quarter = DateTimeUtils.getQuarter();
		//删除历史数据的代码置后。（不置后，用事务来实现代码报错删除操作回滚）
    	int row = projectSummaryService.deleteByQuarterAndType(quarter, SystemConstant.TYPE_CLOUD);
    	
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start=timeSpaceMap.get("start").toString();
        String end=timeSpaceMap.get("end").toString();
        List<IsoftProjectCloudListDO> isoftCloudList = isoftProjectCloudListService.getIsoftCloudList(start, end);

        Map<String, Object> employeeParam = new HashMap<>();
        List<EmployeeListDO> employeeList = employeeListService.getList(employeeParam);
        //将List数据直接转换为按某一字段分组的Map集合
        Map<String, EmployeeListDO> employeeMap = employeeList.stream().filter(t->StringUtils.isNotBlank(t.getDomainid())).
                collect(Collectors.toMap(EmployeeListDO::getDomainid, employeeListDO -> employeeListDO));
        List<ProjectSummaryDO> projectSummaryList=new ArrayList<>();
        for (int i = 0; i < isoftCloudList.size(); i++) {
            double acceptedCode = Double.parseDouble(StringUtils.isNotBlank(isoftCloudList.get(i).getAcceptedCode())?isoftCloudList.get(i).getAcceptedCode():"0");
            double noAcceptedCode = Double.parseDouble(StringUtils.isNotBlank(isoftCloudList.get(i).getNoAcceptedCode())?isoftCloudList.get(i).getNoAcceptedCode():"0");
            //跳过无用数据
            if(acceptedCode+noAcceptedCode<=0 || noAcceptedCode<=0){
                continue;
            }
            double diDensity = Double.parseDouble(StringUtils.isNotBlank(isoftCloudList.get(i).getDiDensity())?isoftCloudList.get(i).getDiDensity():"0");
            double A1=Math.pow(2,(-diDensity/15))*100;
            if (A1 > 100){
                A1 = 100d;
            }
            if (A1 < 0){
                A1 = 0d;
            }
            double A2=70d;
            
            double progressDeviation = Double.parseDouble(StringUtils.isNotBlank(isoftCloudList.get(i).getProgressDeviation())?isoftCloudList.get(i).getProgressDeviation():"0");
            double B=100-progressDeviation*100;
            double D=100d;
            double X = 1/(0.5 + 0.25 + 0.1) ;
            double Q1X=(A1*0.5 + B*0.25 + D*0.1)*X/100;
            double Q2X=(A2*0.5 + B*0.25 + D*0.1)*X/100;
            double S1X=27*acceptedCode/1000;
            double S2X=27*noAcceptedCode/1000;
            double sa = (Q1X * S1X + Q2X * S2X );
            double aMin=(100-A2)*0.5*0.01 * X * S2X + (100-A1)*0.5*0.01 * X * S1X;
            double bMin=(100-B)*0.25 * 0.01 * X * (S1X + S2X);
            double contribution = Double.parseDouble(StringUtils.isNotBlank(isoftCloudList.get(i).getContribution())?isoftCloudList.get(i).getContribution():"0");

            ProjectSummaryDO projectSummaryDO=new ProjectSummaryDO();
            //+isoftCloudList.get(i).getMonth()不加这个，php代码这里有问题，他写成了mouth
            projectSummaryDO.setProjectName(isoftCloudList.get(i).getProjectName());
            projectSummaryDO.setScale((acceptedCode+noAcceptedCode)/1000+"");
            projectSummaryDO.setProjectId(isoftCloudList.get(i).getProjectId());
            EmployeeListDO employeeListDO = employeeMap.get(isoftCloudList.get(i).getJobNumber().toLowerCase());
            projectSummaryDO.setAccount(employeeListDO!=null?employeeListDO.getAccount():"");
            projectSummaryDO.setSaCount(BigDecimal.valueOf(sa));
            projectSummaryDO.setSa(BigDecimal.valueOf(sa*contribution));
            projectSummaryDO.setAMin(BigDecimal.valueOf(aMin));
            projectSummaryDO.setBMin(BigDecimal.valueOf(bMin));
            projectSummaryDO.setCMin(BigDecimal.valueOf(0d));
            projectSummaryDO.setDMin(BigDecimal.valueOf(0d));
            projectSummaryDO.setS1x(BigDecimal.valueOf(S1X+S2X));
            projectSummaryDO.setContributevalue(BigDecimal.valueOf(contribution*100));
            projectSummaryDO.setQuarter(quarter);
            projectSummaryDO.setType(SystemConstant.TYPE_CLOUD);
            projectSummaryDO.setCountDate(new Date());
            projectSummaryList.add(projectSummaryDO);
        }
        boolean b = projectSummaryService.saveBatch(projectSummaryList);
        if(b){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public Boolean weihu(String quarter) throws Exception {
		//String quarter = DateTimeUtils.getQuarter();
		//删除历史数据的代码置后。（不置后，用事务来实现代码报错删除操作回滚）
        int row = idmEmployeeService.deleteIdmEmployeeByQuarter(quarter);
        
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start = timeSpaceMap.get("start").toString();
        String end = timeSpaceMap.get("end").toString();
        List<DptconfigListDO> dptconfigList = dptconfigListService.getList(new HashMap<>());
        Map<String, DptconfigListDO> dptconfigMap = dptconfigList.stream().filter(t->StringUtils.isNotBlank(t.getDptcode())).
                collect(Collectors.toMap(DptconfigListDO::getDptcode, dptconfigListDO -> dptconfigListDO));
        Map<String, Object> employeeParam = new HashMap<>();
        employeeParam.put("postType",SystemConstant.SOFTWARE_DEV);
        List<EmployeeListDO> employeeList = employeeListService.getList(employeeParam);
        Map<String, EmployeeListDO> employeeMap = employeeList.stream().filter(t->StringUtils.isNotBlank(t.getAccount())).
                collect(Collectors.toMap(EmployeeListDO::getAccount, employeeListDO -> employeeListDO));
        List<IdmEmployeeDO> idmEmployeeList=new ArrayList();
        for (int i = 0; i <employeeList.size() ; i++) {
        	IdmEmployeeDO idmEmployeeDO=new IdmEmployeeDO();
        	idmEmployeeDO.setQuarter(quarter);
        	idmEmployeeDO.setSeconddptcode(employeeList.get(i).getSeconddptcode());
        	idmEmployeeDO.setAccount(employeeList.get(i).getAccount().toLowerCase());
        	idmEmployeeDO.setTotalscore(BigDecimal.valueOf(0d));
        	idmEmployeeDO.setNormalTotalScore(BigDecimal.valueOf(0d));
        	idmEmployeeDO.setNormalSubmitNum(0);
        	idmEmployeeDO.setNormalEditNum(0);
        	idmEmployeeDO.setNormalExamineNum(0);
        	idmEmployeeDO.setSyncTotalScore(BigDecimal.valueOf(0d));
        	idmEmployeeDO.setSyncEditNum(0);
        	idmEmployeeDO.setSyncExamineNum(0);
        	idmEmployeeDO.setNormalLocationNum(0);
        	idmEmployeeDO.setCountDate(new Date());
            idmEmployeeList.add(idmEmployeeDO);
        }
        Map<String, Object> idmsinfoParam = new HashMap<>();
        idmsinfoParam.put("start",start);
        idmsinfoParam.put("end",end);
        idmsinfoParam.put("currentNode",SystemConstant.SOFTWARE_DEV);
        List<IdmsinfoListDO> idmsinfoList = idmsinfoListService.getList(idmsinfoParam);
        for (int i = 0; i <idmsinfoList.size() ; i++) {
            boolean checkFlag = this.checkIdms(idmsinfoList.get(i), dptconfigMap,employeeMap);
            if(!checkFlag){
                continue;
            }
            if("普通单".equals(idmsinfoList.get(i).getIdmsType())){
                //提单得分统计
                for (int j = 0; j < idmEmployeeList.size(); j++) {
                    if(idmsinfoList.get(i).getSubmitBy().equalsIgnoreCase(idmEmployeeList.get(j).getAccount())){
                        //计算得分
                        DptconfigListDO config=dptconfigMap.get(idmEmployeeList.get(j).getSeconddptcode());
                        //发现有的部门在配置表中查不到数据。举例：50043804 质量与运营管理部，在数据库配置下
                        double countScore = config.getBugNormalSubmitcntprefix().doubleValue() +
                                SystemCommonUtil.getIdmsType(idmsinfoList.get(i).getOdcSeverity())*config.getBugNormalSubmitdiprefix().doubleValue();
                        idmEmployeeList.get(j).setNormalSubmitNum(idmEmployeeList.get(j).getNormalSubmitNum()+1);
                        idmEmployeeList.get(j).setNormalTotalScore(BigDecimal.valueOf(idmEmployeeList.get(j).getNormalTotalScore().doubleValue()+countScore));
                        idmEmployeeList.get(j).setTotalscore(BigDecimal.valueOf(idmEmployeeList.get(j).getTotalscore().doubleValue()+countScore));
                        break;
                    }
                }
                //定位得分统计
                for (int j = 0; j < idmEmployeeList.size(); j++) {
                    if(idmsinfoList.get(i).getLocationer().equalsIgnoreCase(idmEmployeeList.get(j).getAccount())){
                        //计算得分
                        DptconfigListDO config=dptconfigMap.get(idmEmployeeList.get(j).getSeconddptcode());
                        double countScore = config.getBugNormalLocationcntprefix().doubleValue() +
                                SystemCommonUtil.getIdmsType(idmsinfoList.get(i).getOdcSeverity())*config.getBugNormalLocationdiprefix().doubleValue();
                        idmEmployeeList.get(j).setNormalLocationNum(idmEmployeeList.get(j).getNormalLocationNum()+1);
                        idmEmployeeList.get(j).setNormalTotalScore(BigDecimal.valueOf(idmEmployeeList.get(j).getNormalTotalScore().doubleValue()+countScore));
                        idmEmployeeList.get(j).setTotalscore(BigDecimal.valueOf(idmEmployeeList.get(j).getTotalscore().doubleValue()+countScore));
                        break;
                    }
                }
                //修改得分统计
                for (int j = 0; j < idmEmployeeList.size(); j++) {
                    if(idmsinfoList.get(i).getNameB().equalsIgnoreCase(idmEmployeeList.get(j).getAccount())){
                        //计算得分
                        DptconfigListDO config=dptconfigMap.get(idmEmployeeList.get(j).getSeconddptcode());
                        double countScore = config.getBugNormalModifycntprefix().doubleValue();
                        idmEmployeeList.get(j).setNormalEditNum(idmEmployeeList.get(j).getNormalEditNum()+1);
                        idmEmployeeList.get(j).setNormalTotalScore(BigDecimal.valueOf(idmEmployeeList.get(j).getNormalTotalScore().doubleValue()+countScore));
                        idmEmployeeList.get(j).setTotalscore(BigDecimal.valueOf(idmEmployeeList.get(j).getTotalscore().doubleValue()+countScore));
                        break;
                    }
                }
                //审核得分统计
                for (int j = 0; j < idmEmployeeList.size(); j++) {
                    if(idmsinfoList.get(i).getNameC().equalsIgnoreCase(idmEmployeeList.get(j).getAccount())){
                        //计算得分
                        DptconfigListDO config=dptconfigMap.get(idmEmployeeList.get(j).getSeconddptcode());
                        double countScore = config.getBugNormalReviewcntprefix().doubleValue();
                        idmEmployeeList.get(j).setNormalExamineNum(idmEmployeeList.get(j).getNormalExamineNum()+1);
                        idmEmployeeList.get(j).setNormalTotalScore(BigDecimal.valueOf(idmEmployeeList.get(j).getNormalTotalScore().doubleValue()+countScore));
                        idmEmployeeList.get(j).setTotalscore(BigDecimal.valueOf(idmEmployeeList.get(j).getTotalscore().doubleValue()+countScore));
                        break;
                    }
                }
            }else if("同步单".equals(idmsinfoList.get(i).getIdmsType())){
                //修改得分统计
                for (int j = 0; j < idmEmployeeList.size(); j++) {
                    if(idmsinfoList.get(i).getNameB().equalsIgnoreCase(idmEmployeeList.get(j).getAccount())){
                        //计算得分
                        DptconfigListDO config=dptconfigMap.get(idmEmployeeList.get(j).getSeconddptcode());
                        double countScore = config.getBugSyncModifycntprefix().doubleValue();
                        idmEmployeeList.get(j).setSyncEditNum(idmEmployeeList.get(j).getSyncEditNum()+1);
                        idmEmployeeList.get(j).setSyncTotalScore(BigDecimal.valueOf(idmEmployeeList.get(j).getSyncTotalScore().doubleValue()+countScore));
                        idmEmployeeList.get(j).setTotalscore(BigDecimal.valueOf(idmEmployeeList.get(j).getTotalscore().doubleValue()+countScore));
                        break;
                    }
                }
                //同步单审核得分
                for (int j = 0; j < idmEmployeeList.size(); j++) {
                    if(idmsinfoList.get(i).getNameC().equalsIgnoreCase(idmEmployeeList.get(j).getAccount())){
                        //计算得分
                        DptconfigListDO config=dptconfigMap.get(idmEmployeeList.get(j).getSeconddptcode());
                        double countScore = config.getBugSyncReviewcntprefix().doubleValue();
                        idmEmployeeList.get(j).setSyncExamineNum(idmEmployeeList.get(j).getSyncExamineNum()+1);
                        idmEmployeeList.get(j).setSyncTotalScore(BigDecimal.valueOf(idmEmployeeList.get(j).getSyncTotalScore().doubleValue()+countScore));
                        idmEmployeeList.get(j).setTotalscore(BigDecimal.valueOf(idmEmployeeList.get(j).getTotalscore().doubleValue()+countScore));
                        break;
                    }
                }
            }
        }
        boolean b = false;
        b = idmEmployeeService.saveBatch(idmEmployeeList);
        if(b){
            return true;
        }else{
            return false;
        }
	}

	public boolean checkIdms(IdmsinfoListDO idmsinfoListDO,Map<String, DptconfigListDO> dptconfigMap,Map<String, EmployeeListDO> employeeMap) throws Exception{
    	//优化下面的代码，提高效率，避免for循环中还直接查询数据库，
    	EmployeeListDO employee = employeeMap.get(idmsinfoListDO.getNameB().toLowerCase());
    	if (employee == null) {
    		employee = employeeMap.get(idmsinfoListDO.getNameC().toLowerCase());
    		//php那块根据 idmsinfo_list中的属性Name_B  Name_C  如果这两个都为空的话，怎么算？这条数据排除？---排除
    		if(employee == null) {
    			return false;
    		}
    	}
//        EmployeeListDO employee = employeeListService.getByAccountAndType(idmsinfoListDO.getNameB(), "软件开发");
//        if (employee == null) {
//            employee = employeeListService.getByAccountAndType(idmsinfoListDO.getNameC(), "软件开发");
//        }
        DptconfigListDO config = dptconfigMap.get(employee.getSeconddptcode());
        //进行过滤
        //非无效问题单简述字段过滤内容
        //非无效问题单的筛选条件:也就是有效问题单的判断
        if (StringUtils.isNotBlank(config.getBugInvalidprefix())){
            String[] split = config.getBugInvalidprefix().split(",");
            for (int i = 0; i <split.length ; i++) {
                if(idmsinfoListDO.getSummery().contains(split[i])){
                    return true;
                }
            }
        }
        //无效问题单简述字段过滤内容：简述中带有“系统测试,验收,规格列表,public,PUBLIC,Public,说明书,第一轮,第二轮,第三轮,手册”的识别为无效单
        if (StringUtils.isNotBlank(config.getBugValidprefix())) {
            String[] split2 = config.getBugValidprefix().split(",");
            for (int i = 0; i < split2.length; i++) {
                if (idmsinfoListDO.getSummery().contains(split2[i])) {
                	logger.info("---kcms_weihu------内容：" + idmsinfoListDO.getDefectNO()+"人员："+employee.getAccount());
                    return false;
                }
            }
        }
        //无效问题单基线子版本过滤条件(包含temp等就是无效的)
        if (StringUtils.isNotBlank(config.getBugValidbaseline())) {
            String[] split3 = config.getBugValidbaseline().split(",");
            for (int i = 0; i < split3.length; i++) {
                if (idmsinfoListDO.getBaseline().contains(split3[i].trim()) || idmsinfoListDO.getBaseline1().contains(split3[i].trim())) {
                	logger.info("---kcms_weihu------条件：" + idmsinfoListDO.getDefectNO()+"人员："+employee.getAccount());
                    return false;
                }
            }
        }
        return true;
    }
	@Override
	public Boolean audit(String quarter) throws Exception {
		//String quarter = DateTimeUtils.getQuarter();
		//删除历史数据的代码置后。（不置后，用事务来实现代码报错删除操作回滚）
    	Map<String, Object> deleteParam = new HashMap<>();
        deleteParam.put("quarter",quarter);
        deleteParam.put("typeForSoft",SystemConstant.TYPE_SOFTWARE);
    	int row = reviewSummaryService.deleteByQuarterAndType(deleteParam);
    	
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start = timeSpaceMap.get("start").toString();
        String end = timeSpaceMap.get("end").toString();
        List<DptconfigListDO> dptconfigList = dptconfigListService.getList(new HashMap<>());
        Map<String, DptconfigListDO> dptconfigMap = dptconfigList.stream().filter(t->StringUtils.isNotBlank(t.getDptcode())).
                collect(Collectors.toMap(DptconfigListDO::getDptcode, dptconfigListDO -> dptconfigListDO));
        Map<String, Object> employeeParam = new HashMap<>();
        employeeParam.put("postTypeSoft",SystemConstant.TYPE_SOFTWARE);
        List<EmployeeListDO> employeeList = employeeListService.getList(employeeParam);
        List<ReviewSummaryDO> insertList=new ArrayList();
        for (int i = 0; i <employeeList.size() ; i++) {
        	ReviewSummaryDO reviewSummaryDO=new ReviewSummaryDO();
            reviewSummaryDO.setAccount(employeeList.get(i).getAccount().toLowerCase());
            reviewSummaryDO.setDomainid(employeeList.get(i).getDomainid());
            reviewSummaryDO.setQuarter(quarter);
            reviewSummaryDO.setSeconddptcode(employeeList.get(i).getSeconddptcode());
            reviewSummaryDO.setType(employeeList.get(i).getPostType());
            reviewSummaryDO.setTotalscore(BigDecimal.valueOf(0d));
            reviewSummaryDO.setTotaldi(BigDecimal.valueOf(0d));
            reviewSummaryDO.setTotalnum(0);
            reviewSummaryDO.setOrdinarynum(0);
            reviewSummaryDO.setCriticalnum(0);
            reviewSummaryDO.setTipnum(0);
            reviewSummaryDO.setTotaltime(BigDecimal.valueOf(0d));
            reviewSummaryDO.setCountDate(new Date());
            insertList.add(reviewSummaryDO);
        }
        //已10000条数据为一组
        int reviewinfoMax = 10000;
        List<ReviewinfoListDO> reviewinfoList = reviewinfoListService.getReviewinfoList(start, end);
        List<List<ReviewinfoListDO>> splitList1 = SystemCommonUtil.splitList(reviewinfoList, reviewinfoMax);
        for (int i = 0; i <splitList1.size() ; i++) {
            List<ReviewinfoListDO> limitList = splitList1.get(i);
            for (int j = 0; j < limitList.size(); j++) {
                for (int k = 0; k <insertList.size() ; k++) {
                    if(limitList.get(j).getAccount().trim().equalsIgnoreCase(insertList.get(k).getAccount())){
                        DptconfigListDO dptconfigListDO = dptconfigMap.get(insertList.get(k).getSeconddptcode());
                        if(dptconfigListDO==null){
                            dptconfigListDO = dptconfigMap.get("50041348");
                        }
                        //计算得分
                        int ordinarynum = limitList.get(j).getOrdinarynum();
                        int criticalnum = limitList.get(j).getCriticalnum();
                        int tipnum = limitList.get(j).getTipnum();
                        int totalnum = limitList.get(j).getTotalnum();
                        double totaltime = limitList.get(j).getTotaltime().doubleValue();
                        double totaldi = limitList.get(j).getTotaldi().doubleValue();
                        double totalscore =totaldi*dptconfigListDO.getReviewDiprefix().doubleValue()+dptconfigListDO.getReviewCntprefix().doubleValue()*totalnum;
                        insertList.get(k).setTotalscore(BigDecimal.valueOf(insertList.get(k).getTotalscore().doubleValue()+totalscore));
                        insertList.get(k).setTotaldi(BigDecimal.valueOf(insertList.get(k).getTotaldi().doubleValue()+totaldi));
                        insertList.get(k).setTotalnum(insertList.get(k).getTotalnum()+totalnum);
                        insertList.get(k).setOrdinarynum(insertList.get(k).getOrdinarynum()+ordinarynum);
                        insertList.get(k).setCriticalnum(insertList.get(k).getCriticalnum()+criticalnum);
                        insertList.get(k).setTipnum(insertList.get(k).getTipnum()+tipnum);
                        insertList.get(k).setTotaltime(BigDecimal.valueOf(insertList.get(k).getTotaltime().doubleValue()+totaltime));
                        break;
                    }
                }
            }
        }

        List<ReviewinfoCloudListDO> reviewinfoCloudList = reviewinfoCloudListService.getReviewinfoCloudList(start, end);
        for (int j = 0; j < reviewinfoCloudList.size(); j++) {
            for (int k = 0; k <insertList.size() ; k++) {
                if(reviewinfoCloudList.get(j).getJobNumber().trim().equals(insertList.get(k).getDomainid())){
                    DptconfigListDO dptconfigListDO = dptconfigMap.get(insertList.get(k).getSeconddptcode());
                    if(dptconfigListDO==null){
                        dptconfigListDO = dptconfigMap.get("50041348");
                    }
                    //计算得分
                    int reviewCount = reviewinfoCloudList.get(j).getReviewCount();
                    double totalscore =reviewCount*dptconfigListDO.getReviewDiprefix().doubleValue()+dptconfigListDO.getReviewCntprefix().doubleValue()*reviewCount;
                    insertList.get(k).setTotalscore(BigDecimal.valueOf(insertList.get(k).getTotalscore().doubleValue()+totalscore));
                    insertList.get(k).setTotaldi(BigDecimal.valueOf(insertList.get(k).getTotaldi().doubleValue()+reviewCount));
                    insertList.get(k).setTotalnum(insertList.get(k).getTotalnum()+reviewCount);
                    insertList.get(k).setOrdinarynum(insertList.get(k).getOrdinarynum()+reviewCount);
                    insertList.get(k).setCriticalnum(insertList.get(k).getCriticalnum());
                    insertList.get(k).setTipnum(insertList.get(k).getTipnum());
                    insertList.get(k).setTotaltime(insertList.get(k).getTotaltime());
                    insertList.get(k).setType(SystemConstant.SOFTWARE_DEV);
                    break;
                }
            }
        }
        boolean b = false;
        if (insertList.size() > 1000) {
            //按每max个一组分割
            int insertMax = 1000;
            List<List<ReviewSummaryDO>> splitList = SystemCommonUtil.splitList(insertList, insertMax);
            for (int i = 0; i < splitList.size(); i++) {
                b = reviewSummaryService.saveBatch(splitList.get(i));
            }
        }else{
            b = reviewSummaryService.saveBatch(insertList);
        }
        if(b){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public Boolean platform(String quarter) throws Exception {
        //删除历史数据的代码置后。//删除历史数据的代码置后。（不置后，用事务来实现代码报错删除操作回滚）
    	Map<String, Object> deleteParam = new HashMap<>();
        deleteParam.put("quarter",quarter);
    	int row = platformSummaryService.deleteQuarterByMap(deleteParam);
    	
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start = timeSpaceMap.get("start").toString();
        String end = timeSpaceMap.get("end").toString();
        List<EmployeeListDO> employeeList = employeeListService.getList(new HashMap<>());
        List<PlatformSummaryDO> insertList=new ArrayList();
        for (int i = 0; i <employeeList.size() ; i++) {
        	PlatformSummaryDO platformSummaryDO=new PlatformSummaryDO();
        	platformSummaryDO.setAccount(employeeList.get(i).getAccount()!=null?employeeList.get(i).getAccount():"");
        	platformSummaryDO.setDomainid(employeeList.get(i).getDomainid());
        	platformSummaryDO.setUserId(employeeList.get(i).getId());
            platformSummaryDO.setQuarter(quarter);
            platformSummaryDO.setTotalScore(BigDecimal.valueOf(0d));
            platformSummaryDO.setExperienceScore(BigDecimal.valueOf(0d));
            platformSummaryDO.setPatentScore(BigDecimal.valueOf(0d));
            platformSummaryDO.setSpecScore(BigDecimal.valueOf(0d));
            platformSummaryDO.setSharedocScore(BigDecimal.valueOf(0d));
            platformSummaryDO.setCbbScore(BigDecimal.valueOf(0d));
            platformSummaryDO.setExperienceNum(0);
            platformSummaryDO.setPatentNum(0);
            platformSummaryDO.setSpecNum(0);
            platformSummaryDO.setSharedocNum(0);
            platformSummaryDO.setYnfxError(0);
            platformSummaryDO.setYnfxSuccess(0);
            platformSummaryDO.setYnfxScore(0);
            platformSummaryDO.setCbbNum(0);
            platformSummaryDO.setCountDate(new Date());
            insertList.add(platformSummaryDO);
        }
        //提取所有需要统计的经验案例
        List<ExperienceinfoListDO> experienceinfoList = experienceinfoListService.getListForPlatform(start, end);
        for (int i = 0; i< experienceinfoList.size(); i++) {
        	double score = 1.5;
        	for (int j = 0; j <insertList.size() ; j++) {
        		if(experienceinfoList.get(i).getCaseAuthor().equalsIgnoreCase(insertList.get(j).getDomainid())){
        			insertList.get(j).setTotalScore(BigDecimal.valueOf(insertList.get(j).getTotalScore().doubleValue()+score));
        			insertList.get(j).setExperienceScore(BigDecimal.valueOf(insertList.get(j).getExperienceScore().doubleValue()+score));
        			insertList.get(j).setExperienceNum(insertList.get(j).getExperienceNum()+1);
                    break;
        		}
        	}
        }
        //提取所有需要统计的专利案例 
        List<PatentinfoListDO> patentinfoList = patentinfoListService.getListForPlatform(start, end);
        for (int i = 0; i< patentinfoList.size(); i++) {
        	double weight = patentinfoList.get(i).getWeight().doubleValue();
        	double score = 5*weight;
        	for (int j = 0; j <insertList.size() ; j++) {
        		if(patentinfoList.get(i).getInventorid().equalsIgnoreCase(insertList.get(j).getUserId())){
        			insertList.get(j).setTotalScore(BigDecimal.valueOf(insertList.get(j).getTotalScore().doubleValue()+score));
        			insertList.get(j).setPatentScore(BigDecimal.valueOf(insertList.get(j).getPatentScore().doubleValue()+score));
        			insertList.get(j).setPatentNum(insertList.get(j).getPatentNum()+1);
                    break;
        		}
        	}
        }
        //提取所有需要统计的共享技术案例
        List<SharedocinfoListDO> sharedocinfoList = sharedocinfoListService.getListForPlatform(start, end);
        for (int i = 0; i< sharedocinfoList.size(); i++) {
        	double score = 3;
        	for (int j = 0; j <insertList.size() ; j++) {
        		if(sharedocinfoList.get(i).getApplicant().equalsIgnoreCase(insertList.get(j).getAccount())){
        			insertList.get(j).setTotalScore(BigDecimal.valueOf(insertList.get(j).getTotalScore().doubleValue()+score));
        			insertList.get(j).setSharedocScore(BigDecimal.valueOf(insertList.get(j).getSharedocScore().doubleValue()+score));
        			insertList.get(j).setSharedocNum(insertList.get(j).getSharedocNum()+1);
                    break;
        		}
        	}
        }
        //提取所有需要统计的规范案例
        List<SpecinfoListDO> specinfoList = specinfoListService.getListForPlatform(start, end);
        for (int i = 0; i< specinfoList.size(); i++) {
        	double score = 5;
        	for (int j = 0; j <insertList.size() ; j++) {
        		//if(specinfoList.get(i).getWfAddname().contains(insertList.get(j).getAccount())){
        		if(specinfoList.get(i).getWfAddname().equalsIgnoreCase(insertList.get(j).getAccount())){
        			insertList.get(j).setTotalScore(BigDecimal.valueOf(insertList.get(j).getTotalScore().doubleValue()+score));
        			insertList.get(j).setSpecScore(BigDecimal.valueOf(insertList.get(j).getSpecScore().doubleValue()+score));
        			insertList.get(j).setSpecNum(insertList.get(j).getSpecNum()+1);
                    break;
        		}
        	}
        }
        //提取所有需要统计的cbb案例
        List<CbbinfoListDO> cbbinfoList = cbbinfoListService.getListForPlatform(start, end);
        for (int i = 0; i< cbbinfoList.size(); i++) {
        	double score = 3;
        	for (int j = 0; j <insertList.size() ; j++) {
        		if(cbbinfoList.get(i).getRespParty().equalsIgnoreCase(insertList.get(j).getAccount())){
        			insertList.get(j).setTotalScore(BigDecimal.valueOf(insertList.get(j).getTotalScore().doubleValue()+score));
        			insertList.get(j).setCbbScore(BigDecimal.valueOf(insertList.get(j).getCbbScore().doubleValue()+score));
        			insertList.get(j).setCbbNum(insertList.get(j).getCbbNum()+1);
                    break;
        		}
        	}
        }
        //提取所有需要统计的疑难复现案例
        List<YnfxinfoListDO> ynfxinfoList = ynfxinfoListService.getListForPlatform(start, end);
        for (int i = 0; i< ynfxinfoList.size(); i++) {
        	for (int j = 0; j <insertList.size() ; j++) {
        		if(ynfxinfoList.get(i).getUserid06b().equalsIgnoreCase(insertList.get(j).getAccount())){
        			if(ynfxinfoList.get(i).getStatus().equals("已经复现，定位完成")) {
        				int score = 8 + 4;
        				insertList.get(j).setTotalScore(BigDecimal.valueOf(insertList.get(j).getTotalScore().doubleValue()+score));
            			insertList.get(j).setYnfxScore(insertList.get(j).getYnfxScore()+score);
            			insertList.get(j).setYnfxSuccess(insertList.get(j).getYnfxSuccess()+1);
                        break;
        			}else if(ynfxinfoList.get(i).getStatus().equals("长期不可复现")) {
        				int score = 4;
        				insertList.get(j).setTotalScore(BigDecimal.valueOf(insertList.get(j).getTotalScore().doubleValue()+score));
            			insertList.get(j).setYnfxScore(insertList.get(j).getYnfxScore()+score);
            			insertList.get(j).setYnfxError(insertList.get(j).getYnfxError()+1);
                        break;
        			}
        		}
        	}
        }
        boolean b = false;
        if (insertList.size() > 1000) {
            //按每max个一组分割
            int insertMax = 1000;
            List<List<PlatformSummaryDO>> splitList = SystemCommonUtil.splitList(insertList, insertMax);
            for (int i = 0; i < splitList.size(); i++) {
                b = platformSummaryService.saveBatch(splitList.get(i));
            }
        }else{
            b = platformSummaryService.saveBatch(insertList);
        }
        if(b){
            return true;
        }else{
            return false;
        }
	}
	
	@Override
	public Boolean totalScore(String quarter) throws Exception {
		//String quarter = DateTimeUtils.getQuarter();
		//删除历史数据的代码置后。（不置后，用事务来实现代码报错删除操作回滚）
    	Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("quarter",quarter);
        columnMap.put("type",SystemConstant.SOFTWARE_DEV);
        int row = quarterScoreService.deleteQuarterScoreByMap(columnMap);
        //删除冗余数据
        Map<String, Object> columnMap1 = new HashMap<>();
        columnMap1.put("account","none");
        int row1 = quarterScoreService.deleteQuarterScoreByMap(columnMap1);
        
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start = timeSpaceMap.get("start").toString();
        String end = timeSpaceMap.get("end").toString();
        Map<String, Object> employeeParam = new HashMap<>();
        employeeParam.put("postType",SystemConstant.SOFTWARE_DEV);
        List<EmployeeListDO> employeeList = employeeListService.getList(employeeParam);
        List<QuarterScoreDO> quarterScoreList=new ArrayList();
        String nowDateStr = DateUtil.getDateStrForDefault(new Date());
        for (int i = 0; i <employeeList.size() ; i++) {
            QuarterScoreDO quarterScoreDO=new QuarterScoreDO();
            quarterScoreDO.setQuarter(quarter);
            quarterScoreDO.setAccount(employeeList.get(i).getAccount());
            quarterScoreDO.setReviewScore(BigDecimal.valueOf(0d));
            quarterScoreDO.setTotalScore(BigDecimal.valueOf(0d));
            quarterScoreDO.setMaintainScore(BigDecimal.valueOf(0d));
            quarterScoreDO.setAddScore(BigDecimal.valueOf(0d));
            quarterScoreDO.setDevelopScore(BigDecimal.valueOf(0d));
            quarterScoreDO.setPlatformScore(BigDecimal.valueOf(0d));
            quarterScoreDO.setChargeScore(BigDecimal.valueOf(0d));
            quarterScoreDO.setUpdateTime(nowDateStr);
            quarterScoreDO.setFirstdptcode(employeeList.get(i).getFirstdptcode());
            quarterScoreDO.setSeconddptcode(employeeList.get(i).getSeconddptcode());
            quarterScoreDO.setThirddptcode(employeeList.get(i).getThirddptcode());
            quarterScoreDO.setCurrentdptcode(employeeList.get(i).getCurrentdptcode());
            quarterScoreDO.setFivedptcode(employeeList.get(i).getFivedptcode());
            quarterScoreDO.setFirstdpt(employeeList.get(i).getFirstdpt());
            quarterScoreDO.setSeconddpt(employeeList.get(i).getSeconddpt());
            quarterScoreDO.setThirddpt(employeeList.get(i).getThirddpt());
            quarterScoreDO.setCurrentdpt(employeeList.get(i).getCurrentdpt());
            quarterScoreDO.setFivedpt(employeeList.get(i).getFivedpt());
            quarterScoreDO.setSpecial(employeeList.get(i).getSpecial().toString());
            quarterScoreDO.setType(SystemConstant.SOFTWARE_DEV);
            quarterScoreList.add(quarterScoreDO);
        }
        //计算开发得分
        Map<String, Object> projectSummaryMap = new HashMap<>();
        projectSummaryMap.put("quarter",quarter);
        List<ProjectSummaryDO> projectSummaryList = projectSummaryService.getProjectSummaryList(projectSummaryMap);
        for (int i = 0; i < projectSummaryList.size(); i++) {
            for (int j = 0; j < quarterScoreList.size(); j++) {
                if(projectSummaryList.get(i).getAccount().equalsIgnoreCase(quarterScoreList.get(j).getAccount())){
                    double sa=projectSummaryList.get(i).getSa().doubleValue();
                    quarterScoreList.get(j).setTotalScore(BigDecimal.valueOf(quarterScoreList.get(j).getTotalScore().doubleValue()+sa));
                    quarterScoreList.get(j).setDevelopScore(BigDecimal.valueOf(quarterScoreList.get(j).getDevelopScore().doubleValue()+sa));
                    break;
                }
            }
        }
        //计算维护得分
        Map<String, Object> idmEmployeeMap = new HashMap<>();
        idmEmployeeMap.put("quarter",quarter);
        List<IdmEmployeeDO> idmEmployeeList = idmEmployeeService.getIdmEmployeeList(idmEmployeeMap);
        for (int i = 0; i <idmEmployeeList.size() ; i++) {
            for (int j = 0; j <quarterScoreList.size() ; j++) {
                if(idmEmployeeList.get(i).getAccount().equalsIgnoreCase(quarterScoreList.get(j).getAccount())){
                    double totalscore=idmEmployeeList.get(i).getTotalscore().doubleValue();
                    quarterScoreList.get(j).setTotalScore(BigDecimal.valueOf(quarterScoreList.get(j).getTotalScore().doubleValue()+totalscore));
                    quarterScoreList.get(j).setMaintainScore(BigDecimal.valueOf(quarterScoreList.get(j).getMaintainScore().doubleValue()+totalscore));
                    break;
                }
            }
        }
        //计算评审得分
        Map<String, Object> reviewSummaryMap = new HashMap<>();
        reviewSummaryMap.put("quarter",quarter);
        List<ReviewSummaryDO> reviewSummaryList = reviewSummaryService.getReviewSummaryList(reviewSummaryMap);
        for (int i = 0; i <reviewSummaryList.size() ; i++) {
            for (int j = 0; j <quarterScoreList.size() ; j++) {
                if(reviewSummaryList.get(i).getAccount().equalsIgnoreCase(quarterScoreList.get(j).getAccount())){
                    double totalscore=reviewSummaryList.get(i).getTotalscore().doubleValue();
                    quarterScoreList.get(j).setTotalScore(BigDecimal.valueOf(quarterScoreList.get(j).getTotalScore().doubleValue()+totalscore));
                    quarterScoreList.get(j).setReviewScore(BigDecimal.valueOf(quarterScoreList.get(j).getReviewScore().doubleValue()+totalscore));
                    break;
                }
            }
        }
        //计算关键事件得分
        Map<String, Object> addScoreMap = new HashMap<>();
        addScoreMap.put("quarter",quarter);
        List<AddScoreResVO> addScoreList = addScoreService.getListForTotalScore(addScoreMap);
        for (int i = 0; i <addScoreList.size() ; i++) {
            for (int j = 0; j <quarterScoreList.size() ; j++) {
                if(addScoreList.get(i).getAccount().equalsIgnoreCase(quarterScoreList.get(j).getAccount())){
                    double score=addScoreList.get(i).getScore();
                    quarterScoreList.get(j).setTotalScore(BigDecimal.valueOf(quarterScoreList.get(j).getTotalScore().doubleValue()+score));
                    quarterScoreList.get(j).setAddScore(BigDecimal.valueOf(quarterScoreList.get(j).getAddScore().doubleValue()+score));
                    break;
                }
            }
        }
        //计算平台贡献得分
        Map<String, Object> platformSummaryMap = new HashMap<>();
        platformSummaryMap.put("quarter",quarter);
        List<PlatformSummaryDO> platformList = platformSummaryService.getPlatformList(platformSummaryMap);
        for (int i = 0; i <platformList.size() ; i++) {
            for (int j = 0; j <quarterScoreList.size() ; j++) {
                if(platformList.get(i).getAccount().equalsIgnoreCase(quarterScoreList.get(j).getAccount())){
                    double totalscore=platformList.get(i).getTotalScore().doubleValue();
                    quarterScoreList.get(j).setTotalScore(BigDecimal.valueOf(quarterScoreList.get(j).getTotalScore().doubleValue()+totalscore));
                    quarterScoreList.get(j).setPlatformScore(BigDecimal.valueOf(quarterScoreList.get(j).getPlatformScore().doubleValue()+totalscore));
                    break;
                }
            }
        }
        boolean b = false;
        b = quarterScoreService.saveBatch(quarterScoreList);
        if(b){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public Boolean devModelV(String quarter) throws Exception {
    	String a1 = quarter.substring(0, quarter.indexOf("Q"));
    	String a2 = quarter.substring(a1.length(), quarter.length());
    	//贡献度查询需要传这种形式：2021-Q1
    	String quarter1 =a1+"-"+a2;
    	//删除历史数据的代码已经置后了。(置后有问题)(先备份表数据)
    	//把定时计算软件开发得分的删除逻辑移到汇算成功后再删除，以免删除数据了但是汇算报异常了没有数据。
    	int row = projectSuVService.deleteByQuarter(quarter);
    	//删除可能存在的，下个季度的项目数据
    	String nextQuarter=this.getNextQuarter(quarter);
    	int row1 = projectSuVService.deleteByQuarter(nextQuarter);
    	int row2 = projectSummaryService.deleteByQuarterAndTypeForV(quarter, SystemConstant.TYPE_NORMAL,SystemConstant.TYPE_V);
    	
    	//获取软件开发config信息  map(dptcode,DptconfigListDO)形式
    	List<DptconfigListDO> dptconfigList = dptconfigListService.getList(new HashMap<>());
        Map<String, DptconfigListDO> dptconfigMap = dptconfigList.stream().filter(t->StringUtils.isNotBlank(t.getDptcode())).
                collect(Collectors.toMap(DptconfigListDO::getDptcode, dptconfigListDO -> dptconfigListDO));
        //获取贡献度  map(projectId,List(IsoftContributionListDO))形式
        List<IsoftContributionListDO> contributionList = isoftContributionListService.getContributionListByQuarter(quarter1);
        Map<String, List<IsoftContributionListDO>> contributionMap = contributionList.stream().filter(t->StringUtils.isNoneBlank(t.getProjectId())).
        	collect(Collectors.groupingBy(IsoftContributionListDO::getProjectId));
        //查询V模型项目历史季度分数  map(projectId,List(ProjectSuVDO))形式
        List<ProjectSuVDO> projectSuVlist = projectSuVService.getList();
        Map<String, List<ProjectSuVDO>> projectVMap = projectSuVlist.stream().filter(t->StringUtils.isNoneBlank(t.getProjectId())).
        		collect(Collectors.groupingBy(ProjectSuVDO::getProjectId));
        //查询project_summary历史季度分数   map(projectId,List(ProjectSummaryDO))形式
        List<ProjectSummaryDO> projectSummaryList = projectSummaryService.getProjectSummaryList(new HashMap<>());
        Map<String, List<ProjectSummaryDO>> projectSummaryMap = projectSummaryList.stream().filter(t->StringUtils.isNoneBlank(t.getProjectId())).
        		collect(Collectors.groupingBy(ProjectSummaryDO::getProjectId));
    	//获取当季当量数据表数据 map(projectId,List(ProjectQuarterWorkListDO))形式
        List<ProjectQuarterWorkListDO> quarterWorkList = projectQuarterWorkListService.getList();
        Map<String, List<ProjectQuarterWorkListDO>> quarterWorkMap = quarterWorkList.stream().filter(t->StringUtils.isNoneBlank(t.getProjectId())).
        		collect(Collectors.groupingBy(ProjectQuarterWorkListDO::getProjectId));
        //获取idms数据 （流水号区分） map(ProjectSerialNum,List(IdmsinfoListDO))形式
        List<IdmsinfoListDO> idmsList = idmsinfoListService.getListForModelV();
        Map<String, List<IdmsinfoListDO>> idmsMap = idmsList.stream().filter(t->StringUtils.isNoneBlank(t.getProjectSerialNum())).
        		collect(Collectors.groupingBy(IdmsinfoListDO::getProjectSerialNum));
        //获取项目验收问题单视图表map(projectId,List(ProjectAcceptanceProblemsListDO))形式
        List<ProjectAcceptanceProblemsListDO> projectProblemsList = projectAcceptanceProblemsListService.getList();
        Map<String, List<ProjectAcceptanceProblemsListDO>> problemsMap = projectProblemsList.stream().filter(t->StringUtils.isNoneBlank(t.getProjectId())).
        		collect(Collectors.groupingBy(ProjectAcceptanceProblemsListDO::getProjectId));
        
        //初始化循环的list
        List<ProjectSuVDO> projectSuV=new ArrayList<>();
        List<ProjectSummaryDO> projectSummary=new ArrayList<>();
    	List<ProjectProblemsDiDO> projectProblemsDi=new ArrayList<>();
        //以5000条数据为一组
        int isoftProjectMax = 5000;
        //ProType为2表示V模型项目
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("ProType", "2");
        List<IsoftProjectListDO> isoftProjectList=isoftProjectListService.getIsoftListByParam(paramMap);
        List<List<IsoftProjectListDO>> splitList1 = SystemCommonUtil.splitList(isoftProjectList, isoftProjectMax);
        for (int i = 0; i <splitList1.size() ; i++) {
            List<IsoftProjectListDO> limitList = splitList1.get(i);
            for (int j = 0; j < limitList.size(); j++) {
            	String projectId=limitList.get(j).getProjectId();
            	//流水号信息
            	String projectSerialNum = limitList.get(j).getProjectSerialNum();
            	String seconddptcode=limitList.get(j).getSeconddptcode();
            	//因为上游数据Seconddptcode传过来可能为空，litongxin说这个人在employee_list查不到，那这个人不存在，这个单子过滤掉
            	if(StringUtils.isBlank(limitList.get(j).getSeconddptcode())) {
            		continue;
            	}
            	List<IsoftContributionListDO> kx=contributionMap.get(projectId);
            	//因为上游数据贡献度表要是没有这个项目的话直接过滤掉
            	if(kx==null) {
            		continue;
            	}
            	//因为上游数据projectSerialNum为空的话，则该项目去idms根据流水号找不到，直接过滤掉
            	if(StringUtils.isBlank(projectSerialNum)) {
            		continue;
            	}
            	List<ProjectSuVDO> suv=projectVMap.get(projectId);
            	List<ProjectSummaryDO> proSumm=projectSummaryMap.get(projectId);
            	List<ProjectQuarterWorkListDO> quarterWork = quarterWorkMap.get(projectId);
            	DptconfigListDO config = dptconfigMap.get(seconddptcode);
            	List<IdmsinfoListDO> idms = idmsMap.get(projectSerialNum);
            	//该项目关联的问题单集合
            	List<ProjectAcceptanceProblemsListDO> problemsList = problemsMap.get(projectId);
                if(config==null){
                	config = dptconfigMap.get("50041348");
                }
            	Map<String, List> dev = this.devForModelV(limitList.get(j),kx,config,quarter,suv,quarterWork,idms,idmsList,problemsList,proSumm);
            	projectSuV.addAll(dev.get("projectSuVList"));
            	projectSummary.addAll(dev.get("projectSummaryList"));
            	projectProblemsDi.addAll(dev.get("projectProblemsDiList"));
            }
        }
        boolean a = false;
        boolean b = false;
        boolean c = false;
        //分批往projectSuV表中增加数据
        if (projectSuV.size() > 1000) {
            //按每max个一组分割
            int insertMax = 1000;
            List<List<ProjectSuVDO>> splitList = SystemCommonUtil.splitList(projectSuV, insertMax);
            for (int i = 0; i < splitList.size(); i++) {
                a = projectSuVService.saveBatch(splitList.get(i));
            }
        }else{
            a = projectSuVService.saveBatch(projectSuV);
        }
        //分批往project_summary表中增加数据
        if (projectSummary.size() > 1000) {
            //按每max个一组分割
            int insertMax = 1000;
            List<List<ProjectSummaryDO>> splitList = SystemCommonUtil.splitList(projectSummary, insertMax);
            for (int i = 0; i < splitList.size(); i++) {
                b = projectSummaryService.saveBatch(splitList.get(i));
            }
        }else{
            b = projectSummaryService.saveBatch(projectSummary);
        }
        //分批往project_problems_di表中增加数据
        if (projectProblemsDi.size() > 1000) {
            //按每max个一组分割
            int insertMax = 1000;
            List<List<ProjectProblemsDiDO>> splitList = SystemCommonUtil.splitList(projectProblemsDi, insertMax);
            for (int i = 0; i < splitList.size(); i++) {
                c = projectProblemsDiService.saveBatch(splitList.get(i));
            }
        }else{
            c = projectProblemsDiService.saveBatch(projectProblemsDi);
        }
        if(a && b && c){
            return true;
        }else{
            return false;
        }
	}
	
	public Map<String, List> devForModelV(IsoftProjectListDO v, List<IsoftContributionListDO> kx,
			DptconfigListDO config, String quarter, List<ProjectSuVDO> suv,List<ProjectQuarterWorkListDO> quarterWork,
			List<IdmsinfoListDO> idmsList,List<IdmsinfoListDO> idmsListAll,
			List<ProjectAcceptanceProblemsListDO> problemsList,List<ProjectSummaryDO> proSumm) throws Exception {
		List<ProjectSuVDO> projectSuVList=new ArrayList<>();
    	List<ProjectSummaryDO> projectSummaryList=new ArrayList<>();
    	List<ProjectProblemsDiDO> projectProblemsDiList=new ArrayList<>();
    	//处理脏数据
    	String endTime="";
    	//litongxin反馈这个字段给个默认值为0001-01-01 00:00:00 ，但是查询的时候会报错，统一成1900-01-01 00:00:000 这个值
    	if(DateUtil.getDateStrForDefault(v.getEndTime()).equals("1900-01-01 00:00:00")) {
    		endTime="0000-00-00 00:00:00";
    	}else {
    		endTime=DateUtil.getDateStrForDefault(v.getEndTime());
    	}
    	//代码量折算 综合代码量
//    	double difficult=0;
//    	if(v.getDifficult().doubleValue()<=0 || v.getDifficult().doubleValue()>10) {
//    		difficult=1;
//    	}else {
//    		difficult=v.getDifficult().doubleValue();
//    	}
//        double actualScale=0;
//        if(v.getActualScale().doubleValue()>0) {
//        	actualScale=v.getActualScale().doubleValue();
//        }else {
//        	actualScale=v.getPlanScale().doubleValue();
//        }
//    	//项目类型为移植项目（包含分支内移植和跨分支移植）时，项目规模会做折算
//        if(StringUtils.isNotBlank(v.getPCBType())) {
//	        if(v.getPCBType().contains("移植")) {
//	        	if (actualScale <= 1) {
//	        		difficult=0.36;
//	            } else if (actualScale <= 2) {
//	            	difficult=((0.36 * 1) + (0.25 * (actualScale - 1))) / actualScale;
//	            } else if (actualScale <= 4) {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * (actualScale - 2))) / actualScale;
//	            } else if (actualScale <= 8) {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * (actualScale - 4))) / actualScale;
//	            } else if (actualScale <= 20) {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * 4) + (0.1 * (actualScale - 8))) / actualScale;
//	            } else {
//	            	difficult=((0.36 * 1) + (0.25 * 1) + (0.15 * 2) + (0.13 * 4) + (0.1 * 12) + (0.07 * (actualScale - 20))) / actualScale;
//	            }
//	        }
//	    }
//        //没有实际生产率则取计划生产率 (7/17修改，统一按照计划生产率计算)
//        double proRate =v.getPlanProRate().doubleValue();
//        double manHours =v.getPlanManHours().doubleValue();
//        //实际生产率大于200，则对项目规模进行折算；
//        if (difficult*proRate > 200){
//        	actualScale = (200/difficult)*(manHours/8)/1000;
//        }
        
    	//当前时间季度的起止时间
    	Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String startDateStr=timeSpaceMap.get("start").toString();
        String endDateStr=timeSpaceMap.get("end").toString();
        String endDateStr1=timeSpaceMap.get("end1").toString();
        Date startDate=DateUtil.getDateForDefault(startDateStr);
        Date endDate=DateUtil.getDateForDefault(endDateStr);
        Date endDate1=DateUtil.getDateForDefaultymd(endDateStr1);//不加时分秒，只有年月日
        
      //验收得分A： 100 * 2^(-项目DI密度/15)   项目DI密度= ATDI/实际规模
        //如果没有验收，则取上季平均验收得分。其中DI密度按实际规模（移植类按折算规模）
        double A=0;
        double Asu=0;
        //Atdi这个值，litongxin那边为空的话默认给的是-0.01
        if(endTime.equals("0000-00-00 00:00:00") || v.getAtdi().equals("-99") || v.getAtdi().equals("-0.01") ||
        		StringUtils.isBlank(v.getAtdi()) || v.getActualScale().doubleValue()==0) {
        	A=config.getPrjAtaveragequarter().doubleValue();
        }else if((v.getEndTime().getTime()>endDate.getTime()) || (v.getEndTime().getTime()<startDate.getTime())) {
        	A=config.getPrjAtaveragequarter().doubleValue();
        }else {
        	A=Math.pow(2,(-(Double.valueOf(v.getAtdi())/v.getActualScale().doubleValue())/config.getPrjAtdiprefix().doubleValue()))*100;
        	A = (A > 100) ? 100 : A; //防止数据超出
        	Asu = A;
        }
        //进度得分B： 按[0, 100%]依次线性得[100,0]分
        // 问题单项目不计算进度得分，无进度偏差数据的也不计算进度得分
        double B=0;
        double scheduleForAll=0;
        if(v.getPCBType().equals("问题单特性")) {
        	B = 100;
        }else {
        	//未完成的项目，B直接算80分
        	//ScheduleForAll这个值，litongxin那边为空的话默认给的是-0.01
        	if(v.getScheduleForAll().equals("-0.01") || endTime.equals("0000-00-00 00:00:00")) {
        		B = 80;
        	}else if((v.getEndTime().getTime()>endDate.getTime()) || (v.getEndTime().getTime()<startDate.getTime())) {
        		B = 80;
        	}else {
        		if (Double.valueOf(v.getScheduleForAll()) > 100){
                    scheduleForAll=100;
                    B = (100-scheduleForAll);
                }else if (Double.valueOf(v.getScheduleForAll()) < 0){
                	scheduleForAll=0;
                	B = (100-scheduleForAll);
                }else {
                	B = (100-Double.valueOf(v.getScheduleForAll()));
                }
                
        	}
        }
        //鉴定得分C： 按鉴定评价的9个等级依次给100到10分
        double C=0;
        boolean noCodeAuth=false;
        if(v.getAppraiseEva().equals("None") || StringUtils.isBlank(v.getAppraiseEva())) {
        	C=0;
        	//无鉴定成绩不计算得分
        	noCodeAuth=true;
        }else {
        	C=SystemCommonUtil.getAppraiseEva(v.getAppraiseEva());
        }
        //审计得分D： 按QA提供的审计分，百分制
        double D=0;
        if(v.getAuditScore().doubleValue()==0) {
        	D=100;
        }else {
        	D=v.getAuditScore().doubleValue();
        }

//        if ($v['AuditScore'] == null){  //待确认这个字段回传会有null值吗，确认为0的话，给100分
//            $D = 100;//没有审计得分计算为满分
//        }else{
//            $D = $v['AuditScore'];
//        }
        
        //开发质量得分Q1x：A:B:C:D按50:25:15:10得分（不计算得分的项不计入比例，未结束的项目取本季平均质量得分）
        double Q1X = A*config.getPrjAtprefix().doubleValue() + B*config.getPrjScheduleprefix().doubleValue() + 
        		C*config.getPrjCodeauthprefix().doubleValue() + D*config.getPrjAuditprefix().doubleValue();
        
        //存入到List<project_problems_di>操作：
        double proQuestDI=0;//项目系统测试总DI
        double proCheckDI=0;//项目系统验证测试总DI
        //正在进行的项目，要是没有问题单，则idmsList为null，problemsList为null，则这些DI都为0。
        if(idmsList!=null) {
        	for (int i = 0; i < idmsList.size(); i++) {
        		proQuestDI += SystemCommonUtil.getIdmsType(idmsList.get(i).getOdcSeverity());
    		}
        }
        if(problemsList!=null) {
	        for (int ii = 0; ii < problemsList.size(); ii++) {
	        	//遍历所有的idms问题单号
	        	for (int ik = 0; ik < idmsListAll.size(); ik++) {
					if(problemsList.get(ii).getDefectNo().equals(idmsListAll.get(ik).getDefectNO())) {
						proCheckDI+= SystemCommonUtil.getIdmsType(idmsListAll.get(ik).getOdcSeverity());
					}
				}
			}
        }
        
        for (int j = 0; j < kx.size(); j++) {
        	double subQuestDI=0;//个人提单总DI
        	double solveQuestDI=0;//个人解单总DI
        	if(idmsList!=null) {
	        	for (int k = 0; k < idmsList.size(); k++) {
	        		if(idmsList.get(k).getSubmitBy().equalsIgnoreCase(kx.get(j).getPerson())){
	        			subQuestDI+= SystemCommonUtil.getIdmsType(idmsList.get(k).getOdcSeverity());
	        		}
	        		if(idmsList.get(k).getNameB().equalsIgnoreCase(kx.get(j).getPerson())){
	        			solveQuestDI+= SystemCommonUtil.getIdmsType(idmsList.get(k).getOdcSeverity());
					}
				}
        	}
        	double checkSolveQDI=0;//个人验收测试解单总DI
        	if(problemsList!=null) {
	        	for (int k = 0; k < idmsListAll.size(); k++) {
	        		if(idmsListAll.get(k).getNameB().equalsIgnoreCase(kx.get(j).getPerson())){
	        			for (int m = 0; m < problemsList.size(); m++) {
							if(idmsListAll.get(k).getDefectNO().equals(problemsList.get(m).getDefectNo())) {
								checkSolveQDI+= SystemCommonUtil.getIdmsType(idmsListAll.get(k).getOdcSeverity());
							}
	        			}
					}
				}
        	}
        	//往project_problems_di 项目总问题单DI汇算表中赋值
        	ProjectProblemsDiDO projectProblemsDiDO=new ProjectProblemsDiDO();
        	projectProblemsDiDO.setProjectSerialNum(v.getProjectSerialNum());
        	projectProblemsDiDO.setProjectId(v.getProjectId());
        	projectProblemsDiDO.setProjectName(v.getProjectName());
        	projectProblemsDiDO.setAccount(kx.get(j).getPerson());
        	projectProblemsDiDO.setSubQuestDI(BigDecimal.valueOf(subQuestDI));
        	projectProblemsDiDO.setSolveQuestDI(BigDecimal.valueOf(solveQuestDI));
        	projectProblemsDiDO.setCheckSolveQDI(BigDecimal.valueOf(checkSolveQDI));
        	projectProblemsDiDO.setProQuestDI(BigDecimal.valueOf(proQuestDI));
        	projectProblemsDiDO.setProCheckDI(BigDecimal.valueOf(proCheckDI));
        	projectProblemsDiDO.setCountDate(new Date());
        	projectProblemsDiList.add(projectProblemsDiDO);
        }
        //转成map(Account，ProjectProblemsDiDO)形式，下面好用
        Map<String, ProjectProblemsDiDO> projectProblemsDiMap = projectProblemsDiList.stream().filter(t->StringUtils.isNotBlank(t.getAccount())).
                collect(Collectors.toMap(ProjectProblemsDiDO::getAccount, projectProblemsDiDO -> projectProblemsDiDO));
        
        //存入到List<project_su_v>操作：
        //该项目当季工作量W1X
        double W1X=0;
        //该项目当季得分SA
        double sa=0;
        //标记是否是第一季度
        boolean isFirst=true;
        //之前季度的总工作量
        double beforeWorkTotal=0;
        //之前季度的总得分
        double beforeScoreTotal=0;
        for (int i = 0; i < quarterWork.size(); i++) {
    	  if(quarterWork.get(i).getQuarterStr().equals(DateTimeUtils.getQuarter1())) {
				W1X=quarterWork.get(i).getProQuarWork().doubleValue();
			}
		}
        //本季度项目或者跨季度第一季度。
        if((v.getStartTime().getTime()<endDate.getTime()) && (v.getStartTime().getTime()>startDate.getTime())) {
        	//当季项目实际得分SA = （Q1X*W1X*参数【项目工作量系数】）/100，Q1X为实际值；
        	sa=(Q1X*W1X*config.getVProWork().doubleValue())/100;
        	isFirst=true;
        }else {
        //跨季度其他季度
        	for (int i = 0; i < suv.size(); i++) {
        		beforeWorkTotal+=suv.get(i).getQuarterWork().doubleValue();
        		beforeScoreTotal+=suv.get(i).getSa().doubleValue();
			}
        	//跨季度项目：（每个季度工作量之和*参数【项目工作量系数】*Q1X）/100-之前季度归档季度的实际项目得分
    		//SA=(beforeWorkTotal+W1X)*参数【项目工作量系数】*Q1X）/100-beforeScoreTotal
        	sa=(beforeWorkTotal+W1X)*Q1X*config.getVProWork().doubleValue()/100-beforeScoreTotal;
        	isFirst=false;
        }
        ProjectSuVDO projectSuVDO=new ProjectSuVDO();
        projectSuVDO.setProjectId(v.getProjectId());
        projectSuVDO.setProjectName(v.getProjectName());
        projectSuVDO.setSa(BigDecimal.valueOf(sa));
        projectSuVDO.setQuarter(quarter);
        projectSuVDO.setSeconddptcode(v.getSeconddptcode());
        projectSuVDO.setQuarterWork(BigDecimal.valueOf(W1X));
        projectSuVList.add(projectSuVDO);
        
        
        //存入到List<project_summary>操作：
        //项目的个人贡献度
        double contributevalue=0;
        //员工当季工作量：当季总工作量(项目当量表)*当季员工贡献度百分比
        double accountWork=0;
        //项目满分  项目当季总得分（满分）saFull = （Q1X*W1X*参数【项目工作量系数】）/100，Q1X默认为100
        double saFull=(100*W1X*config.getVProWork().doubleValue())/100;
        //该项目个人得分
        double Sx=0;
        //V模型项目还是普通项目
        String projectType="V模型项目";
        //项目截至当日的得分Bx
        double Bx=0;
        //项目截至当日员工的贡献度百分比Rx
        double Rx=0;
        //Kx为分摊比例。按照贡献度表中的对应项目的人员贡献度算每个人的分数
		for (int j = 0; j < kx.size(); j++) {
			ProjectProblemsDiDO di=projectProblemsDiMap.get(kx.get(j).getPerson());
			//如果是第一季度
			if(isFirst) {
				//个人的贡献度怎么算？kx.get(i)	
				contributevalue=kx.get(j).getContributevalue();
				//员工工作量account_work：Q1当季总工作量(项目当量表)*Q1当季贡献度百分比(kx.get(i))
				accountWork=W1X*kx.get(j).getContributevalue()/100;
				//个人项目得分Sx = (项目当季工作量*参数【项目工作量系数】- 系统测试总DI*系数A - 验收测试解单(这个项目的验收问题，取项目验收问题单视图表中的值关联)总DI*系数B)
				//			*质量得分*项目当季个人贡献度  + [(个人提单的系统测试总DI值*提单系数 + 个人解单（系统测试单）总DI值*解单系数)*系数A +个人验收测试解单总DI*系数B]*质量得分
				Sx=(W1X*config.getVProWork().doubleValue()- di.getProQuestDI().doubleValue()*config.getVSystestdiA().doubleValue()-di.getProCheckDI().doubleValue()*config.getVChecktestdiB().doubleValue())*Q1X/100*contributevalue/100+
					(di.getSubQuestDI().doubleValue()*config.getVSubquestdi().doubleValue()+di.getSolveQuestDI().doubleValue()*config.getVSolvequestdi().doubleValue()+di.getCheckSolveQDI().doubleValue()*config.getVChecktestdiB().doubleValue())*Q1X/100;	
			}else {
			//跨季度其他季度
				//取project_summary(Map(projectId,List<project_summary>)参数传过来)按照项目找到这个人的前几个 季度的个人得分之和   before_Sa   员工工作量	account_work
				double beforeSa=0;
				double beforeAccountWork=0;
				for (int k = 0; k < proSumm.size(); k++) {
					if(proSumm.get(k).getAccount().equals(kx.get(j).getPerson())) {
						beforeSa+=proSumm.get(k).getSa().doubleValue();
						beforeAccountWork=+proSumm.get(k).getAccountWork().doubleValue();
					}
				}
				contributevalue=kx.get(j).getContributevalue();
				/*1.	项目截至当日总工作量=项目所有季度工作量之和(beforeWorkTotal+w1x)
				2.	项目截至当日的得分Bx = ((before_work+w1x)*参数【项目工作量系数】- 系统测试总DI*系数A-系统验收测试总DI*系数B)*质量得分---这个字段不用存
				3.	项目截至当日员工的贡献度百分比Rx =(员工Cx 所有季度项目工作量之和/项目截至当日总工作量)-----？              /(before_work+w1x)
				项目员工截至当日Cx 所有季度工作量之和 =Q1当季总工作量(项目当量表)*Q1当季贡献度百分比+Q2当季总工作量*Q2当季贡献度百分比
				sum（当季总工作量(项目当量表)*当季贡献度百分比）
				员工工作量account_work：当前季度的
				4.	员工Cx截至当日项目实际总得分Sx = Bx *Rx+  [(个人提单的系统测试总DI值*提单系数 + 个人解单（系统测试单）总DI值*解单系数)*系数A +个人验收测试解单总DI*系数B] *质量得分
				5.员工Cx当季此项目总得分Sdx = Sx - S1-S2… - Sn*/
				Bx=	((beforeWorkTotal+W1X)*config.getVProWork().doubleValue()- di.getProQuestDI().doubleValue()*config.getVSystestdiA().doubleValue()-di.getProCheckDI().doubleValue()*config.getVChecktestdiB().doubleValue())*Q1X/100;
				accountWork=W1X*kx.get(j).getContributevalue()/100;
				Rx=(beforeAccountWork+accountWork)/(beforeWorkTotal+W1X);
				Sx=Bx*Rx+((di.getSubQuestDI().doubleValue()*config.getVSubquestdi().doubleValue()+di.getSolveQuestDI().doubleValue()*config.getVSolvequestdi().doubleValue())*config.getVSystestdiA().doubleValue()+di.getProCheckDI().doubleValue()*config.getVChecktestdiB().doubleValue())*Q1X/100;
				Sx=Sx-beforeSa;
			}
			//减分的用当季工作量去代替S1X
			//计算每一个成员的得分
        	double Amin = (1-A/100)*config.getPrjAtprefix().doubleValue() * W1X;
        	double Bmin = (1-B/100)*config.getPrjScheduleprefix().doubleValue() *W1X;
        	//当鉴定得分不计算时，为NA
        	double Cmin = noCodeAuth ? -1:(1-C/100)*config.getPrjCodeauthprefix().doubleValue() *W1X;  
        	double Dmin = (1-D/100)*config.getPrjAuditprefix().doubleValue() *W1X;
        	String scale="";
        	if(v.getActualScale().doubleValue()!=0d) {
        		scale=v.getActualScale().toString();
        	}else {
        		scale=v.getPlanScale().toString();
        	}
        	/*存入project_summary中  加一个字段，区分是v模型项目还是其他的项目
    		（S1X）项目满分：项目当季总得分（满分）SA = （Q1X*W1X*参数【项目工作量系数】）/100，Q1X默认为100
    		（Sa_count）项目总得分：project_su_v表中的ProScored
    		（contributevalue）员工个人贡献度：
    		（Sa）员工个人得分：Sx
    			加一个字段:员工工作量	account_work*/
        	ProjectSummaryDO projectSummaryDO=new ProjectSummaryDO();
        	projectSummaryDO.setProjectName(v.getProjectName());
        	projectSummaryDO.setScale(scale);
        	projectSummaryDO.setProjectId(v.getProjectId());
        	projectSummaryDO.setAccount(kx.get(j).getPerson());
        	projectSummaryDO.setSaCount(BigDecimal.valueOf(sa));
        	projectSummaryDO.setSa(BigDecimal.valueOf(Sx));
        	projectSummaryDO.setAMin(BigDecimal.valueOf(Amin));
        	projectSummaryDO.setBMin(BigDecimal.valueOf(Bmin));
        	projectSummaryDO.setCMin(BigDecimal.valueOf(Cmin));
        	projectSummaryDO.setDMin(BigDecimal.valueOf(Dmin));
        	projectSummaryDO.setS1x(BigDecimal.valueOf(saFull));
        	projectSummaryDO.setContributevalue(BigDecimal.valueOf(contributevalue));
        	projectSummaryDO.setQuarter(quarter);
        	projectSummaryDO.setType(SystemConstant.TYPE_NORMAL);
        	projectSummaryDO.setProjectType(projectType);
        	projectSummaryDO.setAccountWork(BigDecimal.valueOf(accountWork));
        	projectSummaryDO.setCountDate(new Date());
        	projectSummaryList.add(projectSummaryDO);
		}
	
    	Map<String,List> map=new HashMap<>();
        map.put("projectSuVList", projectSuVList);
        map.put("projectSummaryList", projectSummaryList);
        map.put("projectProblemsDiList", projectProblemsDiList);
		return map;
	}
	
	
	
	
}
