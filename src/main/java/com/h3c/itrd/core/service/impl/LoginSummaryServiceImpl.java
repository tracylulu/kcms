package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.LoginSummaryDO;
import com.h3c.itrd.core.mapper.LoginSummaryMapper;
import com.h3c.itrd.core.service.LoginSummaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统登陆统计表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class LoginSummaryServiceImpl extends ServiceImpl<LoginSummaryMapper, LoginSummaryDO> implements LoginSummaryService {
	@Autowired
	private LoginSummaryMapper loginSummaryMapper;

	@Override
	public int add(LoginSummaryDO loginSummaryDO) {
		return loginSummaryMapper.insert(loginSummaryDO);
	}
}
