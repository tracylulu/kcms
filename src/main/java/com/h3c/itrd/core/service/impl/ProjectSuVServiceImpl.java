package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ProjectSuVDO;
import com.h3c.itrd.core.mapper.ProjectSuVMapper;
import com.h3c.itrd.core.service.ProjectSuVService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * project_su_v  v模型的汇算中间表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-29
 */
@Service
public class ProjectSuVServiceImpl extends ServiceImpl<ProjectSuVMapper, ProjectSuVDO> implements ProjectSuVService {

	@Autowired
	private ProjectSuVMapper projectSuVMapper;
	@Override
	public List<ProjectSuVDO> getList() {
		QueryWrapper queryWrapper=new QueryWrapper();
        return projectSuVMapper.selectList(queryWrapper);
	}
	@Override
	public int deleteByQuarter(String quarter) {
		Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("quarter", quarter);
        return  projectSuVMapper.deleteByMap(columnMap);
	}

}
