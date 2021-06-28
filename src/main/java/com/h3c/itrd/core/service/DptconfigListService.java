package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.DptconfigListDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 软件开发算法参数配置表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface DptconfigListService extends IService<DptconfigListDO> {
	DptconfigListDO getDptconfigListByDptcode(String dptcode);
	
	DptconfigListDO getByDptcodeAndconfigtype(String dptcode,String configtype);
	
	int updateDptconfigListById(DptconfigListDO dptconfigListDO);
	
	List<DptconfigListDO> getList(Map<String, Object> param);
}
