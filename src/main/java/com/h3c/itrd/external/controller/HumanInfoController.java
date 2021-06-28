package com.h3c.itrd.external.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.DepartmentConfigDO;
import com.h3c.itrd.core.service.DepartmentConfigService;
import com.h3c.itrd.core.service.DepartmentListService;
import com.h3c.itrd.core.service.DptmanagerListService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.EmployeeResignListService;
import com.h3c.itrd.util.HttpClientUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/HumanInfoController")
@Api(value = "人力数据获取", tags = "人力数据获取")
public class HumanInfoController {
	@Value("${eos.main.url}")
	private String eosMainUrl;
	@Value("${eos.token.url}")
	private String eosTokenUrl;
	@Value("${eos.deptInfo.url}")
	private String deptInfoUrl;
	@Value("${eos.userInfo.url}")
	private String userInfoUrl;
	@Value("${eos.dismissionUser.url}")
	private String dismissionUserUrl;
	@Value("${eos.deptManager.url}")
	private String deptManagerUrl;
	@Value("${eos.account}")
	private String eosAccount;
	@Value("${eos.password}")
	private String eosPassword;
	
	@Autowired
	EmployeeResignListService employeeResignListService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	DptmanagerListService dptmanagerListService;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	DepartmentConfigService departmentConfigService;
	
	@GetMapping("/getDeptInfo")
	@ApiOperation(value = "获取部门数据信息")
	@OperLog(operModelName="外围系统-eos接口",operContent="获取部门数据信息")
	public ResponseResult getDeptInfo() throws IOException, Exception {
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl+eosTokenUrl,"{\"account\":\""+eosAccount+"\",\"password\":\""+eosPassword+"\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString());
		if(code != 20220) {
			throw new Exception(tokenObj.get("message").toString());
		}
		String authorization =  tokenObj.getJSONObject("data").get("token").toString() ;
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization",  "Bearer "+authorization);
		String deptInfo=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+deptInfoUrl,null,header);
		JSONObject deptInfoObj= JSON.parseObject(deptInfo);
		int deptInfoCode = Integer.parseInt(deptInfoObj.get("code").toString());		
		if(deptInfoCode==20216) {
			List<Map<String, Object>> deptInfoList = (List)deptInfoObj.getJSONArray("data");
			int deptInfoListLength = deptInfoList.size();
			if(deptInfoListLength==0)
	        	return ResponseResult.fail("接口无数据");
	        else {
	        	departmentListService.batchSyncDeptInfo(deptInfoList);
	        }
			
		}
		return null;
	}
	
	@GetMapping("/getUserInfo")
	@ApiOperation(value = "获取人员数据信息")
	@OperLog(operModelName="外围系统-eos接口",operContent="获取人员数据信息")
	public ResponseResult getUserInfo() throws IOException, Exception {
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl+eosTokenUrl,"{\"account\":\""+eosAccount+"\",\"password\":\""+eosPassword+"\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString()) ;
		if(code != 20220) {
			throw new Exception(tokenObj.get("message").toString());
		}
		String authorization =  tokenObj.getJSONObject("data").get("token").toString() ;
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization",  "Bearer "+authorization);
		String employeeInfo=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+userInfoUrl,null,header);
		JSONObject employeeInfoObj= JSON.parseObject(employeeInfo);
		int employeeInfoCode = Integer.parseInt(employeeInfoObj.get("code").toString());		
		if(employeeInfoCode==20216) {
			List<Map<String, Object>> employeeInfoList = (List)employeeInfoObj.getJSONArray("data");
			int employeeInfoListLength = employeeInfoList.size();
			if(employeeInfoListLength==0)
	        	return ResponseResult.fail("接口无数据");
	        else {
	        	List<DepartmentConfigDO> departmentConfigList= departmentConfigService.selectDeptmentConfigInfo();
	        	employeeListService.batchSyncEmployeeInfo(employeeInfoList,departmentConfigList);
	        }
			
		}
		return null;
	}
	
	@GetMapping("/getDeptManager")
	@ApiOperation(value = "获取主管数据信息")
	@OperLog(operModelName="外围系统-eos接口",operContent="获取主管数据信息")
	public ResponseResult getDeptManager() throws IOException, Exception {
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl+eosTokenUrl,"{\"account\":\""+eosAccount+"\",\"password\":\""+eosPassword+"\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString()) ;
		if(code != 20220) {
			throw new Exception(tokenObj.get("message").toString());
		}
		String authorization =  tokenObj.getJSONObject("data").get("token").toString() ;
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization",  "Bearer "+authorization);
		String deptManager=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+deptManagerUrl,null,header);
		JSONObject deptManagerObj= JSON.parseObject(deptManager);
		int deptManagerCode = Integer.parseInt(deptManagerObj.get("code").toString());		
		if(deptManagerCode==20216) {
			List<Map<String, Object>> deptManagerList = (List)deptManagerObj.getJSONArray("data");
			int deptManagerListLength = deptManagerList.size();
			if(deptManagerListLength==0)
	        	return ResponseResult.fail("接口无数据");
	        else {
	        	dptmanagerListService.batchSyncDeptManager(deptManagerList);
	        }
			
		}
		return null;
	}
	
	@GetMapping("/getDismissionUser")
	@ApiOperation(value = "获取离职人员数据信息")
	@OperLog(operModelName="外围系统-eos接口",operContent="获取离职人员数据信息")
	public ResponseResult getDismissionUser() throws IOException, Exception {
		Date date1 = new Date();
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl+eosTokenUrl,"{\"account\":\""+eosAccount+"\",\"password\":\""+eosPassword+"\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString()) ;
		if(code != 20220) {
			throw new Exception(tokenObj.get("message").toString());
		}
		String authorization =  tokenObj.getJSONObject("data").get("token").toString() ;
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization",  "Bearer "+authorization);
		String dismissionUser=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+dismissionUserUrl,null,header);
		JSONObject dismissionUserObj= JSON.parseObject(dismissionUser);
		int dismissionUserCode = Integer.parseInt(dismissionUserObj.get("code").toString());		
		if(dismissionUserCode==20216) {
			List<Map<String, Object>> dismissionUserList = (List)dismissionUserObj.getJSONArray("data");
			int dismissionUserListLength = dismissionUserList.size();
			if(dismissionUserListLength==0)
	        	return ResponseResult.fail("接口无数据");
	        else {
	        	employeeResignListService.batchSyncDismissionUser(dismissionUserList);
	        }
		}
		Date date2 = new Date();
		return ResponseResult.success("start:"+date1+", end:"+date2);
	}

}
