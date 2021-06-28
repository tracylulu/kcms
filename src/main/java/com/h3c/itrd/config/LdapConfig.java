package com.h3c.itrd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * LDAP配置
 * 默认不加载该文件。如果需要ldap配置，在配置文件中增加相关配置，取消@Configuration的注释。
 * @author s21046
 * @date 2021年4月28日
 */
//@Configuration 
public class LdapConfig {

    @Value("${LDAP_AUTHENTICATION}")
    private String ldapAuthentication;
    @Value("${LDAP_URL}")
    private String ldapUrl;
    @Value("${LDAP_Name}")
    private String ldapName;
    @Value("${LDAP_PWD}")
    private String ldapPassword;
    public String getLdapAuthentication() {
        return ldapAuthentication;
    }
    public void setLdapAuthentication(String ldapAuthentication) {
        this.ldapAuthentication = ldapAuthentication;
    }
    public String getLdapUrl() {
        return ldapUrl;
    }
    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }
    public String getLdapName() {
        return ldapName;
    }
    public void setLdapName(String ldapName) {
        this.ldapName = ldapName;
    }
    public String getLdapPassword() {
        return ldapPassword;
    }
    public void setLdapPassword(String ldapPassword) {
        this.ldapPassword = ldapPassword;
    }
    
    
}
