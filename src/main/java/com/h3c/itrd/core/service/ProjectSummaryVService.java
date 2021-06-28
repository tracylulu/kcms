package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ProjectSummaryVDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * project_summary_V  v模型的汇算中间表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-25
 */
public interface ProjectSummaryVService extends IService<ProjectSummaryVDO> {
	List<ProjectSummaryVDO> getList();
}
