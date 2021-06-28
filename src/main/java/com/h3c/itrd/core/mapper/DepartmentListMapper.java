package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.DepartmentListDO;
import com.h3c.itrd.core.vo.DepartmentListForAdminVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 部门列表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface DepartmentListMapper extends BaseMapper<DepartmentListDO> {
	List<DepartmentListForAdminVO> getListForAdmin();

	int deleteAllInfo();

	void batchInsertDeptInfo(@Param("list") List<DepartmentListDO> employeeResignList);

	int batchLogicDelDeptInfo(@Param("list") List<DepartmentListDO> dptInfoList);

	int batchUpdateDeptInfo(@Param("list") List<DepartmentListDO> dptInfoList);
}
