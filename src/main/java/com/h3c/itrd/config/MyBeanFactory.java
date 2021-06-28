package com.h3c.itrd.config;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactory implements BeanFactoryPostProcessor{
	
	Logger Logger = org.slf4j.LoggerFactory.getLogger(MyBeanFactory.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("自定义工厂类");
		Logger.error("<<<<<<<<<<<<<<<<<<hjhahha a ");
		
	}

}
