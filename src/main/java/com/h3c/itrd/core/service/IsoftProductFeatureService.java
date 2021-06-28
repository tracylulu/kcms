package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IsoftProductFeatureDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * Comware产品的特性审计结果 服务类
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
public interface IsoftProductFeatureService extends IService<IsoftProductFeatureDO> {

	Boolean batchRenew(List<IsoftProductFeatureDO> lstEdit);

}
