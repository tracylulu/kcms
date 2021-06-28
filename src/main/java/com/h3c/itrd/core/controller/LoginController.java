
package com.h3c.itrd.core.controller;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.annotation.PassToken;
import com.h3c.itrd.common.consts.LogType;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.config.JWTConfig;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.sso.entity.RequestTicket;
import com.h3c.sso.entity.ResponseTicket;


@Api(tags = "登录管理", value = "登录管理")
//@RequestMapping("")
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${sso.main.auth.key}")
    private String key;
    @Value("${sso.main.login.url}")
    private String ssoMainLogin;
    @Value("${sso.main.login.request.param}")
    private String requestParam;
    @Value("${sso.main.login.response.param}")
    private String responseParam;
    @Value("${sso.main.logout.url}")
    private String ssoMainlogout;
    @Value("${sso.main.siteid}")
    private String siteId;
    @Value("${sso.main.url}")
    private String siteMainUrl;
    @Autowired
    private JWTConfig jwtConfig;
    @Autowired
	private EmployeeListService employeeListService;
    
    @PassToken(logType=LogType.LOGIN)
	@ApiOperation(value = "UI免密登录", notes = "UI免密登录")
	@GetMapping(value = "/login")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userCode", value = "用户域账号") })
	@OperLog(operModelName="UI免密登录",operContent="UI免密登录")
	@ResponseBody
	public ResponseResult login(String userCode, HttpServletRequest request) {
		if (StringUtils.isNotBlank(userCode)) {
			try {
				//校验系统用户存不存在
				EmployeeListDO employee = employeeListService.getEmployeeByDomainid(userCode);
				if(employee==null) {
					return ResponseResult.fail("用户不存在！");
				}
				// 添加登录日志
				// loginLogService.create(userCode, IPUtils.getIpAddr(request));
				logger.info(jwtConfig.getToken(userCode)); 
				Map<String,String> map= new HashMap<>();
				map.put("token", jwtConfig.getToken(userCode));
				map.put("id", employee.getId());
				map.put("domainAccount", employee.getAccount());
				map.put("name", employee.getName());
				return ResponseResult.success(map);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("sso解析票据错误", e);
				return ResponseResult.fail("登录失败");
			}
		}else {
			return ResponseResult.fail("userCode为空");
		}
	} 
    
    @PassToken(logType=LogType.LOGIN)
	@ApiOperation(value = "sso登录", notes = "sso登录")
    @PostMapping("/ssoLogin")
	@OperLog(operModelName="sso登录",operContent="sso登录")
    @ResponseBody
    public ResponseResult ssoLogin(@RequestBody JSONObject obj,HttpServletRequest request, HttpServletResponse response){
    	String data = obj.toJSONString();
		// 解析json数据
		JSONObject json = JSON.parseObject(data);
		String responseTicket = json.getString("responseTicket");
		String url = json.getString("url");

		if (StringUtils.isNotBlank(responseTicket)) {
			ResponseTicket repTicket = null;
			try {
				repTicket = ResponseTicket.deserialize(responseTicket, key);
				logger.info("repTicket ====" + repTicket);
				logger.info("repTicket ====" + repTicket.getUserIdentity());
				String userCode = repTicket.getUserIdentity();
				logger.info(repTicket.getExpireTime().toString());
				//校验系统用户存不存在
				EmployeeListDO employee = employeeListService.getEmployeeByDomainid(userCode);
				if(employee==null) {
					return ResponseResult.fail("用户不存在！");
				}
				// 添加登录日志
				logger.info(jwtConfig.getToken(userCode)); 
				Map<String,String> map= new HashMap<>();
				map.put("token", jwtConfig.getToken(userCode));
				map.put("id", employee.getId());
				map.put("domainAccount", employee.getAccount());
				map.put("name", employee.getName());
				return ResponseResult.success(map);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("sso解析票据错误", e);
				return ResponseResult.fail("登录失败");
			}

		} else {
			String requestTicket = null;
			try {
				RequestTicket ticket = RequestTicket.getInstance();
				ticket.setSiteId(siteId);
				ticket.setReturnUrl(url);
				requestTicket = ticket.serialize(key);
				String loginUrl = ssoMainLogin + "?" + requestParam + "=" + requestTicket;
				return ResponseResult.success(loginUrl);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("sso序列化失败", e);
				return ResponseResult.fail("登录失败");
			}

		}
    }
    
    
    @PassToken(logType=LogType.LOGOUT)
    @ApiOperation(value = "退出", notes = "退出")
    @PostMapping("/logout")
    @OperLog(operModelName="退出",operContent="退出")
    @ResponseBody
    public ResponseResult logout(@RequestBody JSONObject obj){
    		String requestTicket = null;
    		String data = obj.toJSONString();
    		// 解析json数据
    		JSONObject json = JSON.parseObject(data);	
    		String url = json.getString("url");
//    		try {
//    			RequestTicket ticket = RequestTicket.getInstance();
//    			ticket.setSiteId(siteId);
//    			ticket.setReturnUrl(url);
//    			requestTicket = ticket.serialize(key);			
//    		} catch (Exception e) {
//    			e.printStackTrace();
//    			logger.error("sso序列化失败", e);
//    			return ResponseResult.fail("登录失败");
//    		}
//    		String loginUrl = ssoMainLogin + "?" + requestParam + "=" + requestTicket;
    		
    		String loginOutUrl = ssoMainlogout+"?siteId="+siteId+"&RequestUrl="+url;
    		return ResponseResult.success(loginOutUrl);
    	}
    
    
}
