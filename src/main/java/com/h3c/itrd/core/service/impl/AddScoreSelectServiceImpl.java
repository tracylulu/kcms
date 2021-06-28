package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.AddScoreSelectDO;
import com.h3c.itrd.core.mapper.AddScoreSelectMapper;
import com.h3c.itrd.core.service.AddScoreSelectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关键事件选项表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class AddScoreSelectServiceImpl extends ServiceImpl<AddScoreSelectMapper, AddScoreSelectDO> implements AddScoreSelectService {
	@Autowired
	private AddScoreSelectMapper addScoreSelectMapper;

	@Override
	public List<AddScoreSelectDO> addScoreSelect(Map<String, Object> param) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("seconddptcode",param.get("seconddptcode"));
        queryWrapper.eq("status",1);
        queryWrapper.in("thirddptcode",Arrays.asList("所有",param.get("thirddptcode")));
        queryWrapper.select("id","name","score");
        return addScoreSelectMapper.selectList(queryWrapper);
	}
}
