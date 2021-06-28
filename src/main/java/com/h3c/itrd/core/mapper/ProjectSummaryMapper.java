package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.ProjectSummaryDO;
import com.h3c.itrd.core.vo.ProjectSummaryVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 项目得分汇总表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ProjectSummaryMapper extends BaseMapper<ProjectSummaryDO> {
	List<ProjectSummaryVO> getSoftDevList(@Param("param") Map<String, Object> param);
}
