package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.DepartmentConfigDO;
import com.h3c.itrd.core.mapper.DepartmentConfigMapper;
import com.h3c.itrd.core.service.DepartmentConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门数据配置表 服务实现类
 * </p>
 *
 * @author c17740
 * @since 2021-06-18
 */
@Service
public class DepartmentConfigServiceImpl extends ServiceImpl<DepartmentConfigMapper, DepartmentConfigDO> implements DepartmentConfigService {
	@Autowired
	private DepartmentConfigMapper departmentConfigMapper;
	
	@Override
	public List<DepartmentConfigDO> selectDeptmentConfigInfo() {
		return departmentConfigMapper.selectDeptmentConfigInfo();
	}

}
