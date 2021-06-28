package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.OperationLogDO;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface OperationLogMapper extends BaseMapper<OperationLogDO> {

	void insertLog(@Param("param") OperationLogDO log);

}
