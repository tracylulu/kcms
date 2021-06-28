package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ProjectAcceptanceProblemsListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * project_acceptance_problems_list项目验收问题单视图表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-25
 */
public interface ProjectAcceptanceProblemsListService extends IService<ProjectAcceptanceProblemsListDO> {
	List<ProjectAcceptanceProblemsListDO> getList();
}
