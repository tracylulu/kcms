package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ProjectSuVDO;
import com.h3c.itrd.core.entity.ProjectSummaryVDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * project_su_v  v模型的汇算中间表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-29
 */
public interface ProjectSuVService extends IService<ProjectSuVDO> {
	List<ProjectSuVDO> getList();
	
	int deleteByQuarter(String quarter);
}
