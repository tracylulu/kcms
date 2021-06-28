package com.h3c.itrd.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.h3c.itrd.common.annotation.PassToken;
import com.h3c.itrd.common.consts.LogType;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.config.JWTConfig;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.service.EmployeeListService;


/**
* @author s21046
* @date 2021年1月31日
*/
@RestController
@RequestMapping("/token")
@Api(tags = {"系统认证"}, value = "系统之间交互，身份校验")
public class TokenController {
    private static Logger logger = LoggerFactory.getLogger(TokenController.class);
    
    @Autowired
	private EmployeeListService employeeListService;
	@Autowired
	private JWTConfig jwtConfig;
         
	@PassToken(logType=LogType.LOGIN)
    @GetMapping("/getToken")
    @ApiOperation(value = "获取token")
    @ResponseBody
    public ResponseResult getToken(HttpServletRequest request, String userCode){
		if (StringUtils.isNotBlank(userCode)) {
			//ResponseTicket repTicket = null;
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
				map.put("code", employee.getId());
				map.put("account", employee.getAccount());
				map.put("name", employee.getName());
				return ResponseResult.success(map);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("sso解析票据错误", e);
				return ResponseResult.fail("登录失败");
			}

		} else {
			
			return ResponseResult.fail("userCode参数错误");
		}           
    } 
    
  

}



