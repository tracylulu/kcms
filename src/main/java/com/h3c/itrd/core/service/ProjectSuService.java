package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ProjectSuDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目数据汇总表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ProjectSuService extends IService<ProjectSuDO> {
	int deleteByQuarter(String quarter);
	
	List<ProjectSuDO> getList();
	
	int add(ProjectSuDO projectSuDO);
	
}
