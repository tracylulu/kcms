package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.SpecinfoListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 规范表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface SpecinfoListService extends IService<SpecinfoListDO> {
	List<SpecinfoListDO> getSpecinfoList(String account, String start, String end);
	List<SpecinfoListDO> getListForPlatform(String start, String end);
}
