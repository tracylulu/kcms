package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IsoftProjectListDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IsoftProjectListService extends IService<IsoftProjectListDO> {
	List<IsoftProjectListDO> getIsoftListByParam(Map<String, Object> param);

	void batchEdit(List<IsoftProjectListDO> lstEdit);
}
