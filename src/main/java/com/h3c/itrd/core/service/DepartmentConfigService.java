package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.DepartmentConfigDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门数据配置表 服务类
 * </p>
 *
 * @author c17740
 * @since 2021-06-18
 */
public interface DepartmentConfigService extends IService<DepartmentConfigDO> {

	List<DepartmentConfigDO> selectDeptmentConfigInfo();

}
