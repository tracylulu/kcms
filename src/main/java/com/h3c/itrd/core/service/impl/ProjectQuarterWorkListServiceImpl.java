package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ProjectQuarterWorkListDO;
import com.h3c.itrd.core.mapper.ProjectQuarterWorkListMapper;
import com.h3c.itrd.core.service.ProjectQuarterWorkListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * project_quarter_work_list当季当量数据表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-20
 */
@Service
public class ProjectQuarterWorkListServiceImpl extends ServiceImpl<ProjectQuarterWorkListMapper, ProjectQuarterWorkListDO> implements ProjectQuarterWorkListService {

	@Autowired
	private ProjectQuarterWorkListMapper projectQuarterWorkListMapper;
	@Override
	public List<ProjectQuarterWorkListDO> getList() {
		QueryWrapper queryWrapper=new QueryWrapper();
        return projectQuarterWorkListMapper.selectList(queryWrapper);
	}

}
