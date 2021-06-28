package com.h3c.itrd.external.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.PatentinfoListDO;
import com.h3c.itrd.core.service.PatentinfoListService;
import com.h3c.itrd.util.HttpClientUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/patentInfo")
@Api(value = "专利信息数据相关", tags = "专利信息数据相关")
public class PatentInfoController {
	@Autowired
	PatentinfoListService patentinfoListService;
	@Value(value="${eos.main.url}")
	private String eosMainUrl;
	@Value(value="${eos.token.url}")
	private String eosTokenUrl;
	@Value(value="${eos.patentInfo.url}")
	private String eosPatentInfoUrl;
	@Value(value="${eos.patentInventor.url}")
	private String eosPatentInventorUrl;
	@Value(value="${eos.account}")
	private String eosAccount;
	@Value(value="${eos.password}")
	private String eosPassword;
	@Value(value="${limit.patentInfo}")
	private Integer limitPatentInfo;
	@Value(value="${limit.patentNo}")
	private Integer limitPatentNo;
	
	@GetMapping("/getPatentInfo")
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
		String patentInventor=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+eosPatentInventorUrl,null,header);
		JSONObject inventorObj= JSON.parseObject(patentInventor);
		int inventorCode = Integer.parseInt(inventorObj.get("code").toString());		
		if(inventorCode==20216) {
	        List<Map<String, Object>> inventorList = (List) inventorObj.getJSONArray("data");
	        int inventorSize =inventorList.size();
	        //查发明人数据量
	        int patentInfoCount = patentinfoListService.selectCount();
	        if(Math.abs(inventorSize-patentInfoCount)>limitPatentInfo) {
	        	return null;
	        } else {
	        	String patentInfoResult=HttpClientUtil.sendHttpPostJsonWithHeader(eosMainUrl+eosPatentInfoUrl,null,header);
	        	JSONObject patentInfoObj= JSON.parseObject(patentInfoResult);
	        	int patentInfoCode = Integer.parseInt(patentInfoObj.get("code").toString());
	        	if(patentInfoCode ==20216) {
	        		List<Map<String, Object>> patentInfoList = (List) patentInfoObj.getJSONArray("data");
	        		int patentInfoSize =patentInfoList.size();
	        		//查发明数据量
	        		int patentNoCount =patentinfoListService.selectPatentNoCount();
	        		//发明数据量差距过大,不更新数据 
	    	        if(Math.abs(patentInfoSize-patentNoCount)>limitPatentNo) {
	    	        	return null;
	    	        }
					else {
						// 1.拼接数据 2.计算占比 3.清理数据 4.插入数据
						List<PatentinfoListDO> patentinfoList = new ArrayList<PatentinfoListDO>();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						for (Map<String, Object> inventor : inventorList) {
							String patentNo = (String) inventor.get("PAT_EPSID");
							Optional<Map<String, Object>> patentInfo = patentInfoList.stream().filter(o -> patentNo.equals((String) o.get("PAT_EPSID"))).findAny();
							if (patentInfo.isPresent() && (String) patentInfo.get().get("PAT_NAME")!=null) {
								List<Map<String, Object>> sameInventorList = inventorList.stream().filter(o -> patentNo.equals(o.get("PAT_EPSID"))).collect(Collectors.toList());
								BigDecimal bignum1 = new BigDecimal(1);
								BigDecimal bignum2 = new BigDecimal(sameInventorList.size());
								BigDecimal weight = bignum1.divide(bignum2,2,BigDecimal.ROUND_HALF_UP);
								PatentinfoListDO patentinfoDo = new PatentinfoListDO();
								patentinfoDo.setPatentNo(patentNo);
								String applyDate = (String) patentInfo.get().get("PAT_APPDATE");
								patentinfoDo.setApplyDate(applyDate==null?null:sdf.parse(applyDate));
								patentinfoDo.setPatentName((String) patentInfo.get().get("PAT_NAME"));
								// 标识转成类型
								Integer category = (Integer) patentInfo.get().get("PAT_CATEGORY");
								switch (category) {
								case 1:
									patentinfoDo.setPatentType("发明");
									break;
								case 2:
									patentinfoDo.setPatentType("实用新型");
									break;
								case 3:
									patentinfoDo.setPatentType("外观设计");
									break;
								default:
									;
								}
								patentinfoDo.setThirddptname((String) patentInfo.get().get("ORG_NAME"));
								patentinfoDo.setDptname((String) patentInfo.get().get("BS_NAME"));
								patentinfoDo.setInventorname((String) inventor.get("USER_NAME"));
								patentinfoDo.setInventorid((String) inventor.get("USER_SID"));
								patentinfoDo.setWeight(weight); // 占比处理
								patentinfoDo.setApplicant((String) patentInfo.get().get("APPLICANT_NAME"));
								patentinfoDo.setSyncTime(new Date());
								patentinfoList.add(patentinfoDo);
							}
	    	        	}
	    	        	patentinfoListService.deleteAllInfo();
	    	        	patentinfoListService.batchInsertInfo(patentinfoList);
	    	        }
	        	}
	        }
		}
		return null;
	}
}
