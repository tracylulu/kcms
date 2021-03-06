package com.h3c.itrd.core.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageInfo;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.consts.SystemConstant;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.AddScoreDO;
import com.h3c.itrd.core.entity.AdminListDO;
import com.h3c.itrd.core.entity.DepartmentListDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.LoginSummaryDO;
import com.h3c.itrd.core.entity.NoticeListDO;
import com.h3c.itrd.core.service.AddScoreService;
import com.h3c.itrd.core.service.AdminListService;
import com.h3c.itrd.core.service.DepartmentListService;
import com.h3c.itrd.core.service.DptmanagerListService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.LoginSummaryService;
import com.h3c.itrd.core.service.NoticeListService;
import com.h3c.itrd.core.service.QuarterScoreService;
import com.h3c.itrd.core.vo.DepartmentListForAdminVO;
import com.h3c.itrd.core.vo.DepartmentListReqVO;
import com.h3c.itrd.core.vo.DepartmentListResVO;
import com.h3c.itrd.core.vo.DepartmentListVO;
import com.h3c.itrd.core.vo.EmployeeListReqVO;
import com.h3c.itrd.core.vo.EmployeeListResVO;
import com.h3c.itrd.core.vo.Get4DptsReqVO;
import com.h3c.itrd.core.vo.OverviewReqVO;
import com.h3c.itrd.core.vo.RankResVO;
import com.h3c.itrd.util.AbstractExcelReader;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.DateUtil;
import com.h3c.itrd.util.ExportExcelWrapper;
import com.h3c.itrd.util.PagingList;
import com.h3c.itrd.util.RankUtil;
import com.h3c.itrd.util.UserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * ????????????  ???????????????
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/api/index")
@Api(value = "???????????????????????????", tags = {"???????????????????????????"})
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private EmployeeListService employeeListService;
	@Autowired
	private DepartmentListService departmentListService;
	@Autowired
	private DptmanagerListService dptmanagerListService;
	@Autowired
	private AdminListService adminListService;
	@Autowired
	private LoginSummaryService loginSummaryService;
	
	
	@GetMapping("/setAdmin")
    @ApiOperation(value = "????????????????????????set_admin")
	@OperLog(operModelName="?????????????????????",operContent="?????????????????????")
    public ResponseResult setAdmin(@RequestParam @ApiParam(name = "id", value = "??????id", required = true) String id,
    					@RequestParam @ApiParam(name = "type", value = "??????", required = true) Integer type) throws Exception{
    	
		if(type != 1 && type != 2) {
			return ResponseResult.fail(1, "type????????????");
		}
		EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "????????????????????????");
        }
        AdminListDO adminListDO=new AdminListDO();
        adminListDO.setType(type);
        adminListDO.setId(employee.getId());
        adminListDO.setName(employee.getName());
        adminListDO.setDptcode(employee.getSeconddptcode());
        int i = adminListService.add(adminListDO);
		if(i>0) {
			return ResponseResult.success(0, "????????????");
		}else {
			return ResponseResult.fail(1, "????????????");
		}
    }
    
	@GetMapping("/userInfo")
    @ApiOperation(value = "?????????????????????user_info")
	@OperLog(operModelName="??????????????????",operContent="??????????????????")
    public ResponseResult userInfo() throws Exception {
		String id = UserUtils.getCurrentUserId();
		//???????????????????????????01917 ???????????????11640  ????????????00745  ??????????????????m09212
		EmployeeListDO employee = employeeListService.getEmployeeById(id);
        if (employee == null) {
            return ResponseResult.fail(1, "????????????????????????");
        }
        Map<String, Object> paramMap = new HashMap<>();
        List<Map> optionLevel2 =new ArrayList<>();
        List<Map> optionLevel3 =new ArrayList<>();
        Map<String, String> optionLevel2Map = new HashMap<>();
        Map<String, Object> optionLevel3Map = new HashMap<>();
        //????????????????????????
        paramMap.put("type", 1);
        paramMap.put("name", employee.getName());
        paramMap.put("id", employee.getId());
        paramMap.put("account", employee.getAccount());
        paramMap.put("postType", employee.getPostType());
        paramMap.put("level2", employee.getSeconddptcode());
        paramMap.put("level3", employee.getThirddptcode());
        //???????????????????????????????????????????????????
        if(employee.getThirddptcode()==null) {
        	paramMap.put("level4", "");
        }else {
        	if(employee.getThirddptcode().equals(employee.getCurrentdptcode())) {
            	paramMap.put("level4", "");
            }else {
            	paramMap.put("level4", employee.getCurrentdptcode());
            }
        }
        
        paramMap.put("optionLevel2", optionLevel2);
        paramMap.put("optionLevel3", optionLevel3);
        //??????
        String level = dptmanagerListService.getLevelById(id);
		if(StringUtils.isNotBlank(level)) {
			paramMap.put("type", 2);
			paramMap.put("level", level);
			optionLevel2Map.put("name", employee.getSeconddpt());
			optionLevel2Map.put("code", employee.getSeconddptcode());
			optionLevel2.add(optionLevel2Map);
			paramMap.put("optionLevel2", optionLevel2);
			List<DepartmentListDO> listBySubDptcode = departmentListService.getListBySubDptcode(employee.getSeconddptcode());
    		List<DepartmentListVO> newList = listBySubDptcode.stream().map(e -> new DepartmentListVO(e.getDptname(), e.getDptcode())).collect(Collectors.toList());
    		optionLevel3Map.put("level2", employee.getSeconddptcode());
    		optionLevel3Map.put("level3", newList);
    		optionLevel3.add(optionLevel3Map);
    		paramMap.put("optionLevel3", optionLevel3);
		}
		AdminListDO adminListDO = adminListService.getById(id);
        if(adminListDO!=null) {
        	//?????????
        	if(adminListDO.getType()==1) {
        		paramMap.put("type", 3);
        		optionLevel2Map.put("name", employee.getSeconddpt());
    			optionLevel2Map.put("code", employee.getSeconddptcode());
    			optionLevel2.add(optionLevel2Map);
    			paramMap.put("optionLevel2", optionLevel2);
    			List<DepartmentListDO> listBySubDptcode = departmentListService.getListBySubDptcode(employee.getSeconddptcode());
        		List<DepartmentListVO> newList = listBySubDptcode.stream().map(e -> new DepartmentListVO(e.getDptname(), e.getDptcode())).collect(Collectors.toList());
        		optionLevel3Map.put("level2", employee.getSeconddptcode());
        		optionLevel3Map.put("level3", newList);
        		optionLevel3.add(optionLevel3Map);
        		paramMap.put("optionLevel3", optionLevel3);
        	}else {
        		//???????????????
        		paramMap.put("type", 4);
        		paramMap.put("admin_level2", adminListDO.getDptcode());
        		
        		List<DepartmentListForAdminVO> departmentListForAdmin=departmentListService.getListForAdmin();
        		paramMap.put("optionLevel2", departmentListForAdmin);
        		for (int i = 0; i < departmentListForAdmin.size(); i++) {
        			List<DepartmentListDO> listBySubDptcode = departmentListService.getListBySubDptcode(departmentListForAdmin.get(i).getCode());
        			List<DepartmentListVO> newList = listBySubDptcode.stream().map(e -> new DepartmentListVO(e.getDptname(), e.getDptcode())).collect(Collectors.toList());
        			Map<String, Object> optionLevel3MapForAdmin = new HashMap<>();
        			optionLevel3MapForAdmin.put("level2", departmentListForAdmin.get(i).getCode());
        			optionLevel3MapForAdmin.put("level3", newList);
            		optionLevel3.add(optionLevel3MapForAdmin);
        		}
        		paramMap.put("optionLevel3", optionLevel3);
        	}
        }
        List<String> careerLevelList = new ArrayList<>(Arrays.asList("????????????","????????????","????????????","????????????"));
        paramMap.put("careerLevel", careerLevelList);
        List<String> cityList = new ArrayList<>(Arrays.asList("??????","??????","??????","??????","??????","??????","??????"));
        paramMap.put("city", cityList);
        //???????????????????????????
        String quarter = DateTimeUtils.getQuarter();
        int index=0;
        List<String> quarterList = new ArrayList<>(
        		Arrays.asList("2020Q1","2020Q2","2020Q3","2020Q4","2021Q1","2021Q2","2021Q3","2021Q4","2022Q1","2022Q2","2022Q3","2022Q4"));
        for (int i = 0; i < quarterList.size(); i++) {
			if(quarter.equals(quarterList.get(i))) {
				index=i+1;
				break;
			}
		}
        paramMap.put("quarter", quarterList.subList(0, index));
        paramMap.put("currentQuarter", DateTimeUtils.getQuarter());
        paramMap.put("status", true);
        paramMap.put("errorInfo", "");
        
        //????????????????????????
        LoginSummaryDO loginSummaryDO=new LoginSummaryDO();
        loginSummaryDO.setAccount(employee.getAccount());
        loginSummaryDO.setUserId(employee.getId());
        loginSummaryDO.setTime(new Date());
        loginSummaryDO.setDate(DateUtil.getDateFormat(new Date()));
        int i = loginSummaryService.add(loginSummaryDO);
        if(i<=0){
        	return ResponseResult.fail(1, "??????????????????????????????");
        }
    	return ResponseResult.success(0, "????????????",paramMap,paramMap.size());
    }
	
	@GetMapping("/getAccount")
    @ApiOperation(value = "?????????????????????account?????????get_account")
	@OperLog(operModelName="?????????????????????account??????",operContent="?????????????????????account??????")
    public ResponseResult getAccount(@RequestParam @ApiParam(name = "query", value = "?????????????????????", required = true) String query,
    		@RequestParam @ApiParam(name = "thirddptcode", value = "????????????code", required = true) String thirddptcode) throws Exception{
		String id = UserUtils.getCurrentUserId();
		List<EmployeeListDO> list = employeeListService.getListByQuery(thirddptcode, query);
    	list=list.stream().filter(o->!id.equals(o.getId())).sorted(Comparator.comparing(EmployeeListDO::getAccount)).collect(Collectors.toList());
    	return ResponseResult.success(0, "????????????",list,list.size());
    }
	
	@GetMapping("/getUserInfoDetail")
    @ApiOperation(value = "?????????????????????????????????getUserInfoDetail")
	@OperLog(operModelName="??????????????????????????????",operContent="??????????????????????????????")
    public ResponseResult getUserInfoDetail(@RequestParam @ApiParam(name = "id", value = "??????id", required = true) String id) throws Exception{
    	EmployeeListDO employee = employeeListService.getEmployeeById(id);
    	List<EmployeeListDO> list=new ArrayList<>();
    	list.add(employee);
    	return ResponseResult.success(0, "????????????",list,list.size());
    }
	
    
    @PostMapping("/get4Dpts")
    @ApiOperation(value = "???????????????????????????????????????????????????get_dpts")
    @OperLog(operModelName="????????????????????????????????????????????????",operContent="????????????????????????????????????????????????")
    public ResponseResult get4Dpts(@RequestBody Get4DptsReqVO get4DptsReqVO) throws Exception{
    	List<Map<String ,Object>> list=new ArrayList<>();
    	List<String> dept3CodeList = get4DptsReqVO.getDept3CodeList();
    	for (int i = 0; i < dept3CodeList.size(); i++) {
    		Map<String ,Object> map =new HashMap<>();
    		DepartmentListDO dpt = departmentListService.getListByDptcode(dept3CodeList.get(i));
    		map.put("level3", dpt.getDptname());
    		List<DepartmentListDO> listBySubDptcode = departmentListService.getListBySubDptcode(dept3CodeList.get(i));
    		List<DepartmentListVO> newList = listBySubDptcode.stream().map(e -> new DepartmentListVO(e.getDptname(), e.getDptcode())).collect(Collectors.toList());
    		map.put("level4", newList);
    		list.add(map);
    	}
    	return ResponseResult.success(0, "????????????",list,list.size());
    }
	
    @GetMapping("/get4Dpt")
    @ApiOperation(value = "?????????????????????????????????????????????????????????get_dpt")
    @OperLog(operModelName="??????????????????????????????????????????????????????",operContent="??????????????????????????????????????????????????????")
    public ResponseResult get4Dpt(@RequestParam @ApiParam(name = "dept3Code", value = "????????????code", required = true) String dept3Code) throws Exception{
    	List<Map<String ,Object>> list=new ArrayList<>();
    		Map<String ,Object> map =new HashMap<>();
    		DepartmentListDO dpt = departmentListService.getListByDptcode(dept3Code);
    		if(dpt==null) {
    			return ResponseResult.fail(1, "?????????????????????????????????");
    		}
    		List<DepartmentListDO> listBySubDptcode = departmentListService.getListBySubDptcode(dept3Code);
    		List<DepartmentListVO> newList = listBySubDptcode.stream().map(e -> new DepartmentListVO(e.getDptname(), e.getDptcode())).collect(Collectors.toList());
    		map.put("level4", newList);
    		list.add(map);
    	return ResponseResult.success(0, "????????????",list,list.size());
    }
	
	
	
	
}

