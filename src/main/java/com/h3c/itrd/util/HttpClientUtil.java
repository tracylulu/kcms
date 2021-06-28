package com.h3c.itrd.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


public class HttpClientUtil {

	// utf-8字符编码
	public static final String CHARSET_UTF_8 = "utf-8";

	// HTTP内容类型。
	public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

	// HTTP内容类型。相当于form表单的形式，提交数据
	public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

	// HTTP内容类型。相当于form表单的形式，提交数据
	public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

	// 连接管理器
	private static PoolingHttpClientConnectionManager pool;

	// 请求配置
	private static RequestConfig requestConfig;

	static {

		try {
			// System.out.porintln("初始化HttpClientTest~~~开始");
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			// 配置同时支持 HTTP 和 HTPPS
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
			// 初始化连接管理器
			pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			// 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
			pool.setMaxTotal(200);
			// 设置最大路由
			pool.setDefaultMaxPerRoute(2);
			// 根据默认超时限制初始化requestConfig
			int socketTimeout = 10000;
			int connectTimeout = 10000;
			int connectionRequestTimeout = 10000;
			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

			// System.out.println("初始化HttpClientTest~~~结束");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		// 设置请求超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
				.setConnectionRequestTimeout(50000).build();
	}

	public static CloseableHttpClient getHttpClient() {

		CloseableHttpClient httpClient = HttpClients.custom()
				// 设置连接池管理
				.setConnectionManager(pool)
				// 设置请求配置
				.setDefaultRequestConfig(requestConfig)
				// 设置重试次数
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();

		return httpClient;
	}

	/**
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();
			// 配置请求信息
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			// 得到响应实例
			HttpEntity entity = response.getEntity();

			// 可以获得响应头
			// Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
			// for (Header header : headers) {
			// System.out.println(header.getName());
			// }

			// 得到响应类型
			// System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	
	
	private static String sendHttpPostAndReturnHeads(HttpPost httpPost) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		JSONObject  responseJson = new JSONObject();
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();
			// 配置请求信息
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			// 得到响应实例
			HttpEntity entity = response.getEntity();

			// 可以获得响应头
			JSONObject headJson = new JSONObject();
			 Header[] headers = response.getAllHeaders();
			 for (Header header : headers) {
//			 System.out.println(header.getName());
			 headJson.put(header.getName(), header.getValue());
			 }
			 responseJson.put("heads", headJson);
			// 得到响应类型
			// System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				responseJson.put("body", responseContent);
				responseContent = responseJson.toString();
				EntityUtils.consume(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	/**
	 * 发送Get请求
	 * 
	 * @param httpGet
	 * @return
	 */
	private static String sendHttpGet(HttpGet httpGet) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();
			// 配置请求信息
			httpGet.setConfig(requestConfig);

			// 执行请求
			response = httpClient.execute(httpGet);
			// 得到响应实例
			HttpEntity entity = response.getEntity();

			// 可以获得响应头
			// Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
			// for (Header header : headers) {
			// System.out.println(header.getName());
			// }

			// 得到响应类型
			// System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public static String sendHttpPost(String httpUrl) {
		// 创建httpPost
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 */
	public static String sendHttpGet(String httpUrl) {
		// 创建get请求
		HttpGet httpGet = new HttpGet(httpUrl);
		return sendHttpGet(httpGet);
	}
	
	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 */
	public static String sendHttpGet(String httpUrl,String user,String pass) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF, authpref);
        //参数分别为用户名、密码、服务器url、工作域名称
        NTCredentials creds = new NTCredentials(user,pass,httpUrl, null);
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);
		// 创建get请求
		HttpGet httpGet = new HttpGet(httpUrl);
		return sendHttpGet(httpGet);
	}
	
	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 * @throws Exception 
	 */
	public static String sendHttpGetNTLM(String httpUrl,String user,String pass) {
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF, authpref);
        //参数usernamePassword the domain/username:password
        NTCredentials creds = new NTCredentials(user+":"+pass);
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);
      //设置要连接的目标名称、端口
//        HttpHost target = new HttpHost("rdkms.h3c.com", 80, "http");
     // Make sure the same context is used to execute logically related requests
