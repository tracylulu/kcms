package com.h3c.itrd.authority;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.h3c.itrd.common.annotation.PassToken;
import com.h3c.itrd.common.entity.ResponseResult;
//import com.h3c.itrd.common.entity.Result;
import com.h3c.itrd.config.JWTConfig;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.service.EmployeeListService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;


/**
* Token 拦截器
*/
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
	
    private static Logger log = LoggerFactory.getLogger(TokenInterceptor.class);
    
    @Value("${checkToken}")
	private String checkToken;
    @Resource
    private JWTConfig jwtConfig ;
    @Value(value="${sso.main.allowedpath}")
    private  String ALLOWED_PATHS;
    @Autowired
	private EmployeeListService employeeListService;
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
    
        
        // Token 验证
        String token = request.getHeader(jwtConfig.getHeader());
        String uri = request.getRequestURI();
        log.info("kcms  -- uri     " + uri);
        String[] strArray = ALLOWED_PATHS.split(";");
        if(StringUtils.isBlank(token) && Arrays.asList(strArray).contains(uri)) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token");           
            return true;
        }
        // 如果不是映射到方法直接通过
        if(!(handler  instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证（免密登录，sso登录，退出这几个接口不需要验证token的，所以加上了这个注解）
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if ("true".equals(checkToken)) {
            // 执行认证
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            Claims claims = jwtConfig.getTokenClaim(token);
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
            	//returnResult(response);    
            	throw new JwtException("登录已过期，请重新登录");
            }else{
            	//sso登录使用
                //String domainAccount = UserUtils.getCurrentDomainAccountByTicket(claims.getSubject());
            	//免密登录使用(sso登录页可以用)
            	String domainAccount = claims.getSubject();
            	EmployeeListDO employee = employeeListService.getEmployeeByDomainid(domainAccount);
            	//域账号
            	request.setAttribute("account", domainAccount);
            	//工号
            	request.setAttribute("id", employee.getId());
            }
            return true;   
        }
        return true;    
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
        
    }
    
    private void returnJson(HttpServletResponse response) {
    	PrintWriter writer = null;
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json;charset=utf-8");
    	try {
    		writer = response.getWriter();
    		ResponseResult res=ResponseResult.fail(false, jwtConfig.getHeader() + "失效，请重新获取token");
    		//Result res = new Result(false,20801,jwtConfig.getHeader() + "失效，请重新获取token");
    		writer.print(JSON.toJSON(res));
//    		writer.flush();
    	}catch(IOException e) {
    		e.printStackTrace();
    		System.out.println(jwtConfig.getHeader() + "失效，请重新获取token");
    	}finally {
    		if(writer != null) {
    			writer.close();
    		}
    	}
    }

    private ResponseResult returnResult(HttpServletResponse response) {
    	ResponseResult res=ResponseResult.fail(false, jwtConfig.getHeader() + "失效，请重新获取token");
		return res;
    }
 
}
