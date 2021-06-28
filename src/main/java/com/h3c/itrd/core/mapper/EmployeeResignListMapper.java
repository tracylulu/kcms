package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.DptmanagerListDO;
import com.h3c.itrd.core.entity.EmployeeResignListDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 离职员工信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface EmployeeResignListMapper extends BaseMapper<EmployeeResignListDO> {

	int deleteAllInfo();

	void batchInsertDismissionUser( @Param("list") List<EmployeeResignListDO> employeeResignList);

}
