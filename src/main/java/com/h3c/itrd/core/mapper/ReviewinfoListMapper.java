package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.ReviewinfoListDO;
import com.h3c.itrd.core.vo.ReviewinfoListResVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 评审信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ReviewinfoListMapper extends BaseMapper<ReviewinfoListDO> {
	List<ReviewinfoListResVO> getReviewinfoListByParam(@Param("param") Map<String, Object> param);
}
