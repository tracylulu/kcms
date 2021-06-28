package com.h3c.itrd.core.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.PageInfo;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.consts.SystemConstant;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.*;
import com.h3c.itrd.core.service.*;
import com.h3c.itrd.core.vo.*;

import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.DateUtil;
import com.h3c.itrd.util.RankUtil;
import com.h3c.itrd.util.SystemCommonUtil;
import com.h3c.itrd.util.UserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 我的得分模块  前端控制器
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/api/myScore")
@Api(value = "我的得分模块相关接口", tags = {"我的得分模块相关接口"})
public class MyScoreController {

	private static Logger logger = LoggerFactory.getLogger(MyScoreController.class);
	
    @Autowired
    private EmployeeListService employeeListService;
    @Autowired
    private QuarterScoreService quarterScoreService;
    @Autowired
    private NoticeListService noticeListService;
    @Autowired
    private IdmEmployeeService idmEmployeeService;
    @Autowired
    private ProjectSummaryService projectSummaryService;
    @Autowired
    private ReviewSummaryService reviewSummaryService;
    @Autowired
    private AddScoreService addScoreService;
    @Autowired
    private PlatformSummaryService platformSummaryService;
    @Autowired
    private IsoftProjectListService isoftProjectListService;
    @Autowired
    private IdmsinfoListService idmsinfoListService;
    @Autowired
    private DptconfigListService dptconfigListService;
    @Autowired
    private ReviewinfoListService reviewinfoListService;
    @Autowired
    private ReviewinfoCloudListService reviewinfoCloudListService;
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
    @Autowired
    private NoticeReadRecordService noticeReadRecordService;
    
    
    
    
    
    
    @PostMapping("/overview")
    @ApiOperation(value = "总览接口—overview")
    @OperLog(operModelName="总览接口",operContent="总览接口")
    public ResponseResult overview(@RequestBody OverviewReqVO overviewReqVO) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
    	//logger.info("方法开始时间1："+System.currentTimeMillis());
        EmployeeListDO employee = employeeListService.getEmployeeById(overviewReqVO.getId());
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("quarter", overviewReqVO.getQuarterList());
        param.put("account", employee.getAccount());
        List<OverviewResVO> overviewList = quarterScoreService.getOverviewList(param);
        //logger.info("方法开始时间2："+System.currentTimeMillis());
        //查询排名和总数的公共方法。
        for (int i = 0; i < overviewList.size(); i++) {
            Map<String, Object> param1 = new HashMap<>();
            param1.put("seconddptcode", employee.getSeconddptcode());
            param1.put("postType", employee.getPostType());
            param1.put("quarter", overviewList.get(i).getQuarter());
            List<RankResVO> ranklist = quarterScoreService.getRankForTotal(param1);
            int rank = RankUtil.rank(ranklist, employee.getAccount());
            overviewList.get(i).setRank(rank);
            int count = employeeListService.getCount(employee.getSeconddptcode(), employee.getPostType());
            overviewList.get(i).setHeadcount(count);
        }
        //logger.info("方法开始时间3："+System.currentTimeMillis());
        return ResponseResult.success(0, "查询成功", overviewList, overviewList.size());
    }

    @GetMapping("/notice")
    @ApiOperation(value = "获取通知接口-notice")
    @OperLog(operModelName="获取通知接口",operContent="获取通知接口")
    public ResponseResult notice() throws Exception{
        List<NoticeListDO> noticeList = noticeListService.getNoticeList();
        noticeList=noticeList.stream().sorted(Comparator.comparing(NoticeListDO::getId).reversed()).collect(Collectors.toList());
        logger.info("获取通知接口:"+noticeList);
        return ResponseResult.success(0, "查询成功", noticeList, noticeList.size());
    }
    
    @GetMapping("/notice_isread")
    @ApiOperation(value = "获取通知是否阅读的接口(先穿个员工工号id，等后续登录做好了就不用传了)-notice_isread")
    @OperLog(operModelName="获取通知是否阅读的接口",operContent="获取通知是否阅读的接口")
    public ResponseResult noticeIsread() throws Exception{
    	String id = UserUtils.getCurrentUserId();
        List<NoticeListDO> noticeList = noticeListService.getNoticeList();
        noticeList=noticeList.stream().sorted(Comparator.comparing(NoticeListDO::getId).reversed()).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(noticeList)) {
        	//获取最新的公告
        	Integer newNoticeId = noticeList.get(0).getId();
        	NoticeReadRecordDO record = noticeReadRecordService.getRecordByParam(id, newNoticeId);
        	if(record!=null) {
        		if(record.getIsRead()==1) {
        			return ResponseResult.success(0, "公告已阅");
        		}
        	}
        }
        logger.info("获取通知接口:"+noticeList);
        return ResponseResult.success(0, "公告未阅");
    }
    
    @GetMapping("/notice_isread_approved")
    @ApiOperation(value = "通知阅读公告审核接口-notice_isread_approved")
    @OperLog(operModelName="通知阅读公告审核接口",operContent="通知阅读公告审核接口")
    public ResponseResult noticeIsreadApproved(@RequestParam @ApiParam(name = "noticeId", value = "最新的公告id", required = true) String noticeId) throws Exception{
    	String id = UserUtils.getCurrentUserId();
    	NoticeReadRecordDO noticeReadRecordDO=new NoticeReadRecordDO();
    	noticeReadRecordDO.setEmployeeId(id);
    	noticeReadRecordDO.setNoticeId(noticeId);
    	noticeReadRecordDO.setIsRead(1);
    	noticeReadRecordDO.setReaddate(new Date());
    	int row=noticeReadRecordService.add(noticeReadRecordDO);
    	if(row>0) {
    		return ResponseResult.success(0, "最新公告阅读审核成功");
    	}else {
    		return ResponseResult.fail(1, "最新公告阅读审核失败");
    	}
    }

    @GetMapping("/totalScore")
    @ApiOperation(value = "当季得分(软件开发)列表查询接口-total_score")
    @OperLog(operModelName="当季得分(软件开发)列表查询接口",operContent="当季得分(软件开发)列表查询接口")
    public ResponseResult totalScore(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                     @RequestParam @ApiParam(name = "quarter", value = "季度", required = true) String quarter) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
    	//logger.info("方法开始时间1："+System.currentTimeMillis());
        quarter = (quarter != null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        List<Object> develop = new ArrayList<>();
        List<Object> maintain = new ArrayList<>();

        Map<String, Object> param = new HashMap<>();
        param.put("account", employee.getAccount());
        param.put("quarter", quarter);
        if ("软件开发".equals(employee.getPostType())) {
            //查询开发得分
        	double totalCmin=0;
            List<ProjectSummaryVO> softDevList = projectSummaryService.getSoftDevList(param);
            List<ProjectSummaryDO> proList = projectSummaryService.getProListByAccountAndQuarter(employee.getAccount(), quarter);
            List<ProjectSummaryDO> collect = proList.stream().filter(o->o.getCMin().doubleValue()!=-1).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(collect)){
            	totalCmin=-1;
            }else {
            	for (int i = 0; i < collect.size(); i++) {
            		totalCmin+=collect.get(i).getCMin().doubleValue();
				}
            }
            softDevList.get(0).setTotalCmin(totalCmin);
            develop.addAll(softDevList);
            //查询维护得分
            List<IdmEmployeeVO> softMaintainList = idmEmployeeService.getSoftMaintainList(param);
            maintain.addAll(softMaintainList);
        }
        //logger.info("方法开始时间2："+System.currentTimeMillis());
        //查询审计得分
        List<ReviewSummaryDO> reviewList = reviewSummaryService.getReviewListByAccountAndQuarter(employee.getAccount(), quarter);
        //查询额外加减分
        List<AddScoreDO> otherList = addScoreService.getListBy2Param(employee.getId(), quarter);
        List<Map> otherScoreList = new ArrayList<>();
        Map<String, Object> otherScoreMap = new HashMap();
        double othercount = 0;
        double add = 0;
        double minus = 0;
        for (int i = 0; i < otherList.size(); i++) {
            if (otherList.get(i).getScore().doubleValue() > 0) {
                othercount = otherList.get(i).getScore().doubleValue() + othercount;
                add = otherList.get(i).getScore().doubleValue() + add;
            } else {
                othercount = otherList.get(i).getScore().doubleValue() + othercount;
                minus = otherList.get(i).getScore().doubleValue() + minus;
            }
        }
        otherScoreMap.put("othercount", othercount);
        otherScoreMap.put("add", add);
        otherScoreMap.put("minus", minus);
        otherScoreList.add(otherScoreMap);
        //logger.info("方法开始时间3："+System.currentTimeMillis());
        //查询平台贡献得分
        List<PlatformSummaryDO> platformList = platformSummaryService.getPlatformListByAccountAndQuarter(employee.getAccount(), quarter);

        QuarterScoreDO quarterScore = quarterScoreService.getByAccountAndQuarter(employee.getAccount(), quarter);
        Map<String, Object> rankParam = new HashMap<>();
        rankParam.put("seconddptcode", employee.getSeconddptcode());
        rankParam.put("postType", employee.getPostType());
        rankParam.put("quarter", quarter);
        List<RankListResVO> bestList = quarterScoreService.getRankList(rankParam);
        //查询排名和总数的公共方法
        Map<String, Object> param1 = new HashMap<>();
        param1.put("seconddptcode", employee.getSeconddptcode());
        param1.put("postType", employee.getPostType());
        param1.put("quarter", quarter);
        List<RankResVO> ranklist = quarterScoreService.getRankForTotal(param1);
        int rank = RankUtil.rank(ranklist, employee.getAccount());
        int count = employeeListService.getCount(employee.getSeconddptcode(), employee.getPostType());
        //logger.info("方法开始时间4："+System.currentTimeMillis());
        Map<String, Object> map = new HashMap<>();
        map.put("develop", develop);
        map.put("maintain", maintain);
        map.put("other", otherScoreList);
        map.put("review", reviewList);
        map.put("platform", platformList);
        map.put("total", quarterScore.getTotalScore());
        map.put("best", bestList);
        map.put("rank", rank);
        map.put("headcount", count);
        map.put("status", true);
        map.put("errorInfo", "");
        return ResponseResult.success(0, "查询成功", map, map.size());
    }

    @GetMapping("/empProjectList")
    @ApiOperation(value = "个人开发得分(软件开发)查询接口-emp_project_list")
    @OperLog(operModelName="个人开发得分(软件开发)查询接口",operContent="个人开发得分(软件开发)查询接口")
    public ResponseResult empProjectList(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                         @RequestParam @ApiParam(name = "quarter", value = "季度", required = true) String quarter) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
        quarter = (quarter != null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        List<Object> list = new ArrayList<>();
        if (employee.getPostType().equals("软件开发")) {
            List<ProjectSummaryDO> proList = projectSummaryService.getProListByAccountAndQuarter(employee.getAccount(), quarter);
            list.addAll(proList);
        }

        //查询排名和总数的公共方法。
        Map<String, Object> param1 = new HashMap<>();
        param1.put("seconddptcode", employee.getSeconddptcode());
        param1.put("postType", employee.getPostType());
        param1.put("quarter", quarter);
        List<RankResVO> ranklist = quarterScoreService.getRankForDev(param1);
        int rank = RankUtil.rank(ranklist, employee.getAccount());
        logger.info("开发得分排名为："+rank);
        int count = employeeListService.getCount(employee.getSeconddptcode(), employee.getPostType());

        Map<String, Object> map = new HashMap<>();
        map.put("dataList", list);
        map.put("rank", rank);
        map.put("headcount", count);
        map.put("status", true);
        map.put("errorInfo", "");
        return ResponseResult.success(0, "查询成功", map, map.size());
    }

    @GetMapping("/empProjectDetail")
    @ApiOperation(value = "开发得分项目详情接口-emp_project_detail")
    @OperLog(operModelName="开发得分项目详情接口",operContent="开发得分项目详情接口")
    public ResponseResult empProjectDetail(@RequestParam @ApiParam(name = "id", value = "项目id", required = true) String id,
                                           @RequestParam @ApiParam(name = "type", value = "类型", required = true) Integer type) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
        type = (type != null) ? type : 1;
        List<Object> list = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        switch (type) {
            case 1:
                List<IsoftProjectListDO> isoftList = isoftProjectListService.getIsoftListByParam(param);
                for (int i = 0; i < isoftList.size(); i++) {
                    if (StringUtils.isBlank(isoftList.get(i).getAppraiseID())) {
                        isoftList.get(i).setAppraiseID("NA");
                    }
                    if (StringUtils.isNotBlank(isoftList.get(i).getAtdi()) && "-0.01".equals(isoftList.get(i).getAtdi())) {
                        isoftList.get(i).setAtdi("NA");
                    }
                    if (StringUtils.isNotBlank(isoftList.get(i).getScheduleForAll()) && "-0.010".equals(isoftList.get(i).getScheduleForAll())) {
                        isoftList.get(i).setScheduleForAll("NA");
                    }
                }
                list.addAll(isoftList);
                break;
            default:
                return ResponseResult.fail(1, "查询失败，type参数错误");
        }

        return ResponseResult.success(0, "查询成功", list, list.size());
    }

    @GetMapping("/empDefend")
    @ApiOperation(value = "维护得分(软件开发)查询接口-emp_defend")
    @OperLog(operModelName="维护得分(软件开发)查询接口",operContent="维护得分(软件开发)查询接口")
    public ResponseResult empDefend(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                    @RequestParam @ApiParam(name = "quarter", value = "季度", required = true) String quarter) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
        quarter = (quarter != null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        List<Object> list = new ArrayList<>();
        if (employee.getPostType().equals("软件开发")) {
            List<IdmEmployeeDO> softList = idmEmployeeService.getSoftListByAccountAndQuarter(employee.getAccount(), quarter);
            list.addAll(softList);
        }

        //查询排名和总数的公共方法。
        Map<String, Object> param1 = new HashMap<>();
        param1.put("seconddptcode", employee.getSeconddptcode());
        param1.put("postType", employee.getPostType());
        param1.put("quarter", quarter);
        List<RankResVO> ranklist = quarterScoreService.getRankForMaintain(param1);
        int rank = RankUtil.rank(ranklist, employee.getAccount());
        logger.info("维护得分排名为："+rank);
        int count = employeeListService.getCount(employee.getSeconddptcode(), employee.getPostType());

        Map<String, Object> map = new HashMap<>();
        map.put("dataList", list);
        map.put("rank", rank);
        map.put("headcount", count);
        map.put("status", true);
        map.put("errorInfo", "");
        return ResponseResult.success(0, "查询成功", map, map.size());
    }

    @PostMapping("/empDefendList")
    @ApiOperation(value = "维护得分项目详情接口-emp_defend_list")
    @OperLog(operModelName="维护得分项目详情接口",operContent="维护得分项目详情接口")
    public ResponseResult empDefendList(@RequestBody EmpDefendListReqVO empDefendListReqVO) throws Exception{
        //logger.info("方法开始时间："+System.currentTimeMillis());
        String quarter=empDefendListReqVO.getQuarter();
        String id = empDefendListReqVO.getId();
        Integer type = empDefendListReqVO.getType();
        //分页参数处理
        int pageNum=empDefendListReqVO.getPageNum();
        int pageSize=empDefendListReqVO.getPageSize();
        if(pageNum<=0) {
        	pageNum=1;
        }
        if(pageSize<=0) {
        	pageSize=20;
        }
        List<DptconfigListDO> dptconfigList = dptconfigListService.getList(new HashMap<>());
        Map<String, DptconfigListDO> dptconfigMap = dptconfigList.stream().filter(t->StringUtils.isNotBlank(t.getDptcode())).
                collect(Collectors.toMap(DptconfigListDO::getDptcode, dptconfigListDO -> dptconfigListDO));
        Map<String, Object> employeeParam = new HashMap<>();
        employeeParam.put("postType",SystemConstant.SOFTWARE_DEV);
        List<EmployeeListDO> employeeList = employeeListService.getList(employeeParam);
        Map<String, EmployeeListDO> employeeMap = employeeList.stream().filter(t->StringUtils.isNotBlank(t.getAccount())).
                collect(Collectors.toMap(EmployeeListDO::getAccount, employeeListDO -> employeeListDO));
        quarter = (quarter != null) ? quarter : "2020Q2";
        type = (type != null) ? type : 1;
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start=timeSpaceMap.get("start").toString();
        String end=timeSpaceMap.get("end").toString();
        Map<String, Object> param1 = new HashMap<>();
        param1.put("account",employee.getAccount());
        param1.put("start",start);
        param1.put("end",end);
        //普通单提单1
        if(type==1){
        	List<IdmsResVO> list1 =new ArrayList<>();
        	//logger.info("方法开始时间111："+System.currentTimeMillis());
            List<IdmsResVO> idmsPT = idmsinfoListService.getIdmsPT1(param1);
            //logger.info("方法结束时间111："+System.currentTimeMillis());
            for (int i = 0; i <idmsPT.size() ; i++) {
                boolean b = this.checkIdms(idmsPT.get(i),dptconfigMap,employeeMap);
                if(b){
                    list1.add(idmsPT.get(i));
                }
            }
            List<IdmsResVO> pageList = list1.stream().sorted().skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            return ResponseResult.success(0, "查询成功", pageNum, list1.size(),null,pageList);
        }
        //普通单修改2
        if(type==2){
        	List<IdmsResVO> list1 =new ArrayList<>();
        	//logger.info("方法开始时间111："+System.currentTimeMillis());
            List<IdmsResVO> idmsPT = idmsinfoListService.getIdmsPT2(param1);
            //logger.info("方法结束时间111："+System.currentTimeMillis());
            for (int i = 0; i <idmsPT.size() ; i++) {
                boolean b = this.checkIdms(idmsPT.get(i),dptconfigMap,employeeMap);
                if(b){
                    list1.add(idmsPT.get(i));
                }
            }
            List<IdmsResVO> pageList = list1.stream().sorted().skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            return ResponseResult.success(0, "查询成功", pageNum, list1.size(),null,pageList);
        }    
        //普通单定位3
        if(type==3){
        	List<IdmsResVO> list1 =new ArrayList<>();
        	//logger.info("方法开始时间111："+System.currentTimeMillis());
            List<IdmsResVO> idmsPT = idmsinfoListService.getIdmsPT3(param1);
            //logger.info("方法结束时间111："+System.currentTimeMillis());
            for (int i = 0; i <idmsPT.size() ; i++) {
                boolean b = this.checkIdms(idmsPT.get(i),dptconfigMap,employeeMap);
                if(b){
                    list1.add(idmsPT.get(i));
                }
            }
            List<IdmsResVO> pageList = list1.stream().sorted().skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            return ResponseResult.success(0, "查询成功", pageNum, list1.size(),null,pageList);
        }    
        //普通单审核4
        if(type==4){
        	List<IdmsResVO> list1 =new ArrayList<>();
        	//logger.info("方法开始时间111："+System.currentTimeMillis());
            List<IdmsResVO> idmsPT = idmsinfoListService.getIdmsPT4(param1);
            //logger.info("方法结束时间111："+System.currentTimeMillis());
            for (int i = 0; i <idmsPT.size() ; i++) {
                boolean b = this.checkIdms(idmsPT.get(i),dptconfigMap,employeeMap);
                if(b){
                    list1.add(idmsPT.get(i));
                }
            }
            List<IdmsResVO> pageList = list1.stream().sorted().skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            return ResponseResult.success(0, "查询成功", pageNum, list1.size(),null,pageList);
        }
        //同步单修改5
        if(type==5){
        	List<IdmsResVO> list2 =new ArrayList<>();
            List<IdmsResVO> idmsTB = idmsinfoListService.getIdmsTB1(param1);
            for (int j = 0; j <idmsTB.size() ; j++) {
                boolean b = this.checkIdms(idmsTB.get(j),dptconfigMap,employeeMap);
                if(b){
                    list2.add(idmsTB.get(j));
                }
            }
            List<IdmsResVO> pageList = list2.stream().sorted().skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            return ResponseResult.success(0, "查询成功", pageNum, list2.size(),null,pageList);
        }
        //同步单审核6
        if(type==6){
        	List<IdmsResVO> list2 =new ArrayList<>();
            List<IdmsResVO> idmsTB = idmsinfoListService.getIdmsTB2(param1);
            for (int j = 0; j <idmsTB.size() ; j++) {
                boolean b = this.checkIdms(idmsTB.get(j),dptconfigMap,employeeMap);
                if(b){
                    list2.add(idmsTB.get(j));
                }
            }
            List<IdmsResVO> pageList = list2.stream().sorted().skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            return ResponseResult.success(0, "查询成功", pageNum, list2.size(),null,pageList);
        }
        //logger.info("方法结束时间："+System.currentTimeMillis());
        return null;
    }

    public boolean checkIdms(IdmsResVO idmsResVO,Map<String, DptconfigListDO> dptconfigMap,Map<String, EmployeeListDO> employeeMap) throws Exception{
    	//优化下面的代码，提高效率，避免for循环中还直接查询数据库，
    	EmployeeListDO employee = employeeMap.get(idmsResVO.getNameB().toLowerCase());
    	if (employee == null) {
    		employee = employeeMap.get(idmsResVO.getNameC().toLowerCase());
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
                if(idmsResVO.getResume().contains(split[i])){
                    return true;
                }
            }
        }
        //无效问题单简述字段过滤内容
        //无效问题单简述字段过滤内容：简述中带有“系统测试,验收,规格列表,public,PUBLIC,Public,说明书,第一轮,第二轮,第三轮,手册”的识别为无效单
        if (StringUtils.isNotBlank(config.getBugValidprefix())) {
            String[] split2 = config.getBugValidprefix().split(",");
            for (int i = 0; i < split2.length; i++) {
                if (idmsResVO.getResume().contains(split2[i])) {
                    logger.info("内容：" + idmsResVO.getNum()+"人员："+employee.getAccount());
                    return false;
                }
            }
        }
        //无效问题单基线子版本过滤条件(包含temp等就是无效的)
        if (StringUtils.isNotBlank(config.getBugValidbaseline())) {
            String[] split3 = config.getBugValidbaseline().split(",");
            for (int i = 0; i < split3.length; i++) {
                if (idmsResVO.getBaseline().contains(split3[i].trim()) || idmsResVO.getBaseline1().contains(split3[i].trim())) {
                    logger.info("条件：" + idmsResVO.getNum()+"人员："+employee.getAccount());
                    return false;
                }
            }
        }
        return true;
    }

    @GetMapping("/empAudit")
    @ApiOperation(value = "评审得分查询接口-emp_audit")
    @OperLog(operModelName="评审得分查询接口",operContent="评审得分查询接口")
    public ResponseResult empAudit(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                   @RequestParam @ApiParam(name = "quarter", value = "季度", required = true) String quarter) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
        quarter = (quarter!=null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        List<ReviewSummaryDO> reviewList = reviewSummaryService.getReviewListByAccountAndQuarter(employee.getAccount(), quarter);

        //查询排名和总数的公共方法
        Map<String, Object> param1 = new HashMap<>();
        param1.put("seconddptcode", employee.getSeconddptcode());
        param1.put("postType", employee.getPostType());
        param1.put("quarter", quarter);
        List<RankResVO> ranklist = quarterScoreService.getRankForAudit(param1);
        int rank = RankUtil.rank(ranklist, employee.getAccount());
        logger.info("评审得分排名为："+rank);
        int count = employeeListService.getCount(employee.getSeconddptcode(), employee.getPostType());

        Map<String, Object> map = new HashMap<>();
        map.put("dataList",reviewList);
        map.put("rank",rank);
        map.put("headcount",count);
        map.put("status",true);
        map.put("errorInfo","");
        return ResponseResult.success(0, "查询成功",map,map.size());
    }

    @PostMapping("/empAuditList")
    @ApiOperation(value = "评审得分详情接口-emp_audit_list")
    @OperLog(operModelName="评审得分详情接口",operContent="评审得分详情接口")
    public ResponseResult empAuditList(@RequestBody EmpAuditListReqVO empAuditListReqVO) throws Exception{
    	//logger.info("方法开始时间1："+System.currentTimeMillis());
    	//发现问题项传1，未发现问题项传0
    	int type = empAuditListReqVO.getType();
    	String id = empAuditListReqVO.getId();	
    	String quarter = empAuditListReqVO.getQuarter();
    	//分页参数处理
        int pageNum=empAuditListReqVO.getPageNum();
        int pageSize=empAuditListReqVO.getPageSize();
        if(pageNum<=0) {
        	pageNum=1;
        }
        if(pageSize<=0) {
        	pageSize=20;
        }
        quarter = (quarter!=null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start=timeSpaceMap.get("start").toString();
        String end=timeSpaceMap.get("end").toString();
        Map<String, Object> param = new HashMap<>();
        param.put("account",employee.getAccount());
        param.put("start",start);
        param.put("end",end);
        List<ReviewinfoListResVO> reviewinfoList = reviewinfoListService.getReviewinfoListByParam(param);
        //logger.info("方法开始时间2："+System.currentTimeMillis());
        if (employee.getPostType().equals("软件开发") || employee.getPostType().equals("软件测试")){
            //获取二级部门配置参数
            DptconfigListDO dptconfig = dptconfigListService.getDptconfigListByDptcode(employee.getSeconddptcode());
            double reviewDIprefix=1;
            double reviewCNTprefix=1;
            if(dptconfig!=null){
                reviewDIprefix= dptconfig.getReviewDiprefix().doubleValue();
                reviewCNTprefix=dptconfig.getReviewCntprefix().doubleValue();
            }
            for (int i = 0; i < reviewinfoList.size(); i++) {
                double v = reviewinfoList.get(i).getTotaldi().doubleValue() * reviewDIprefix + reviewinfoList.get(i).getTotalnum() * reviewCNTprefix;
                reviewinfoList.get(i).setReviewScore(SystemCommonUtil.doubleFormat2(v));
            }
            Map<String, Object> param1 = new HashMap<>();
            param1.put("account",employee.getDomainid());
            param1.put("start",start);
            param1.put("end",end);
            int reviewCount = reviewinfoCloudListService.getReviewCountByParam(param1);
            //logger.info("方法开始时间3："+System.currentTimeMillis());
            if(reviewCount>0){
                ReviewinfoListResVO reviewinfoListResVO=new ReviewinfoListResVO();
                reviewinfoListResVO.setSerialNumber("newdjdam系统得分");
                reviewinfoListResVO.setReviewScore(SystemCommonUtil.doubleFormat2(reviewDIprefix*reviewCount+reviewCNTprefix*reviewCount));
                reviewinfoListResVO.setProblemNumber(reviewCount);
                reviewinfoList.add(reviewinfoListResVO);
            }
        }
        if(type==1) {
        	//发现问题评审项，也就是ProblemNumber不为0的列表
        	reviewinfoList=reviewinfoList.stream().filter(o->o.getProblemNumber()!=0).collect(Collectors.toList());
        }else {
        	reviewinfoList=reviewinfoList.stream().filter(o->o.getProblemNumber()==0).collect(Collectors.toList());
        }
        List<ReviewinfoListResVO> pageList = reviewinfoList.stream().sorted().skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        return ResponseResult.success(0, "查询成功", pageNum, reviewinfoList.size(),null,pageList);
    }

    @GetMapping("/addScoreList")
    @ApiOperation(value = "关键事件分列表汇总列表接口-add_score_list")
    @OperLog(operModelName="关键事件分列表汇总列表接口",operContent="关键事件分列表汇总列表接口")
    public ResponseResult addScoreList(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                       @RequestParam @ApiParam(name = "quarter", value = "季度", required = true) String quarter) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
        quarter = (quarter!=null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userId",id);
        param.put("quarter",quarter);
        List<AddScoreResVO> list = addScoreService.getAddScoreListByParam(param);
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getDescription().equals("个人申请加分")){
                list.get(i).setType("个人补录");
            }else{
                list.get(i).setType("部门录入");
            }
        }
        //查询排名和总数的公共方法
        Map<String, Object> param1 = new HashMap<>();
        param1.put("seconddptcode", employee.getSeconddptcode());
        param1.put("postType", employee.getPostType());
        param1.put("quarter", quarter);
        List<RankResVO> ranklist = quarterScoreService.getRankForAdd(param1);
        int rank = RankUtil.rank(ranklist, employee.getAccount());
        logger.info("关键事件得分排名为："+rank);
        int count = employeeListService.getCount(employee.getSeconddptcode(), employee.getPostType());

        Map<String, Object> map = new HashMap<>();
        map.put("dataList",list);
        map.put("rank",rank);
        map.put("headcount",count);
        map.put("status",true);
        map.put("errorInfo","");
        return ResponseResult.success(0, "查询成功",map,map.size());
    }

    @PostMapping("/addScoreDetail")
    @ApiOperation(value = "关键事件得分详情接口-add_score_detail")
    @OperLog(operModelName="关键事件得分详情接口",operContent="关键事件得分详情接口")
    public ResponseResult addScoreDetail(@RequestBody AddScoreDetailReqVO addScoreDetailReqVO) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
    	String id = addScoreDetailReqVO.getId();
    	//String project = addScoreDetailReqVO.getProject();
    	String quarter = addScoreDetailReqVO.getQuarter();
    	//分页参数处理
        int pageNum=addScoreDetailReqVO.getPageNum();
        int pageSize=addScoreDetailReqVO.getPageSize();
        if(pageNum<=0) {
        	pageNum=1;
        }
        if(pageSize<=0) {
        	pageSize=20;
        }
        quarter = (quarter!=null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userId",employee.getId());
        param.put("quarter",quarter);
        List<AddScoreResVO> addScoreDetailList1 = addScoreService.getAddScoreDetailByParam(param);
        //List<AddScoreDO> addScoreList1 = addScoreService.getListBy3Param(employee.getId(), quarter);
        int total=addScoreDetailList1.size();
        com.github.pagehelper.page.PageMethod.startPage(pageNum,pageSize);
        List<AddScoreResVO> addScoreDetailList = addScoreService.getAddScoreDetailByParam(param);
        for (int i = 0; i <addScoreDetailList.size() ; i++) {
            if(addScoreDetailList.get(i).getDescription().equals("个人申请加分")){
            	addScoreDetailList.get(i).setType("个人补录");
            }else{
            	addScoreDetailList.get(i).setType("部门录入");
            }
        }
        //List<AddScoreDO> addScoreList = addScoreService.getListBy3Param(employee.getId(), quarter);
        addScoreDetailList=addScoreDetailList.stream().sorted(Comparator.comparing(AddScoreResVO::getScoreType).reversed()).collect(Collectors.toList());
        PageInfo<AddScoreResVO> pageInfo = new PageInfo<>(addScoreDetailList);
    	return ResponseResult.success(0, "查询成功", pageNum, total,null,pageInfo.getList());
    }

    @GetMapping("/platform")
    @ApiOperation(value = "平台贡献得分查询接口-platform")
    @OperLog(operModelName="平台贡献得分查询接口",operContent="平台贡献得分查询接口")
    public ResponseResult platform(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                   @RequestParam @ApiParam(name = "quarter", value = "季度", required = true) String quarter) throws Exception{
        //校验身份权限
    	//验证身份
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "没有权限访问");
    	}
        quarter = (quarter!=null) ? quarter : "2020Q2";
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        List<PlatformSummaryDO> platformList = platformSummaryService.getPlatformListByAccountAndQuarter(employee.getAccount(), quarter);

        //查询排名和总数的公共方法
        Map<String, Object> param1 = new HashMap<>();
        param1.put("seconddptcode", employee.getSeconddptcode());
        param1.put("postType", employee.getPostType());
        param1.put("quarter", quarter);
        List<RankResVO> ranklist = quarterScoreService.getRankForPlat(param1);
        int rank = RankUtil.rank(ranklist, employee.getAccount());
        logger.info("平台贡献得分排名为："+rank);
        int count = employeeListService.getCount(employee.getSeconddptcode(), employee.getPostType());

        Map<String, Object> map = new HashMap<>();
        map.put("dataList",platformList);
        map.put("rank",rank);
        map.put("headcount",count);
        map.put("status",true);
        map.put("errorInfo","");
        return ResponseResult.success(0, "查询成功",map,map.size());
    }

    @PostMapping("/platformList")
    @ApiOperation(value = "平台贡献得分详情接口-platform_list")
    @OperLog(operModelName="平台贡献得分详情接口",operContent="平台贡献得分详情接口")
    public ResponseResult platformList(@RequestBody PlatformListReqVO platformListReqVO) throws Exception{
    	String id = platformListReqVO.getId();
    	String quarter = platformListReqVO.getQuarter();
    	Integer type = platformListReqVO.getType();
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        Map timeSpaceMap = DateTimeUtils.getTimeSpace(quarter);
        String start=timeSpaceMap.get("start").toString();
        String end=timeSpaceMap.get("end").toString();
        if(type==1) {
        	List<ExperienceinfoListDO> experienceinfoList = experienceinfoListService.getExperienceinfoList(employee.getDomainid(), start, end);
            return ResponseResult.success(0, "查询成功", experienceinfoList,experienceinfoList.size());
        }else if(type==2) {
        	List<SharedocinfoListDO> sharedocinfoList = sharedocinfoListService.getSharedocinfoList(employee.getAccount(), start, end);
            return ResponseResult.success(0, "查询成功", sharedocinfoList,sharedocinfoList.size());
        }else if(type==3) {
        	List<SpecinfoListDO> specinfoList = specinfoListService.getSpecinfoList(employee.getAccount(), start, end);
            return ResponseResult.success(0, "查询成功", specinfoList,specinfoList.size());
        }else if(type==4) {
        	List<PatentinfoListDO> patentinfoList = patentinfoListService.getPatentinfoList(employee.getId(), start, end);
            return ResponseResult.success(0, "查询成功", patentinfoList,patentinfoList.size());
        }else if(type==5) {
        	List<CbbinfoListDO> cbbinfoList = cbbinfoListService.getCbbinfoList(employee.getAccount(), start, end);
            return ResponseResult.success(0, "查询成功", cbbinfoList,cbbinfoList.size());
        }else if(type==6) {
        	List<YnfxinfoListDO> ynfxinfoList = ynfxinfoListService.getYnfxinfoList(employee.getAccount(), start, end);
            return ResponseResult.success(0, "查询成功", ynfxinfoList,ynfxinfoList.size());
        }else {
        	return ResponseResult.fail(1, "类型参数错误");
        }
    }

    private Boolean checkPerson() {
    	String currentUserId = UserUtils.getCurrentUserId();
    	//查询身份信息,如果不通过就不能访问接口
    	EmployeeListDO employeeListDO = employeeListService.getEmployeeById(currentUserId);
    	if(employeeListDO==null) {
    		return false;
    	}
		return true;
	}



}
