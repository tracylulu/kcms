package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.DepartmentConfigDO;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 部门数据配置表 Mapper 接口
 * </p>
 *
 * @author c17740
 * @since 2021-06-18
 */
public interface DepartmentConfigMapper extends BaseMapper<DepartmentConfigDO> {

	List<DepartmentConfigDO> selectDeptmentConfigInfo();

}
