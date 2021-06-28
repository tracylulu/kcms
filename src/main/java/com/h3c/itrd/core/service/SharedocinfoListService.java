package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.SharedocinfoListDO;

import java.util.List;

import org.jsoup.select.Elements;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 共享技术信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface SharedocinfoListService extends IService<SharedocinfoListDO> {
	List<SharedocinfoListDO> getSharedocinfoList(String account, String start, String end);
	List<SharedocinfoListDO> getListForPlatform(String start, String end);
	void syncShareDocInfo(Elements elements);
}
