package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.EmployeeListDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 员工信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface EmployeeListMapper extends BaseMapper<EmployeeListDO> {

	int batchInsertEmployeeInfo(@Param("list") List<EmployeeListDO> employeeInfoList);

	int batchUpdateEmployeeInfo(@Param("list") List<EmployeeListDO> employeeInfoList);

	int batchUpdateEmployeeInfoForSys(@Param("list") List<EmployeeListDO> employeeInfoList);
	
	int deleteAllSyncEmployeeInfo();

}
