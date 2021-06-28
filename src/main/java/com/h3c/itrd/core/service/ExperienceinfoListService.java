package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ExperienceinfoListDO;

import java.util.List;

import org.jsoup.select.Elements;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 经验案例信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ExperienceinfoListService extends IService<ExperienceinfoListDO> {
	List<ExperienceinfoListDO> getExperienceinfoList(String account, String start, String end);

	void syncExperienceinfo(Elements elements);
	
	List<ExperienceinfoListDO> getListForPlatform(String start, String end);
}
