package com.h3c.itrd.task.rdkms;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.h3c.itrd.core.service.ExperienceinfoListService;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@JobHandler(value="experienceSWJobHandler")
@Component
public class ExperienceSWJobHandler extends IJobHandler {

	@Autowired
    ExperienceinfoListService experienceinfoListService;
	@Value(value="${rdkms.username}")
    private String username;
    @Value(value="${rdkms.password}")
    private String password;
    @Value(value="${rdkms.main.url}")
    private String mainUrl;
    @Value(value="${rdkms.sw.info.url}")
    private String swInfoUrl;
	@Override
	public ReturnT<String> execute(String param) throws Exception {
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
		return SUCCESS;
	}

}
