package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.DptmanagerListDO;
import com.h3c.itrd.core.entity.EmployeeResignListDO;
import com.h3c.itrd.core.mapper.EmployeeResignListMapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 离职员工信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface EmployeeResignListService extends IService<EmployeeResignListDO> {

	int deleteAllInfo();

	void batchInsertDismissionUser(List<EmployeeResignListDO> employeeResignList);

	void batchSyncDismissionUser(List<Map<String, Object>> dismissionUserList);

}
