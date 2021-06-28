package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ProjectSummaryDO;
import com.h3c.itrd.core.vo.ProjectSummaryVO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目得分汇总表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ProjectSummaryService extends IService<ProjectSummaryDO> {
	List<ProjectSummaryDO> getProListByAccountAndQuarter(String account, String quarter);

    List<ProjectSummaryVO> getSoftDevList(Map<String, Object> param);

    int deleteByQuarterAndType(String quarter,String type);
    
    int deleteByQuarterAndTypeForV(String quarter,String type,String projectType);

    List<ProjectSummaryDO> getProjectSummaryList(Map<String, Object> param);
    
    int add(ProjectSummaryDO projectSummaryDO);
}
