package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.OperationLogDO;
import com.h3c.itrd.core.mapper.OperationLogMapper;
import com.h3c.itrd.core.service.OperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLogDO> implements OperationLogService {
	@Autowired
	OperationLogMapper operationLogMapper;
	public void insertLog(OperationLogDO log) {
		operationLogMapper.insertLog(log);
	}

}
