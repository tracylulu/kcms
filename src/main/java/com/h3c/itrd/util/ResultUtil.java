package com.h3c.itrd.util;

import com.h3c.itrd.common.consts.StatusCodeEnum;
import com.h3c.itrd.common.entity.Result;

public class ResultUtil {
    public static <T> Result<T> createResult(boolean flag, StatusCodeEnum statusCodeEnum){
        return new Result<T>(flag, statusCodeEnum);
    }
    
    public static <T> Result<T> createTrueResult(StatusCodeEnum statusCodeEnum, T res){
        return new Result<T>(true, statusCodeEnum, res);
    }
    
    public static <T> Result<T> createTrueResult(StatusCodeEnum statusCodeEnum){
        return createTrueResult(statusCodeEnum, null);
    }
    
    public static <T> Result<T> createFalseResult(StatusCodeEnum statusCodeEnum){
        return createResult(false, statusCodeEnum);
    }
    
    public static <T> Result<T> createFalseResult(StatusCodeEnum statusCodeEnum, T res){
        return createResult(false, statusCodeEnum, res);
    }
    
    public static <T> Result<T> createResult(boolean flag, StatusCodeEnum statusCodeEnum, T data) {
        return new Result<>(flag, statusCodeEnum, data);
    }
}
