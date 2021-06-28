package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ProjectSuDO;
import com.h3c.itrd.core.entity.ReviewSummaryDO;
import com.h3c.itrd.core.mapper.ProjectSuMapper;
import com.h3c.itrd.core.service.ProjectSuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目数据汇总表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class ProjectSuServiceImpl extends ServiceImpl<ProjectSuMapper, ProjectSuDO> implements ProjectSuService {

	@Autowired
	private ProjectSuMapper projectSuMapper;
	
	@Override
    public boolean saveBatch(Collection<ProjectSuDO> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }
	@Override
	public int deleteByQuarter(String quarter) {
		Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("quarter", quarter);
        return  projectSuMapper.deleteByMap(columnMap);
	}
	@Override
	public List<ProjectSuDO> getList() {
		QueryWrapper queryWrapper=new QueryWrapper();
        return projectSuMapper.selectList(queryWrapper);
	}
	@Override
	public int add(ProjectSuDO projectSuDO) {
		return projectSuMapper.insert(projectSuDO);
	}

	
}
