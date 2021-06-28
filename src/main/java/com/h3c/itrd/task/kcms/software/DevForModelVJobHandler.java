package com.h3c.itrd.task.kcms.software;

import java.util.Date;
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
import com.h3c.itrd.core.service.SoftwareListService;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
/*
 * 软件开发模块中的isoftV模型项目开发得分汇算定时任务
 * 
 */
@JobHandler(value="devForModelVJobHandler")
@Component
public class DevForModelVJobHandler extends IJobHandler {

	@Autowired
	SoftwareListService softwareListService;
	
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		XxlJobLogger.log("xxl-job----kcms devForModelV job start");
		//根据当前时间获取当前季度
		String nowQuarter = DateTimeUtils.getQuarter();
		XxlJobLogger.log(nowQuarter);
		//根据当前时间获取上个季度
		String baseTodayDate = DateTimeUtils.getBaseTodayDate(-30);
		String lastQuarter = DateTimeUtils.getQuarterByDate(baseTodayDate);
		XxlJobLogger.log(lastQuarter);
		Boolean flagA=false;
		Boolean flagB=false;
		Boolean flagC=false;
		if(nowQuarter.equals(lastQuarter)) {
			flagA = softwareListService.devModelV(nowQuarter);
			XxlJobLogger.log(String.valueOf(flagA));
		}else {
			//不同则同时汇算两个季度
			flagB = softwareListService.devModelV(lastQuarter);
			XxlJobLogger.log(String.valueOf(flagB));
			
			flagC = softwareListService.devModelV(nowQuarter);
			XxlJobLogger.log(String.valueOf(flagC));
		}
		if(flagA || (flagB && flagC)) {
			XxlJobLogger.log("xxl-job----kcms devForModelV job end success");
			return SUCCESS;
		}else {
			XxlJobLogger.log("xxl-job----kcms devForModelV job end fail");
			return FAIL;
		}
	}
	
	

}
