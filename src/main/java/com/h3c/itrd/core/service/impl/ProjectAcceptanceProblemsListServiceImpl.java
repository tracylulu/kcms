package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ProjectAcceptanceProblemsListDO;
import com.h3c.itrd.core.mapper.ProjectAcceptanceProblemsListMapper;
import com.h3c.itrd.core.service.ProjectAcceptanceProblemsListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * project_acceptance_problems_list项目验收问题单视图表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-25
 */
@Service
public class ProjectAcceptanceProblemsListServiceImpl extends ServiceImpl<ProjectAcceptanceProblemsListMapper, ProjectAcceptanceProblemsListDO> implements ProjectAcceptanceProblemsListService {

	@Autowired
	private ProjectAcceptanceProblemsListMapper projectAcceptanceProblemsListMapper;
	@Override
	public List<ProjectAcceptanceProblemsListDO> getList() {
		QueryWrapper queryWrapper=new QueryWrapper();
        return projectAcceptanceProblemsListMapper.selectList(queryWrapper);
	}

}
