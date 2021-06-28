package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.AddScoreAuditDO;
import com.h3c.itrd.core.mapper.AddScoreAuditMapper;
import com.h3c.itrd.core.service.AddScoreAuditService;
import com.h3c.itrd.core.vo.AddScoreAuditListResVO;
import com.h3c.itrd.core.vo.AddScoreAuditResVO;
import com.h3c.itrd.core.vo.AddScoreReplyListVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 关键事件审核表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class AddScoreAuditServiceImpl extends ServiceImpl<AddScoreAuditMapper, AddScoreAuditDO> implements AddScoreAuditService {
	@Autowired
	private AddScoreAuditMapper addScoreAuditMapper;

	@Override
	public List<AddScoreReplyListVO> addScoreReplyList(Map<String, Object> param) {
		return addScoreAuditMapper.addScoreReplyList(param);
	}

	@Override
	public int add(AddScoreAuditDO addScoreAuditDO) {
		return addScoreAuditMapper.insert(addScoreAuditDO);
	}

	@Override
	public List<AddScoreAuditListResVO> addScoreAuditListByStatus(Map<String, Object> param) {
		return addScoreAuditMapper.addScoreAuditListByStatus(param);
	}

	@Override
	public AddScoreAuditResVO getAddScoreAuditById(Map<String, Object> param) {
		return addScoreAuditMapper.getAddScoreAuditById(param);
	}

	@Override
	public int updateAuditById(AddScoreAuditDO addScoreAuditDO) {
		return addScoreAuditMapper.updateById(addScoreAuditDO);
	}
}
