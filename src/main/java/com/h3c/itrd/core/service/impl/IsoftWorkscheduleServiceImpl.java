package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.IsoftWorkscheduleDO;
import com.h3c.itrd.core.mapper.IsoftWorkscheduleMapper;
import com.h3c.itrd.core.service.IsoftWorkscheduleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工作日历表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class IsoftWorkscheduleServiceImpl extends ServiceImpl<IsoftWorkscheduleMapper, IsoftWorkscheduleDO> implements IsoftWorkscheduleService {

	@Autowired
	private IsoftWorkscheduleMapper isoftWorkscheduleMapper;
	@Override
	public List<IsoftWorkscheduleDO> getWorkscheduleList() {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("DayType","1.0");
        return isoftWorkscheduleMapper.selectList(queryWrapper);
	}
	@Override
	public List<IsoftWorkscheduleDO> getListByDate(String start, String end) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("DayType","1.0");
        queryWrapper.between("WorkDay", start, end);
        return isoftWorkscheduleMapper.selectList(queryWrapper);
	}

}
