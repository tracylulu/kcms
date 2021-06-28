package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ProjectQuarterWorkListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * project_quarter_work_list当季当量数据表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-20
 */
public interface ProjectQuarterWorkListService extends IService<ProjectQuarterWorkListDO> {
	List<ProjectQuarterWorkListDO> getList();
}
