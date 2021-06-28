package com.h3c.itrd.core.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.AddScoreAuditDO;
import com.h3c.itrd.core.entity.AddScoreDO;
import com.h3c.itrd.core.entity.AddScoreSelectDO;
import com.h3c.itrd.core.entity.DptmanagerListDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.service.*;
import com.h3c.itrd.core.vo.AddScoreAuditListReqVO;
import com.h3c.itrd.core.vo.AddScoreAuditListResVO;
import com.h3c.itrd.core.vo.AddScoreAuditResVO;
import com.h3c.itrd.core.vo.AddScoreReplyListVO;
import com.h3c.itrd.core.vo.AddScoreReplyReqVO;
import com.h3c.itrd.core.vo.AddScoreResVO;
import com.h3c.itrd.core.vo.RankResVO;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.DateUtil;
import com.h3c.itrd.util.RankUtil;
import com.h3c.itrd.util.UserUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  申请补录模块  前端控制器
 * </p>
 *
 * @author cYS1425
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/api/apply")
@Api(value = "申请补录模块相关接口", tags = {"申请补录模块相关接口"})
public class ApplyController {
	private static Logger logger = LoggerFactory.getLogger(ApplyController.class);
	
    @Autowired
    private EmployeeListService employeeListService;
    @Autowired
    private AddScoreAuditService addScoreAuditService;
    @Autowired
    private AddScoreSelectService addScoreSelectService;
    @Autowired
    private DptmanagerListService dptmanagerListService;
    @Autowired
    private AddScoreService addScoreService;
    @Autowired
    private QuarterScoreService quarterScoreService;
    
    
    

    @GetMapping("/addScoreReplyList")
    @ApiOperation(value = "申请记录列表接口-add_score_reply_list")
    @OperLog(operModelName="申请记录列表接口",operContent="申请记录列表接口")
    public ResponseResult addScoreReplyList(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                            @RequestParam @ApiParam(name = "quarter", value = "季度", required = true) String quarter) throws Exception{

        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("account",employee.getAccount());
        if(!quarter.equals("所有")){
            param.put("quarter",quarter);
        }
        List<AddScoreReplyListVO> list = addScoreAuditService.addScoreReplyList(param);

        return ResponseResult.success(0, "查询成功",list,list.size());
    }
	   
    @GetMapping("/urging")
    @ApiOperation(value = "催签发邮件接口（催签发邮件功能暂时不做）-urging")
    @OperLog(operModelName="催签发邮件接口",operContent="催签发邮件接口")
    public ResponseResult urging(@RequestParam @ApiParam(name = "id", value = "主键id", required = true) Integer id) {

        AddScoreAuditDO addScoreAuditDO = addScoreAuditService.getById(id);
        EmployeeListDO employee = employeeListService.getEmployeeByAccount(addScoreAuditDO.getUserAccount());
        //发送邮件功能
        //todo
        return ResponseResult.success(0, "发送邮件成功");
    }

