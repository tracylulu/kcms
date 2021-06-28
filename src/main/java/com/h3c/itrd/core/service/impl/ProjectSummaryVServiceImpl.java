package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ProjectSummaryVDO;
import com.h3c.itrd.core.mapper.ProjectSummaryVMapper;
import com.h3c.itrd.core.service.ProjectSummaryVService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * project_summary_V  v模型的汇算中间表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-25
 */
@Service
public class ProjectSummaryVServiceImpl extends ServiceImpl<ProjectSummaryVMapper, ProjectSummaryVDO> implements ProjectSummaryVService {

	@Autowired
	private ProjectSummaryVMapper projectSummaryVMapper;
	
	@Override
	public List<ProjectSummaryVDO> getList() {
		QueryWrapper queryWrapper=new QueryWrapper();
        return projectSummaryVMapper.selectList(queryWrapper);
	}

}
