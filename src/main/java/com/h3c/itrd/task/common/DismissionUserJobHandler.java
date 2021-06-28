package com.h3c.itrd.task.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.core.service.EmployeeResignListService;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value="dismissionUserJobHandler")
@Component
public class DismissionUserJobHandler extends IJobHandler {

	@Autowired
	EmployeeResignListService employeeResignListService;
	@Value("${eos.main.url}")
	private String eosMainUrl;
	@Value("${eos.token.url}")
	private String eosTokenUrl;
	@Value("${eos.dismissionUser.url}")
	private String dismissionUserUrl;
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
		String dismissionUser=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+dismissionUserUrl,null,header);
		JSONObject dismissionUserObj= JSON.parseObject(dismissionUser);
		int dismissionUserCode = Integer.parseInt(dismissionUserObj.get("code").toString());		
		if(dismissionUserCode==20216) {
			List<Map<String, Object>> dismissionUserList = (List)dismissionUserObj.getJSONArray("data");
			int dismissionUserListLength = dismissionUserList.size();
			if(dismissionUserListLength==0) {
				XxlJobLogger.log("离职人员接口无数据");
	        	return FAIL;
			}
	        else {
	        	employeeResignListService.batchSyncDismissionUser(dismissionUserList);
	        	return SUCCESS;
	        }
		}else {
			message = dismissionUserObj.get("message").toString();
			XxlJobLogger.log(message);
        	return FAIL;
		}
		
	}

}
