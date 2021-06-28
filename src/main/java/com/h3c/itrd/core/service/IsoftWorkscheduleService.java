package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IsoftWorkscheduleDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工作日历表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IsoftWorkscheduleService extends IService<IsoftWorkscheduleDO> {
	List<IsoftWorkscheduleDO> getWorkscheduleList();
	
	List<IsoftWorkscheduleDO> getListByDate(String start,String end);
}
