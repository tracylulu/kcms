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
import com.h3c.itrd.core.service.DepartmentListService;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value="deptInfoJobHandler")
@Component
public class DeptInfoJobHandler extends IJobHandler {

	@Autowired
	DepartmentListService departmentListService;
	@Value("${eos.main.url}")
	private String eosMainUrl;
	@Value("${eos.token.url}")
	private String eosTokenUrl;
	@Value("${eos.deptInfo.url}")
	private String deptInfoUrl;
	@Value("${eos.account}")
	private String eosAccount;
	@Value("${eos.password}")
	private String eosPassword;
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		String message;
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl+eosTokenUrl,"{\"account\":\""+eosAccount+"\",\"password\":\""+eosPassword+"\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString());
		if(code != 20220) {
			message = tokenObj.get("message").toString();
			XxlJobLogger.log(message);
        	return FAIL;
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
			if(deptInfoListLength==0) {
				XxlJobLogger.log("部门信息接口无数据");
	        	return FAIL;
			}
	        else {
	        	departmentListService.batchSyncDeptInfo(deptInfoList);
	        	return SUCCESS;
	        }
		} else {
			message = deptInfoObj.get("message").toString();
			XxlJobLogger.log(message);
        	return FAIL;
		}
	}

}
