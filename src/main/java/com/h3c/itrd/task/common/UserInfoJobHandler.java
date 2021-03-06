package com.h3c.itrd.task.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.DepartmentConfigDO;
import com.h3c.itrd.core.service.DepartmentConfigService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value="userInfoJobHandler")
@Component
public class UserInfoJobHandler extends IJobHandler {

	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	DepartmentConfigService departmentConfigService;
	@Value("${eos.main.url}")
	private String eosMainUrl;
	@Value("${eos.token.url}")
	private String eosTokenUrl;
	@Value("${eos.userInfo.url}")
	private String userInfoUrl;
	@Value("${eos.account}")
	private String eosAccount;
	@Value("${eos.password}")
	private String eosPassword;
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		String message;
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl+eosTokenUrl,"{\"account\":\""+eosAccount+"\",\"password\":\""+eosPassword+"\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString()) ;
		if(code != 20220) {
			message = tokenObj.get("message").toString();
			XxlJobLogger.log(message);
        	return FAIL;
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
			if(employeeInfoListLength==0) {
				XxlJobLogger.log("?????????????????????????????????");
	        	return FAIL;
			}
	        else {
	        	List<DepartmentConfigDO> departmentConfigList= departmentConfigService.selectDeptmentConfigInfo();
	        	employeeListService.batchSyncEmployeeInfo(employeeInfoList,departmentConfigList);
	        	return SUCCESS;
	        }
			
		}else {
			message = employeeInfoObj.get("message").toString();
			XxlJobLogger.log(message);
        	return FAIL;
		}
	}

}
