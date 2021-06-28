package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IsoftProjectCloudListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IsoftProjectCloudListService extends IService<IsoftProjectCloudListDO> {
	List<IsoftProjectCloudListDO> getIsoftCloudList(String start, String end);
}
