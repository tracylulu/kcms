package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.AddScoreSelectDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 关键事件选项表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface AddScoreSelectService extends IService<AddScoreSelectDO> {
	List<AddScoreSelectDO> addScoreSelect(Map<String, Object> param);
	
	
	
}
