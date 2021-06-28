package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IsoftProductIdentificationDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * Comware产品的代码鉴定结果 服务类
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
public interface IsoftProductIdentificationService extends IService<IsoftProductIdentificationDO> {

	Boolean batchRenew(List<IsoftProductIdentificationDO> lstEdit);

}
