package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.AddScoreAuditDO;
import com.h3c.itrd.core.vo.AddScoreAuditListResVO;
import com.h3c.itrd.core.vo.AddScoreAuditResVO;
import com.h3c.itrd.core.vo.AddScoreReplyListVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 关键事件审核表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface AddScoreAuditMapper extends BaseMapper<AddScoreAuditDO> {
	List<AddScoreReplyListVO> addScoreReplyList(@Param("param") Map<String, Object> param);
	
	List<AddScoreAuditListResVO> addScoreAuditListByStatus(@Param("param") Map<String, Object> param);
	
	AddScoreAuditResVO getAddScoreAuditById(@Param("param") Map<String, Object> param);
}