    @GetMapping("/addScoreSelect")
    @ApiOperation(value = "补录申请中类别下拉列表接口-add_score_select")
    @OperLog(operModelName="补录申请中类别下拉列表接口",operContent="补录申请中类别下拉列表接口")
    public ResponseResult addScoreSelect(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                         @RequestParam @ApiParam(name = "seconddptcode", value = "二级部门编码", required = true) String seconddptcode,
                                         @RequestParam @ApiParam(name = "thirddptcode", value = "三级部门编码", required = true) String thirddptcode) throws Exception{
        //一级部门进来没有二级部门code。直接返回空值
    	if(seconddptcode.equals("所有")) {
        	Map<String, Object> map=new HashMap<>();
            map.put("data","");
            map.put("applyAccount","");
            return ResponseResult.success(0, "查询成功",map,map.size());
        }
    	Map<String, Object> param = new HashMap<>();
        param.put("thirddptcode",thirddptcode);
        param.put("seconddptcode",seconddptcode);
        List<AddScoreSelectDO> addScoreSelectList = addScoreSelectService.addScoreSelect(param);
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        List<DptmanagerListDO> managerList = dptmanagerListService.getDptManagerBydptcode(employee.getCurrentdptcode());
        if (managerList.isEmpty()) {
        	managerList = dptmanagerListService.getDptManagerBydptcode(employee.getThirddptcode());
            if (managerList.isEmpty()) {
            	managerList = dptmanagerListService.getDptManagerBydptcode(employee.getSeconddptcode());
            }
        }
        //容错，小马姐登录肯定查不到人
        if(managerList.isEmpty()) {
        	Map<String, Object> map=new HashMap<>();
            map.put("data","");
            map.put("applyAccount","");
            return ResponseResult.success(0, "查询成功",map,map.size());
        }
        List<EmployeeListDO> list=new ArrayList<>();
        for (int i = 0; i < managerList.size(); i++) {
        	EmployeeListDO employee1 = employeeListService.getEmployeeById(managerList.get(i).getId());
        	list.add(employee1);
		}
        List<String> list2 = list.stream().map(EmployeeListDO::getAccount).collect(Collectors.toList());
        Map<String, Object> map=new HashMap<>();
        map.put("data",addScoreSelectList);
        map.put("applyAccount",list2);
        return ResponseResult.success(0, "查询成功",map,map.size());
    }

