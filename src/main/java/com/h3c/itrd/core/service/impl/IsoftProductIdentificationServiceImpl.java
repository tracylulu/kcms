package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.IsoftProductIdentificationDO;
import com.h3c.itrd.core.mapper.IsoftProductIdentificationMapper;
import com.h3c.itrd.core.mapper.IsoftProjectListMapper;
import com.h3c.itrd.core.service.IsoftProductIdentificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Comware产品的代码鉴定结果 服务实现类
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
@Service
public class IsoftProductIdentificationServiceImpl extends ServiceImpl<IsoftProductIdentificationMapper, IsoftProductIdentificationDO> implements IsoftProductIdentificationService {
	@Autowired
	IsoftProductIdentificationMapper isoftProductIdentificationMapper;
	@Autowired
	IsoftProjectListMapper isoftProjectListMapper;
	@Override
	@Transactional
	public Boolean batchRenew(List<IsoftProductIdentificationDO> lst) {
		Integer listSize =lst.size();
		Integer num = isoftProductIdentificationMapper.selectCount();
		if(num==0) {
			isoftProductIdentificationMapper.batchInsert(lst);
			isoftProjectListMapper.updateAppraiseEva();
			return true;
		}
		else if (Math.abs(listSize-num)<100) {
		isoftProductIdentificationMapper.deleteAllInfo();
		isoftProductIdentificationMapper.batchInsert(lst);
		isoftProjectListMapper.updateAppraiseEva();
			return true;}
		
		else
			return false;
}

}
