package com.h3c.itrd.task.idms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.core.service.IdmsinfoListService;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value="defectInfoJobHandler")
@Component
public class DefectInfoJobHandler extends IJobHandler{
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
	
	@Override
	public ReturnT<String>  execute(String param) throws Exception {
		String message;
		String tokenResult = HttpClientUtil.sendHttpPostJson(eosMainUrl + eosTokenUrl,"{\"account\":\"" + eosAccount + "\",\"password\":\"" + eosPassword + "\"} ");
		JSONObject tokenObj = JSON.parseObject(tokenResult);
		int code = Integer.parseInt(tokenObj.get("code").toString());
		if (code != 20220) {
			message = tokenObj.get("message").toString();
			XxlJobLogger.log(message);
			return FAIL;
		}
		String authorization = tokenObj.getJSONObject("data").get("token").toString();
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "Bearer " + authorization);
		// 开始时间-结束时间逻辑处理 start一周前日期，end今天日期
		String start = DateTimeUtils.getBaseTodayDate(-1);
		String end = DateTimeUtils.getTodayDate();
		String patentInventor = HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl + changeDefectNOsUrl,"{\"begindate\":\""+start+"\",\"enddate\":\""+end+"\"}", header);
		JSONObject defectNosObj = JSON.parseObject(patentInventor);
		int inventorCode = Integer.parseInt(defectNosObj.get("code").toString());
		if (inventorCode == 20216) {
			List<Map<String, Object>> defectNosList = (List) defectNosObj.getJSONArray("data");
			int defectNosListLength = defectNosList.size();
			if (defectNosListLength == 0) {
				XxlJobLogger.log("缺陷编码接口无数据");
				return SUCCESS;
			} else {
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
				List<Map<String, Object>> defectAllDetailList = new ArrayList<Map<String, Object>>();
				for (String defectNosParamString : defectNosParamList) {
					defectNosParamString = defectNosParamString.substring(0, defectNosParamString.length() - 1);
					String defectDetailResult = HttpClientUtil.sendHttpPostJsonWithHeader(
							eosMainUrl + changeDefectDetailUrl,
							"{\"defectno\":[" + defectNosParamString.toString() + "]}", header);
					JSONObject defectDetailObj = JSON.parseObject(defectDetailResult);
					int defectDetailCode = Integer.parseInt(defectDetailObj.get("code").toString());
					if (defectDetailCode == 20216) {
						List<Map<String, Object>> defectDetailList = (List) defectDetailObj.getJSONArray("data");
						defectAllDetailList.addAll(defectDetailList);
					} else {
						message = defectDetailObj.get("message").toString();
						XxlJobLogger.log(message);
						return FAIL;
					}
				}
				idmsinfoListService.syncDefectInfo(defectAllDetailList);
				return SUCCESS;
			}
		} else {
			message = defectNosObj.get("message").toString();
			XxlJobLogger.log(message);
			return FAIL;
		}
	}

}
