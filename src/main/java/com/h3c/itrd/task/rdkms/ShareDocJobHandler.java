package com.h3c.itrd.task.rdkms;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.h3c.itrd.core.service.SharedocinfoListService;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.HttpClientUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@JobHandler(value="shareDocJobHandler")
@Component
public class ShareDocJobHandler extends IJobHandler {

	@Autowired
    SharedocinfoListService sharedocinfoListService;
	@Value(value="${rdkms.username}")
    private String username;
    @Value(value="${rdkms.password}")
    private String password;
    @Value(value="${rdkms.main.url}")
    private String mainUrl;
    @Value(value="${rdkms.sd.info.url}")
    private String sdInfoUrl;
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		sdInfoUrl=sdInfoUrl.replace("{param}", DateTimeUtils.getBaseTodayDate(-7));
		sdInfoUrl=sdInfoUrl.replace(" ", "%20");
		String httpUrl=mainUrl+sdInfoUrl;
		String result = HttpClientUtil.sendHttpGetNTLM(httpUrl,username,password);
		Document doc =Jsoup.parse(result);
		Elements rows = doc.select("feed").select("entry");
		if (!rows.isEmpty()) {
			sharedocinfoListService.syncShareDocInfo(rows);
		}
		return SUCCESS;
	}

}
