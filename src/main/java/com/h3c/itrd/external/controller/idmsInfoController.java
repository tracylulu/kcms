package com.h3c.itrd.external.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.service.IdmsinfoListService;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.HttpClientUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/defectInfo")
@Api(value = "idms缺陷数据获取", tags = "idms缺陷数据获取")
public class idmsInfoController {
	@Autowired
	IdmsinfoListService idmsinfoListService;
	@Value(value="${eos.account}")
	private String eosAccount;
	@Value(value="${eos.password}")
	private String eosPassword;
	@Value(value="${eos.main.url}")
	private String eosMainUrl;
	@Value("${eos.token.url}")
	private String eosTokenUrl;
	@Value("${eos.changeDefectNOs.url}")
	private String changeDefectNOsUrl;
	@Value("${eos.changeDefectDetail.url}")
	private String changeDefectDetailUrl;
	@Value("${limit.defectInfo}")
	private Integer limitDefectInfo;

	@GetMapping("/getDefectInfo")
	@ApiOperation(value = "获取专利信息")
	@OperLog(operModelName="外围系统-专利信息",operContent="获取专利/发明人数据")
	public ResponseResult getPatentInfo() throws IOException, Exception {
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl+eosTokenUrl,"{\"account\":\""+eosAccount+"\",\"password\":\""+eosPassword+"\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString()) ;
		if(code != 20220) {
			throw new Exception(tokenObj.get("message").toString());
		}
		String authorization =  tokenObj.getJSONObject("data").get("token").toString() ;
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization",  "Bearer "+authorization);
		//开始时间-结束时间逻辑处理 start一周前日期，end今天日期
		String start = DateTimeUtils.getBaseTodayDate(-1);
		String end =DateTimeUtils.getTodayDate();
		String patentInventor=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+changeDefectNOsUrl,"{\"begindate\":\""+start+"\",\"enddate\":\""+end+"\"}",header);//"{\"begindate\":\"2020-04-27 00:00:00\",\"enddate\":\"2021-07-27 00:00:00\"}"
		JSONObject defectNosObj= JSON.parseObject(patentInventor);
		int inventorCode = Integer.parseInt(defectNosObj.get("code").toString());		
		if(inventorCode==20216) {
			List<Map<String, Object>> defectNosList = (List)defectNosObj.getJSONArray("data");
			int defectNosListLength = defectNosList.size();
			if(defectNosListLength==0)
	        	return ResponseResult.fail("接口无数据");
	        else {
//				String defectNos = (String) defectNosList.get(0).get("DefectNOs");
//				String[] defectNosArray = defectNos.split(",");
				List<String> defectNosParamList = new ArrayList<String>();
				String defectNosParam = "";
				int i = 0;
				for (Map<String, Object> defectNo : defectNosList) {
					if (++i > limitDefectInfo) {
						i = 1;
						defectNosParamList.add(defectNosParam);
						defectNosParam = "";
					}
					defectNosParam = defectNosParam + "\"" + defectNo.get("DefectNO") + "\",";
				}
		        defectNosParamList.add(defectNosParam);
		        List<Map<String, Object>> defectAllDetailList =new ArrayList<Map<String, Object>>();
		        for (String defectNosParamString : defectNosParamList) {
		        	defectNosParamString=defectNosParamString.substring(0,defectNosParamString.length()-1);
			        String defectDetailResult=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+changeDefectDetailUrl,"{\"defectno\":["+defectNosParamString.toString()+"]}",header);
			        JSONObject defectDetailObj= JSON.parseObject(defectDetailResult);
			        List<Map<String, Object>> defectDetailList =(List)defectDetailObj.getJSONArray("data");
			        defectAllDetailList.addAll(defectDetailList);
				}
		        
		        idmsinfoListService.syncDefectInfo(defectAllDetailList);
		       
			}
			return ResponseResult.success("同步接口数据成功");
	        } 
		return ResponseResult.fail("同步接口数据失败");
	}
}
