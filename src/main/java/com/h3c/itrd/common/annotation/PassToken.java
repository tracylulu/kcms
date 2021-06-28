package com.h3c.itrd.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.h3c.itrd.common.consts.LogType;


/**
 * 不需要token验证注解
 * @author lkf7579
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
    
    int logType() default LogType.LOGIN;
}