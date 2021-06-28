package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.DptmanagerListDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门领导表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface DptmanagerListService extends IService<DptmanagerListDO> {
	String getLevelById(String id);
	
	List<DptmanagerListDO> getDptManagerBydptcode(String dptcode);

	void batchInsertDeptManager(List<DptmanagerListDO> dptmanagerList);

	int deleteAllInfo();

	void batchSyncDeptManager(List<Map<String, Object>> deptManagerList);
	
	List<DptmanagerListDO> getById(String id);

	int deleteAllSyncInfo();
}
