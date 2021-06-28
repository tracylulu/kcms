package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.AdminListDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员列表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface AdminListService extends IService<AdminListDO> {
	int add(AdminListDO adminListDO);
	
	AdminListDO getById(String id);
	
	
	
}