//        HttpContext localContext = new BasicHttpContext();

        
		// 创建get请求
		try {
//	        httpUrl= URLEncoder.encode(httpUrl, "UTF-8");
	        HttpGet httpget = new HttpGet(httpUrl);
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}


	/**
	 * 发送 post请求（带文件）
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 */
	

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 * 
	 */
	public static String sendHttpPost(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (params != null && params.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(params, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param maps
	 *            参数
	 */
	public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
		String parem = convertStringParamter(maps);
		return sendHttpPost(httpUrl, parem);
	}

	/**
	 * 发送 post请求 发送json数据
	 * 
	 * @param httpUrl
	 *            地址
	 * @param paramsJson
	 *            参数(格式 json)
	 * 
	 */
	public static String sendHttpPostJson(String httpUrl, String paramsJson) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求 发送json数据
	 * 
	 * @param httpUrl
	 *            地址
	 * @param paramsJson
	 *            参数(格式 json)
	 * 
	 */
	public static String sendHttpPostJsonAndReturnHeads(String httpUrl, String paramsJson) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPostAndReturnHeads(httpPost);
	}
	
	public static String sendHttpPostAndGetReturnHeads(String httpUrl, String paramsJson, Map<String, String> headMap){
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		
		if(headMap != null){
			for(Entry<String, String> entry : headMap.entrySet()){
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		/*httpPost.addHeader("userId", "l20095");
		httpPost.addHeader("syspwd","IBDSpw123");
		httpPost.addHeader("sysid","IBDS");*/
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPostAndReturnHeads(httpPost);
	}
	public static String sendHttpPostJsonWithHeader(String httpUrl, String paramsJson, Map<String, String> headMap){
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		
		if(headMap != null){
			for(Entry<String, String> entry : headMap.entrySet()){
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		/*httpPost.addHeader("userId", "l20095");
		httpPost.addHeader("syspwd","IBDSpw123");
		httpPost.addHeader("sysid","IBDS");*/
		try {
			// 设置参数
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	
	
	/**
	 * 发送 post请求 发送xml数据
	 * 
	 * @param httpUrl
	 *            地址
	 * @param paramsXml
	 *            参数(格式 Xml)
	 * 
	 */
	public static String sendHttpPostXml(String httpUrl, String paramsXml) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (paramsXml != null && paramsXml.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
	 * 
	 * @param parameterMap
	 *            需要转化的键值对集合
	 * @return 字符串
	 */
	public static String convertStringParamter(Map parameterMap) {
		StringBuffer parameterBuffer = new StringBuffer();
		if (parameterMap != null) {
			Iterator iterator = parameterMap.keySet().iterator();
			String key = null;
			String value = null;
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				if (parameterMap.get(key) != null) {
					value = (String) parameterMap.get(key);
				} else {
					value = "";
				}
				parameterBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					parameterBuffer.append("&");
				}
			}
		}
		return parameterBuffer.toString();
	}
	
	

	public static void main(String[] args) throws Exception {
		// 登录
//		HttpClientUtil client = new HttpClientUtil();
//		String httpUrl = "http://10.98.0.118:9001/api/Signature/Sign";
//		JSONObject json = new JSONObject();
//		json.put("version", "v1");
//		String res = client.sendHttpPostJson(httpUrl, json.toString());
//		System.out.println(res);
//		
//		httpGet = new HttpGet("http://localhost:8080/product/data/ptdata");
//		System.out.println(sendHttpGet(httpGet));
//		sendEosHttpPostJsonWithHeader("http://api.eos-ts.h3c.com/user/v1.0/account/token", "eos_antifake", "eos_antifake", "http://api.eos-ts.h3c.com/orgbaseinfo/v1.0/views/antifake_emps");

//		String httpUrl = "http://rdkms.h3c.com";
//		String uri = "/sites/sharedoc/_api/web/lists/getbytitle('2019ShareDocList')/items?$filter=LastModifyDate%20gt%20%272020-01-01%27%20&$select=DocumentCode,DocumentMainType,Applicant,LastModifyDate,DepartmentLevel1,DepartmentLevel2,DepartmentLevel3,EvaluationLevel,TechContributionScore,MainBusiness,SubBusiness,%20DocumentSubType%20&$orderby=LastModifyDate%20&$top=99999";
//		String uri2 = "filter=ApplicatEndTime%C2%A0gt%20%272020-01-01%27%20and%20CaseType%20eq%20%271^%E8%BD%AF%E4%BB%B6%E7%BB%8F%E9%AA%8C%E6%A1%88%E4%BE%8B%27&$select=DocumentCode,CaseName,CaseAuthor,ChineseName,ApplicatEndTime,DepartmentLevel1,DepartmentLevel2,DepartmentLevel3,CaseValueEvaluation,%C2%A0Applicant,ApplicatTime%20,SummaryAndkeyword%C2%A0&$orderby=ApplicatEndTime%20&$top=99999";
//		String cc = URLDecoder.decode(uri2, "UTF-8");
//		System.out.println("decode==========" + cc);
//		String ab = URLEncoder.encode("^", "UTF-8");
//		System.out.println("encode======" + ab);
//		String username = "Apprdrdtms";
//		String password = "8b/Y+4Gq";
//		NTCredentials creds = new NTCredentials(username + ":" + password);
//		String result = sendHttpGetNTLM(httpUrl + uri, username, password);
//		System.out.println(result);
	}
}