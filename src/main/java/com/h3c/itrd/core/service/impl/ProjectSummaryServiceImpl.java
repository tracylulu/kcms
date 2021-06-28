package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ProjectSummaryDO;
import com.h3c.itrd.core.mapper.ProjectSummaryMapper;
import com.h3c.itrd.core.service.ProjectSummaryService;
import com.h3c.itrd.core.vo.ProjectSummaryVO;
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
 * 项目得分汇总表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class ProjectSummaryServiceImpl extends ServiceImpl<ProjectSummaryMapper, ProjectSummaryDO> implements ProjectSummaryService {
	@Autowired
    private ProjectSummaryMapper projectSummaryMapper;

    @Override
    public boolean saveBatch(Collection<ProjectSummaryDO> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public List<ProjectSummaryDO> getProListByAccountAndQuarter(String account, String quarter) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("quarter",quarter);
        queryWrapper.select("Sa","projectId","projectName","Scale","S1X","A_min","B_min","C_min","D_min","Sa_count","contributevalue");
        return projectSummaryMapper.selectList(queryWrapper);
    }

    @Override
    public List<ProjectSummaryVO> getSoftDevList(Map<String, Object> param) {
        return projectSummaryMapper.getSoftDevList(param);
    }

    @Override
    public int deleteByQuarterAndType(String quarter, String type) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("quarter", quarter);
        columnMap.put("type", type);
        return  projectSummaryMapper.deleteByMap(columnMap);
    }

    @Override
    public List<ProjectSummaryDO> getProjectSummaryList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (param.get("quarter") != null) {
            queryWrapper.eq("quarter",param.get("quarter"));
        }
        return projectSummaryMapper.selectList(queryWrapper);
    }

	@Override
	public int add(ProjectSummaryDO projectSummaryDO) {
		return projectSummaryMapper.insert(projectSummaryDO);
	}

	@Override
	public int deleteByQuarterAndTypeForV(String quarter, String type, String projectType) {
		Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("quarter", quarter);
        columnMap.put("type", type);
        columnMap.put("projectType", projectType);
        return  projectSummaryMapper.deleteByMap(columnMap);
	}
}
