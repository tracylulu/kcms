package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.ReviewinfoCloudListDO;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 云产品评审信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ReviewinfoCloudListMapper extends BaseMapper<ReviewinfoCloudListDO> {
	int getReviewCountByParam(@Param("param") Map<String, Object> param);
}
