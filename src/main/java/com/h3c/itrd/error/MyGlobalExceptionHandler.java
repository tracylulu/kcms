package com.h3c.itrd.error;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.OperationLogDO;
import com.h3c.itrd.core.service.OperationLogService;
import com.h3c.itrd.util.IPUtils;
import com.h3c.itrd.util.ObjToStrUtil;
import com.h3c.itrd.util.UserUtils;
import com.h3c.sso.entity.RequestTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@Controller
public class MyGlobalExceptionHandler implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

	@Value("${sso.main.auth.key}")
	private String key;
	@Value("${sso.main.login.url}")
	private String ssoMainLogin;
	@Value("${sso.main.login.response.param}")
	private String requestParam;
	@Value("${sso.main.login.response.param}")
	private String responseParam;
	@Value("${sso.main.logout.url}")
	private String ssoMainlogout;
	@Value("${sso.main.siteid}")
	private String siteId;
	@Value("${sso.main.url}")
	private String siteMainUrl;
	@Autowired
	OperationLogService operationLogService;
	@Autowired
	private ErrorAttributes errorAttributes;

	private static final String ERROR_PATH = "/error";

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	/**
	 * JSON格式错误信息
	 */
	@RequestMapping(value = ERROR_PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
	
	@ExceptionHandler(Exception.class)
	public ResponseResult error(HttpServletRequest request, WebRequest webRequest) {
		Map<String, Object> body = this.errorAttributes.getErrorAttributes(webRequest, true);
		String url = request.getRequestURL().toString();
		String errMsg = ObjToStrUtil.replaceNullValue(body.get("message"));
		logger.info("-----kcms GlobalException------当前时间："+new Date()+"-----kcms GlobalException------错误："+errMsg);
		if (StringUtils.isNotBlank(errMsg) && errMsg.contains("登录")) {
			OperationLogDO operLog = new OperationLogDO();
			operLog.setModelCode("com.h3c.platform.interceptor.preHandle");
			operLog.setModelName("拦截器拦截登录错误");
			operLog.setSummary(url);
			operLog.setContent(errMsg);
			operLog.setUserId(UserUtils.getCurrentDomainAccount());
			operLog.setIp(IPUtils.getIpAddr(request));
			operLog.setLogTime(new Date());
			operationLogService.insertLog(operLog);
		}else {
			OperationLogDO operLog = new OperationLogDO();
			operLog.setModelCode("com.h3c.platform.interceptor.preHandle");
			operLog.setModelName("拦截器拦截其他错误");
			operLog.setSummary(url);
			operLog.setContent(errMsg);
			operLog.setUserId(UserUtils.getCurrentDomainAccount());
			operLog.setIp(IPUtils.getIpAddr(request));
			operLog.setLogTime(new Date());
			operationLogService.insertLog(operLog);
		}
		return ResponseResult.fail(errMsg);

	}

	/*private static int getStatus(HttpServletRequest response) {
		Integer status = (Integer) response.getAttribute("javax.servlet.error.status_code");
		if (status != null) {
			return status;
		}

		return 500;
	}*/

//	/**
//	 * 拦截Exception类的异常
//	 *
//	 * @param e
//	 * @return
//	 */
//	@ExceptionHandler(Exception.class)
//	public ResponseResult exceptionHandler(HttpServletRequest req, Exception ex) {
//		ex.printStackTrace();
//		if (StringUtils.isNotBlank(ex.getMessage()) && ex.getMessage().contains("登录")) {
//			String requestTicket = null;
//			String url = req.getRequestURL().toString();
//			try {
//				RequestTicket ticket = RequestTicket.getInstance();
//				ticket.setSiteId(siteId);
//				ticket.setReturnUrl(siteMainUrl);
//				requestTicket = ticket.serialize(key);
//				String loginUrl = ssoMainLogin + "?" + requestParam + "=" + requestTicket;
//				return ResponseResult.success(loginUrl);
//			} catch (Exception e) {
//				return ResponseResult.fail(e.getMessage());
//			}
//		}
//		return ResponseResult.fail("操作失败");
//	}
//
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseResult exceptionHandler(HttpServletRequest req, RuntimeException ex) {
//		ex.printStackTrace();
//		// 判断是否包含登录错误
//		if (StringUtils.isNotBlank(ex.getMessage()) && ex.getMessage().contains("登录")) {
//			String requestTicket = null;
//			String url = req.getRequestURL().toString();
//			OperationLog log = new OperationLog();
//			log.setModelcode("com.h3c.platform.interceptor.preHandle");
//			log.setModelname("拦截器");
//			log.setSummary(url);
//			log.setContent(ex.getMessage());
//			// log.setUserid(UserUtils.getCurrentUserId());
//			log.setLogtype(LogType.LOGIN);
//			try {
//				log.setIp("IP:" + IPUtils.getIpAddr(req) + ";service:" + InetAddress.getLocalHost().getHostAddress());
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//			}
//			operationLogService.saveLog(log);
//			try {
//				RequestTicket ticket = RequestTicket.getInstance();
//				ticket.setSiteId(siteId);
//				ticket.setReturnUrl(siteMainUrl);
//				requestTicket = ticket.serialize(key);
//				String loginUrl = ssoMainLogin + "?" + requestParam + "=" + requestTicket;
//				return ResponseResult.loginOut(loginUrl);
//			} catch (Exception e) {
//				return ResponseResult.fail(e.getMessage());
//			}
//		}
//		return ResponseResult.fail(ex.getMessage());
//	}
//
//	@ExceptionHandler(JWTVerificationException.class)
//	public ResponseResult exceptionHandler1(HttpServletRequest req, RuntimeException ex) {
//		String requestTicket = null;
//		String url = req.getRequestURL().toString();
//		OperationLog log = new OperationLog();
//		log.setModelcode("com.h3c.platform.interceptor.preHandle");
//		log.setModelname("拦截器");
//		log.setSummary(url);
//		log.setContent(ex.getMessage());
//		log.setLogtype(LogType.LOGIN);
//		try {
//			log.setIp("IP:" + IPUtils.getIpAddr(req) + ";service:" + InetAddress.getLocalHost().getHostAddress());
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//		operationLogService.saveLog(log);
//		try {
//			RequestTicket ticket = RequestTicket.getInstance();
//			ticket.setSiteId(siteId);
//			ticket.setReturnUrl(siteMainUrl);
//			requestTicket = ticket.serialize(key);
//			String loginUrl = ssoMainLogin + "?" + requestParam + "=" + requestTicket;
//			return ResponseResult.loginOut(loginUrl);
//		} catch (Exception e) {
//			return ResponseResult.fail(ex.getMessage());
//		}
//	}
}
