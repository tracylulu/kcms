package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.AdminListDO;
import com.h3c.itrd.core.mapper.AdminListMapper;
import com.h3c.itrd.core.service.AdminListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员列表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class AdminListServiceImpl extends ServiceImpl<AdminListMapper, AdminListDO> implements AdminListService {
	@Autowired
	private AdminListMapper adminListMapper;

	@Override
	public int add(AdminListDO adminListDO) {
		return adminListMapper.insert(adminListDO);
	}

	@Override
	public AdminListDO getById(String id) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        return adminListMapper.selectOne(queryWrapper);
	}
	
}
