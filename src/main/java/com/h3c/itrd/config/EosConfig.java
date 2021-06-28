/**
 * 
 */
package com.h3c.itrd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.util.HttpClientUtil;

/**
 * @author dys4334
 * @date   2021年2月7日
 */
@Configuration
public class EosConfig {
	
	@Value("${eos.account}")
	private String account;
	
	@Value("${eos.password}")
	private String password;

	@Value("${eos.token.url}")
	private String tokenUrl;
	
	public String getAccount() {
        return account;
    }



    public void setAccount(String account) {
        this.account = account;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public String getTokenUrl() {
        return tokenUrl;
    }



    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }



    /**
	 * 获取中台数据接口的token
	 * @author dys4334
	 * @date   2021年2月8日	
	 * @return
	 * 	
	 *
	 */
	public  String getEosToken() {
		String url = this.tokenUrl;
		JSONObject json = new JSONObject();
		json.put("account", account);
		json.put("password", password);
		String paramsJson = json.toJSONString();
		String data = HttpClientUtil.sendHttpPostJson(url, paramsJson);
		JSONObject parseJSONObject = JSONObject.parseObject(data);
		JSONObject dataJSONObject = (JSONObject) parseJSONObject.get("data");
		String token = (String) dataJSONObject.get("token");
		return "Bearer " + token;
	}
	
}
