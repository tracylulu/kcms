package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.ReviewSummaryDO;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 评审得分统计表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ReviewSummaryMapper extends BaseMapper<ReviewSummaryDO> {
	//软件硬件通用方法
    int deleteByQuarterAndType(@Param("param") Map<String, Object> param);
}
