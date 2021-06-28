package com.h3c.itrd.web.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.h3c.sso.entity.ResponseTicket;
@Component
public class SSOUtils {

    private static final Logger logger = LoggerFactory.getLogger(SSOUtils.class);
    
    @Value("${sso.main.auth.key}")
    private String key;
    @Value("${sso.main.login.url}")
    private String ssoMainLogin;
    @Value("${sso.main.login.request.param}")
    private String requestParam;
    @Value("${sso.main.login.response.param}")
    private String responseParam;
    
    public  String getDomainAccount(String responseTicket){
//        System.out.println("tickt---" + responseTicket);
        ResponseTicket repTicket = null;
        String userAcc = null;
       
        try {
            repTicket = ResponseTicket.deserialize(responseTicket, key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("sso解析票据错误", e);
        }
        if (repTicket != null && repTicket.getUserIdentity() != null
                && !"".equals(repTicket.getUserIdentity())) {
            // 取出UserIdentity放入session
            userAcc = repTicket.getUserIdentity();
            // Subject subject = SecurityUtils.getSubject();
            // subject.login(new UsernamePasswordToken(userAcc,""));
        }
        return userAcc;
    }
}