    @PostMapping("/addScoreReply")
    @ApiOperation(value = "提交申请按钮接口—add_score_reply")
    @OperLog(operModelName="提交申请按钮接口",operContent="提交申请按钮接口")
    public ResponseResult addScoreReply(@RequestBody AddScoreReplyReqVO addScoreReplyReqVO) throws Exception{
        EmployeeListDO employee = employeeListService.getEmployeeById(addScoreReplyReqVO.getId());
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        if (addScoreReplyReqVO.getAuditAccount().equals(addScoreReplyReqVO.getCopyAccount())) {
            return ResponseResult.fail(1, "审核人与申请人不能是同一人");
        }
        for (int i = 0; i < addScoreReplyReqVO.getAddScoreId().size(); i++) {
        	AddScoreAuditDO addScoreAuditDO=new AddScoreAuditDO();
        	addScoreAuditDO.setUserAccount(employee.getAccount());
        	addScoreAuditDO.setAuditAccount(addScoreReplyReqVO.getAuditAccount());
        	//抄送人可以填多个
        	List<String> copyAccount = addScoreReplyReqVO.getCopyAccount();
        	String result = copyAccount.stream().map(String::valueOf).collect(Collectors.joining(","));
        	addScoreAuditDO.setCopyAccount(result);
        	addScoreAuditDO.setAddScoreId(addScoreReplyReqVO.getAddScoreId().get(i));
        	addScoreAuditDO.setRemark(addScoreReplyReqVO.getRemark().get(i));
        	addScoreAuditDO.setScore(BigDecimal.valueOf(0d));
        	addScoreAuditDO.setStatus(0);
        	addScoreAuditDO.setQuarter(addScoreReplyReqVO.getQuarter());
        	addScoreAuditDO.setReplyDate(new Date());
            int row = addScoreAuditService.add(addScoreAuditDO);
            if(row<=0) {
            	return ResponseResult.fail(1, "新增失败");
            }
        }
        return ResponseResult.success(0, "新增成功");
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

    @GetMapping("/addScoreAuditList")
    @ApiOperation(value = "补录审核页面展示列表接口-add_score_audit_list")
    @OperLog(operModelName="补录审核页面展示列表接口",operContent="补录审核页面展示列表接口")
    public ResponseResult addScoreAuditList(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id,
                                       @RequestParam @ApiParam(name = "status", value = "状态", required = true) Integer status) throws Exception{
        EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("account",employee.getAccount());
        param.put("status",status);
        List<AddScoreAuditListResVO> list = addScoreAuditService.addScoreAuditListByStatus(param);
        return ResponseResult.success(0, "查询成功",list,list.size());
    }

    @PostMapping("/addScoreAudit")
    @ApiOperation(value = "补录审核页面个人关键事件得分审核功能接口-add_score_audit")
    @OperLog(operModelName="补录审核页面个人关键事件得分审核功能接口",operContent="补录审核页面个人关键事件得分审核功能接口")
    public ResponseResult addScoreAudit(@RequestBody AddScoreAuditListReqVO addScoreAuditListReqVO) throws Exception{
    	String id = addScoreAuditListReqVO.getId();
    	List<Integer> itemIdList = addScoreAuditListReqVO.getItemIdList();
    	List<String> auditStatusList = addScoreAuditListReqVO.getAuditStatusList();
    	EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "个人信息查询失败");
        }
        for (int i = 0; i < itemIdList.size(); i++) {
        	Map<String, Object> param = new HashMap<>();
            param.put("id",itemIdList.get(i));
        	AddScoreAuditResVO r = addScoreAuditService.getAddScoreAuditById(param);
        	//防止重复审批---原来php的代码，感觉没啥用，先注释掉
//            if ($v['audit_status'] != 0){
//                continue;
//            }
        	if(auditStatusList.get(i).equals("驳回")) {
        		AddScoreAuditDO addScoreAuditDO=new AddScoreAuditDO();
        		addScoreAuditDO.setId(itemIdList.get(i));
            	addScoreAuditDO.setAuditDate(new Date());
            	addScoreAuditDO.setStatus(2);
            	addScoreAuditDO.setAuditStatus(auditStatusList.get(i));
            	addScoreAuditDO.setScore(BigDecimal.valueOf(0d));
                int row = addScoreAuditService.updateAuditById(addScoreAuditDO);
                if(row<=0) {
                	return ResponseResult.fail(1, "驳回的数据存入add_score_audit关键事件审核表失败");
                }
        	}else {
        		String auditStatus = auditStatusList.get(i);
        		double d=0d;
        		if("好".equals(auditStatus)) {
        			auditStatus="A";
        			d =r.getA();
        		}else if("良好".equals(auditStatus)){
        			auditStatus="B";
        			d =r.getB();
        		}else {
        			//一般和普通都为C
        			auditStatus="C";
        			d =r.getC();
        		}
        		AddScoreAuditDO addScoreAuditDO=new AddScoreAuditDO();
        		addScoreAuditDO.setId(itemIdList.get(i));
            	addScoreAuditDO.setAuditDate(new Date());
            	addScoreAuditDO.setStatus(1);
            	addScoreAuditDO.setAuditStatus(auditStatusList.get(i));
            	addScoreAuditDO.setScore(BigDecimal.valueOf(r.getScore()*d));
                int row = addScoreAuditService.updateAuditById(addScoreAuditDO);
                if(row<=0) {
                	return ResponseResult.fail(1, "通过的数据存入add_score_audit关键事件审核表失败");
                }
                EmployeeListDO userInfo = employeeListService.getEmployeeByAccount(r.getUserAccount());
        		
                AddScoreDO r3=addScoreService.getByAuditId(itemIdList.get(i).toString());
                if(r3!=null) {
                	//存入关键事件加分表
                	AddScoreDO addScoreDO=new AddScoreDO();
                	addScoreDO.setUserId(userInfo.getId());
                	addScoreDO.setName(userInfo.getName());
                	addScoreDO.setDptcode(userInfo.getSeconddptcode());
                	addScoreDO.setScore(BigDecimal.valueOf(r.getScore()*d));
                	addScoreDO.setRemark("个人申请加分");
                	addScoreDO.setProject(r.getName());
                	addScoreDO.setQuarter(r.getQuarter());
                	addScoreDO.setAuditTime(DateUtil.getDateStrForDefault(new Date()));
                	addScoreDO.setAuditId(itemIdList.get(i));
                	addScoreDO.setInsertTime("");
                	addScoreDO.setInsertUserId(0);
                	int r2=addScoreService.add(addScoreDO);
                	if(r2<=0) {
                    	return ResponseResult.fail(1, "数据存入add_score关键事件得分表失败");
                    }
                }
        	}	
		}
        return ResponseResult.success(0, "审核成功");
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

