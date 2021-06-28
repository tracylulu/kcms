//package com.h3c.itrd.web.filter;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
////@Component
//public class SSOFilter implements Filter {
//
//	private static final Logger logger = LoggerFactory.getLogger(SSOFilter.class);
//
//	@Value("${sso.main.auth.key}")
//	private String key;
//	@Value("${sso.main.login.url}")
//	private String ssoMainLogin;
//	@Value("${sso.main.login.request.param}")
//	private String requestParam;
//	@Value("${sso.main.login.response.param}")
//	private String responseParam;
//	@Value("${sso.main.logout.url}")
//	private String ssoMainlogout;
//	@Value("${sso.main.siteid}")
//	private String siteId;
//	@Value("${sso.main.url}")
//	private String siteMainUrl;
//	//放行的url
//	@Value(value="${sso.main.allowedpath}")
//	private String ALLOWED_PATHS;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		HttpServletRequest req = (HttpServletRequest) request;
////		logger.info("web过滤器执行,uri:" + req.getRequestURI() + ",url:" + req.getRequestURL());
//		// 如果请求为根路径或sso不处理交由下游过滤器处理
//		String url = req.getRequestURI();
//		String[] strArray = ALLOWED_PATHS.split(";");
//		if (Arrays.asList(strArray).contains(url)) {
//			chain.doFilter(request, response);
//			return;
//		}
//		chain.doFilter(request, response);
//		
//	}
//
//	
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//
//	}
//
//}
