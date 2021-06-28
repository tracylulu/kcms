package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.IsoftProductFeatureDO;
import com.h3c.itrd.core.mapper.IsoftProductFeatureMapper;
import com.h3c.itrd.core.mapper.IsoftProjectListMapper;
import com.h3c.itrd.core.service.IsoftProductFeatureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Comware产品的特性审计结果 服务实现类
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
@Service
public class IsoftProductFeatureServiceImpl extends ServiceImpl<IsoftProductFeatureMapper, IsoftProductFeatureDO> implements IsoftProductFeatureService {
	@Autowired
	private IsoftProductFeatureMapper isoftProductFeatureMapper;
	@Autowired
	private IsoftProjectListMapper isoftProjectListMapper;
	
	@Override
	@Transactional
	public	Boolean batchRenew(List<IsoftProductFeatureDO> lstEdit) {
		Integer listSize =lstEdit.size();
		Integer num = isoftProductFeatureMapper.selectCount();
		if (num==0) {
			isoftProductFeatureMapper.batchInsert(lstEdit);
			isoftProjectListMapper.updateAuditScore();
			return true;
		}
		else if (Math.abs(listSize-num)<100) {
			isoftProductFeatureMapper.deleteAllInfo();
			isoftProductFeatureMapper.batchInsert(lstEdit);
			isoftProjectListMapper.updateAuditScore();
			return true;
		}
		else
			return false;
		
	}

}
