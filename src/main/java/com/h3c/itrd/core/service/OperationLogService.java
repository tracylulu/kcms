package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.OperationLogDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface OperationLogService extends IService<OperationLogDO> {
	void insertLog(OperationLogDO log);

}
