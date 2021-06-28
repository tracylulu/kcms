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
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * 解决session失效后ajax请求不能跨域重新验证问题的过滤器
// */
////@Component
//public class FirstFilter implements Filter {
//	//放行的url
//    @Value(value="${sso.main.allowedpath}")
//	private  String ALLOWED_PATHS;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse rep = (HttpServletResponse) response;
//		// 如果请求为根路径或sso不处理交由下游过滤器处理
//		String url = req.getRequestURI();
//		String[] strArray = ALLOWED_PATHS.split(";");
//		if (Arrays.asList(strArray).contains(url)) {
//			chain.doFilter(request, response);
//			return;
//		} else {
//			/*// 如果session为空，重定向的sso页面把复杂请求转为简单请求进行跨域请求
//			if (req.getSession().getAttribute("UserIdentity") == null) {
//
////				rep.sendRedirect("/");
//			} else {
//			}*/
//		    chain.doFilter(request, response);
//
//		}
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//}
