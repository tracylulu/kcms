package com.h3c.itrd.external.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.config.EosConfig;
import com.h3c.itrd.core.entity.ExperienceinfoListDO;
import com.h3c.itrd.core.service.ExperienceinfoListService;
import com.h3c.itrd.core.service.SharedocinfoListService;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.HttpClientUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rdkmsInfoController")
@Api(value = "经验案例、共享技术库", tags = "经验案例、共享技术库")
public class rdkmsInfoController {
    @Autowired
    ExperienceinfoListService experienceinfoListService;
    @Autowired
    SharedocinfoListService sharedocinfoListService;
    @Autowired
    EosConfig eosConfig;
    @Value(value="${rdkms.username}")
    private String username;
    @Value(value="${rdkms.password}")
    private String password;
    @Value(value="${rdkms.main.url}")
    private String mainUrl;
    @Value(value="${rdkms.sw.info.url}")
    private String swInfoUrl;
    @Value(value="${rdkms.test.info.url}")
    private String testInfoUrl;
    @Value(value="${rdkms.hw.info.url}")
    private String hdInfoUrl;
    @Value(value="${rdkms.sd.info.url}")
    private String sdInfoUrl;
    
	@PostMapping("/getExperienceSoftwareInfo")
	@ApiOperation(value = "获取经验案例软件信息")
	@OperLog(operModelName = "外围系统-rdkms", operContent = "获取经验案例软件信息")
	public ResponseResult getExperienceSoftwswInfoUrlareInfo() throws IOException, Exception {
		swInfoUrl=swInfoUrl.replace("{param}", DateTimeUtils.getBaseTodayDate(-7));
		swInfoUrl=swInfoUrl.replace(" ", "%20");
		swInfoUrl=swInfoUrl.replace("^", "%5E");
		String httpUrl=mainUrl+swInfoUrl;
		String result = HttpClientUtil.sendHttpGetNTLM(httpUrl,username,password);
		Document doc =Jsoup.parse(result);
		Elements rows = doc.select("feed").select("entry");
		if (!rows.isEmpty()) {
			experienceinfoListService.syncExperienceinfo(rows);
		}
		return ResponseResult.success(result);

	}
	
	@PostMapping("/getExperienceTestInfo")
	@ApiOperation(value = "获取经验案例测试信息")
	@OperLog(operModelName = "外围系统-rdkms", operContent = "获取经验案例测试信息")
	public ResponseResult getExperienceTestInfo() throws IOException, Exception {
		testInfoUrl=testInfoUrl.replace("{param}", DateTimeUtils.getBaseTodayDate(-7));
		testInfoUrl=testInfoUrl.replace(" ", "%20");
		testInfoUrl=testInfoUrl.replace("^", "%5E");
		String httpUrl=mainUrl+testInfoUrl;
		String result = HttpClientUtil.sendHttpGetNTLM(httpUrl,username,password);
		Document doc =Jsoup.parse(result);
		Elements rows = doc.select("feed").select("entry");
		if (!rows.isEmpty()) {
			experienceinfoListService.syncExperienceinfo(rows);
		}
		return ResponseResult.success(result);

	}
	
	@PostMapping("/getExperienceHardwareInfo")
	@ApiOperation(value = "获取经验案例硬件信息")
	@OperLog(operModelName = "外围系统-rdkms", operContent = "获取经验案例硬件信息")
	public ResponseResult getExperienceHardwareInfo() throws IOException, Exception {
		hdInfoUrl=hdInfoUrl.replace("{param}", DateTimeUtils.getBaseTodayDate(-7));
		hdInfoUrl=hdInfoUrl.replace(" ", "%20");
		String httpUrl=mainUrl+hdInfoUrl;
		String result = HttpClientUtil.sendHttpGetNTLM(httpUrl,username,password);
		Document doc =Jsoup.parse(result);
		Elements rows = doc.select("feed").select("entry");
		if (!rows.isEmpty()) {
			experienceinfoListService.syncExperienceinfo(rows);
		}
		return ResponseResult.success(result);

	}
	
	@PostMapping("/getShareDocInfo")
	@ApiOperation(value = "获取共享技术信息")
	@OperLog(operModelName = "外围系统-rdkms", operContent = "获取共享技术信息")
	public ResponseResult getShareDocInfo() throws IOException, Exception {
		sdInfoUrl=sdInfoUrl.replace("{param}", DateTimeUtils.getBaseTodayDate(-7));
		sdInfoUrl=sdInfoUrl.replace(" ", "%20");
		String httpUrl=mainUrl+sdInfoUrl;
		String result = HttpClientUtil.sendHttpGetNTLM(httpUrl,username,password);
		Document doc =Jsoup.parse(result);
		Elements rows = doc.select("feed").select("entry");
		if (!rows.isEmpty()) {
			sharedocinfoListService.syncShareDocInfo(rows);
		}
		return ResponseResult.success(result);

	}

}
