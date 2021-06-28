package com.h3c.itrd.task.rdkms;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.service.ExperienceinfoListService;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@JobHandler(value="experienceHWJobHandler")
@Component
public class ExperienceHWJobHandler extends IJobHandler {

	@Autowired
    ExperienceinfoListService experienceinfoListService;
	@Value(value="${rdkms.username}")
    private String username;
    @Value(value="${rdkms.password}")
    private String password;
    @Value(value="${rdkms.main.url}")
    private String mainUrl;
    @Value(value="${rdkms.hw.info.url}")
    private String hdInfoUrl;
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		hdInfoUrl=hdInfoUrl.replace("{param}", DateTimeUtils.getBaseTodayDate(-7));
		hdInfoUrl=hdInfoUrl.replace(" ", "%20");
		String httpUrl=mainUrl+hdInfoUrl;
		String result = HttpClientUtil.sendHttpGetNTLM(httpUrl,username,password);
		Document doc =Jsoup.parse(result);
		Elements rows = doc.select("feed").select("entry");
		if (!rows.isEmpty()) {
			experienceinfoListService.syncExperienceinfo(rows);
		}
		return SUCCESS;
	}

}
