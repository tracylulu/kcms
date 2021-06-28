package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.IsoftProjectListDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 项目信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IsoftProjectListMapper extends BaseMapper<IsoftProjectListDO> {

	void batchEdit(@Param("list") List<IsoftProjectListDO> lstEdit);

	void updateAuditScore();

	void updateAppraiseEva();

}
