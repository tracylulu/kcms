package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.LoginSummaryDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统登陆统计表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface LoginSummaryService extends IService<LoginSummaryDO> {
	int add(LoginSummaryDO loginSummaryDO);
}
