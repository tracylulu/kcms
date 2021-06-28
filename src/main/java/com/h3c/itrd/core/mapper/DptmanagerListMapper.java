package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.DptmanagerListDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 部门领导表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface DptmanagerListMapper extends BaseMapper<DptmanagerListDO> {
	String getLevelById(@Param("id") String id);

	void batchInsertDeptManager(@Param ("list")List<DptmanagerListDO> dptmanagerList);

	int deleteAllInfo();

	int deleteAllSyncInfo();
}
