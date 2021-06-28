package com.h3c.itrd.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.h3c.itrd.core.entity.AddScoreDO;
import com.h3c.itrd.core.vo.AddScoreResVO;

/**
 * <p>
 * 关键事件得分表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface AddScoreMapper extends BaseMapper<AddScoreDO> {
	List<AddScoreResVO> getAddScoreListByParam(@Param("param") Map<String, Object> param);
	
	List<AddScoreResVO> getAddScoreDetailByParam(@Param("param") Map<String, Object> param);

    //计算季度得分中的关键事件分
    List<AddScoreResVO> getListForTotalScore(@Param("param") Map<String, Object> param);
}
