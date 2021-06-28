package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.YnfxinfoListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 疑难复现电子流信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface YnfxinfoListService extends IService<YnfxinfoListDO> {
	List<YnfxinfoListDO> getYnfxinfoList(String account,String start, String end);
	List<YnfxinfoListDO> getListForPlatform(String start, String end);
}
