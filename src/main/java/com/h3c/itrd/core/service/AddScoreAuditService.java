package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.AddScoreAuditDO;
import com.h3c.itrd.core.vo.AddScoreAuditListResVO;
import com.h3c.itrd.core.vo.AddScoreAuditResVO;
import com.h3c.itrd.core.vo.AddScoreReplyListVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 关键事件审核表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface AddScoreAuditService extends IService<AddScoreAuditDO> {
	List<AddScoreReplyListVO> addScoreReplyList(Map<String, Object> param);
	
	int add(AddScoreAuditDO addScoreAuditDO);
	
	List<AddScoreAuditListResVO> addScoreAuditListByStatus(Map<String, Object> param);
	
	AddScoreAuditResVO getAddScoreAuditById(Map<String, Object> param);
	
	int updateAuditById(AddScoreAuditDO addScoreAuditDO);
}
