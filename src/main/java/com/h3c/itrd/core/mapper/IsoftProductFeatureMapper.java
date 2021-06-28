package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.IsoftProductFeatureDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Comware产品的特性审计结果 Mapper 接口
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
public interface IsoftProductFeatureMapper extends BaseMapper<IsoftProductFeatureDO> {

	void batchInsert(@Param(value="list")List<IsoftProductFeatureDO> lst);

	Integer selectCount();

	void deleteAllInfo();

}
