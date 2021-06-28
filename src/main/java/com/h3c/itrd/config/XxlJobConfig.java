/**
 * 
 */
package com.h3c.itrd.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;


/**
 * 定时任务调度配置
 * 默认不加载该文件。如果需要ldap配置，在配置文件中增加相关配置，取消@Configuration的注释(added sunliguo)
 */

@Configuration  
public class XxlJobConfig {
	private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

	@Value("${xxl.job.admin.addresses}")
	private String adminAddresses;

	@Value("${xxl.job.executor.appName}")
	private String appName;

	@Value("${xxl.job.executor.ip}")
	private String ip;

	@Value("${xxl.job.executor.port}")
	private int port;

	@Value("${xxl.job.accessToken}")
	private String accessToken;

	@Value("${xxl.job.executor.logpath}")
	private String logPath;

	@Value("${xxl.job.executor.logretentiondays}")
	private int logRetentionDays;

	@Bean(initMethod = "start", destroyMethod = "destroy")
	 public XxlJobSpringExecutor xxlJobExecutor() {
	        logger.info(">>>>>>>>>>> xxl-job config init.");
	        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
	        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
	        xxlJobSpringExecutor.setAppName(appName);
	        xxlJobSpringExecutor.setIp(ip);
	        xxlJobSpringExecutor.setPort(port);
	        xxlJobSpringExecutor.setAccessToken(accessToken);
	        xxlJobSpringExecutor.setLogPath(logPath);
	        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

	        return xxlJobSpringExecutor;
	    }

	
	
}	