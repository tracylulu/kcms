package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.IsoftProductIdentificationDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Comware产品的代码鉴定结果 Mapper 接口
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
public interface IsoftProductIdentificationMapper extends BaseMapper<IsoftProductIdentificationDO> {

	Integer selectCount();

	void deleteAllInfo();

	void batchInsert(@Param(value="list")List<IsoftProductIdentificationDO> lst);

}
