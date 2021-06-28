package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.DepartmentListDO;
import com.h3c.itrd.core.vo.DepartmentListForAdminVO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门列表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface DepartmentListService extends IService<DepartmentListDO> {
	DepartmentListDO getListByDptcode(String dptcode);
	List<DepartmentListDO> getListBySubDptcode(String subdptcode);
	List<DepartmentListForAdminVO> getListForAdmin();
	void batchSyncDeptInfo(List<Map<String, Object>> deptInfoList);
	int deleteAllInfo();
	void batchInsertDeptInfo(List<DepartmentListDO> employeeResignList);
	int batchLogicDelDeptInfo(List<DepartmentListDO> dptInfoList);
	int batchUpdateDeptInfo(List<DepartmentListDO> dptInfoList);
	
}
