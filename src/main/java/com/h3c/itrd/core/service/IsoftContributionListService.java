package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IsoftContributionListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目人员贡献度 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IsoftContributionListService extends IService<IsoftContributionListDO> {
	List<IsoftContributionListDO> getContributionListByQuarter(String quarter);
}
