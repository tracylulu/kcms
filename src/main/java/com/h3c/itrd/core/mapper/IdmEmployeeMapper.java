package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.IdmEmployeeDO;
import com.h3c.itrd.core.vo.IdmEmployeeVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 软件开发问题单得分表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IdmEmployeeMapper extends BaseMapper<IdmEmployeeDO> {
	List<IdmEmployeeVO> getSoftMaintainList(@Param("param") Map<String, Object> param);
}
