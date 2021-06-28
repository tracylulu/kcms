package com.h3c.itrd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.h3c.itrd.authority.TokenInterceptor;


/**
 * 框架拦截器配置
 * @author s21046
 * @date 2021年4月28日
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	@Autowired
	private TokenInterceptor tokenInterceptor ;
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		
		/* 
		 * 系统token验证拦截  
		 * 排除拦截  
		 * 1.不拦截swagger.
		 * 2.不拦截获取token的请求,提供颁发token.
		 * 3.(重要!)不拦截心跳检查接口，避免在eos平台部署后无法获取服务心跳，导致‘部署检查’失败，进而销毁容器.
		 * 
		 * */
		registry.addInterceptor(tokenInterceptor).addPathPatterns("/*/**")
		                                         .excludePathPatterns("/swagger/**")
		                                         .excludePathPatterns("/healthy");
		/* 其他拦截器可在此配置  */
		//{for example} :    registry.addInterceptor(paramsCheck4AddInterceptor);


	
		super.addInterceptors(registry);
	}

}
