package com.h3c.itrd.common.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.h3c.itrd.core.entity.OperationLogDO;
import com.h3c.itrd.core.service.OperationLogService;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.util.IPUtils;
import com.h3c.itrd.util.UserUtils;

import io.swagger.annotations.ApiOperation;

@Aspect
@Component
public class OperLogAspect {
	@Autowired
	OperationLogService operationLogService;
	@Value("${spring.application.name}")
	String appName;
	/**
	* 设置操作日志切入点 记录操作日志 在注解的位置切入代码
	*/
	@Pointcut("@annotation(com.h3c.itrd.common.annotation.OperLog)")
	public void operLogPoinCut() {
	}
	
	/**
	* 设置操作异常切入点记录异常日志 扫描所有controller包下操作
	*/
	@Pointcut("execution(public * com.h3c.itrd.*.controller..*.*(..))")
	public void operExceptionLogPoinCut() {
	}
	
	@AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
	public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) { 
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		OperationLogDO operLog = new OperationLogDO();
		try {
			//方法名
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			//获取切入点所在的方法
			Method method=signature.getMethod();
			//获取请求类名
			String className = joinPoint.getTarget().getClass().getName();
			// 获取请求的方法名（包）
			String methodName = method.getName();
			methodName = className + "." + methodName;
			//获取异常详细信息
			String content= ExceptionUtils.getFullStackTrace(e);
			//获取操作模块名称信息
			String modelname="";
			if (method.isAnnotationPresent(OperLog.class)) {
				OperLog opLog=method.getAnnotation(OperLog.class);
				modelname = opLog.operModelName();
				content =opLog.operContent()+";details:"+content;
			}
			//获取操作uri
			String url=request.getRequestURI().toString(); 
			//获取操作人账号
			String userId = UserUtils.getCurrentDomainAccount();
			if (userId==null ||"".equals(userId)) {
				userId=appName;
			}
			operLog.setModelCode(methodName);
			operLog.setModelName(modelname);
			operLog.setSummary(url);
			operLog.setContent(content);
			operLog.setUserId(userId);
			operLog.setIp(IPUtils.getIpAddr(request));
			operLog.setLogTime(new Date());
			operationLogService.insertLog(operLog);
		} catch (Exception e2) {
			e2.printStackTrace();
			}
	}
	
	@AfterReturning(value = "operLogPoinCut()", returning = "keys")
	public void saveOperLog(JoinPoint joinPoint, Object keys) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		OperationLogDO operLog = new OperationLogDO();
		try {
			//方法名
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			//获取切入点所在的方法
			Method method=signature.getMethod();
			// 获取操作
			OperLog opLog = method.getAnnotation(OperLog.class);
			if (opLog != null) {
				String modelName = opLog.operModelName();
				String content = opLog.operContent();
				operLog.setModelName(modelName); // 操作模块
				operLog.setContent(content); // 操作类型
				}
			//获取请求类名
			String className = joinPoint.getTarget().getClass().getName();
			// 获取请求的方法名（包）
			String methodName = method.getName();
			methodName = className + "." + methodName;
			//获取操作uri
			String url=request.getRequestURI().toString();
			//获取操作人账号
			String userId = UserUtils.getCurrentDomainAccount();
			if (userId==null ||"".equals(userId)) {
				userId=appName;
			}
			operLog.setModelCode(methodName);
			operLog.setSummary(url);
			operLog.setUserId(userId);
			operLog.setIp(IPUtils.getIpAddr(request));
			operLog.setLogTime(new Date());
			operationLogService.insertLog(operLog);
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

}
