package com.h3c.itrd.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.h3c.itrd.web.util.SSOUtils;

public class UserUtils {

    public static String getCurrentDomainAccount(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return (String) request.getAttribute("account");
        } catch (Exception e) {
            return "system";
        }
    }
    
    public static String getCurrentUserId(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return (String) request.getAttribute("id");
        } catch (Exception e) {
            return "system";
        }
    }
    
    public static String getCurrentDomainAccountByTicket(String responseTicket){
        SSOUtils ssoUtils = (SSOUtils)SpringUtil.getBean(SSOUtils.class);
        
        String domainAccount = ssoUtils.getDomainAccount(responseTicket);
        
        return domainAccount;
    }
}
