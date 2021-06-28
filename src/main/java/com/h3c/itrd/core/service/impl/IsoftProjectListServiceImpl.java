package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.IsoftProjectListDO;
import com.h3c.itrd.core.mapper.IsoftProjectListMapper;
import com.h3c.itrd.core.service.IsoftProjectListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class IsoftProjectListServiceImpl extends ServiceImpl<IsoftProjectListMapper, IsoftProjectListDO> implements IsoftProjectListService {
	@Autowired
    private IsoftProjectListMapper isoftProjectListMapper;
    @Override
    public List<IsoftProjectListDO> getIsoftListByParam(Map<String, Object> param) {
    	QueryWrapper queryWrapper=new QueryWrapper();
    	if(param.get("id") != null && param.get("id")!="") {
	        queryWrapper.eq("projectId",param.get("id"));
	        queryWrapper.select("projectId","projectName","Url","state","PCBType","PM","PlanScale","ActualScale","ATEvaluation",
	                "ATDI","ScheduleForAll","AppraiseEva","AuditScore","Difficult");
    	}
    	//v模型项目条件，ProType为2
    	if(param.get("ProType") != null && param.get("ProType")!="") {
    		queryWrapper.eq("ProType",param.get("ProType"));
    	}
    	//其他项目条件，ProType不为2
    	if(param.get("ProTypeNot2") != null && param.get("ProTypeNot2")!="") {
    		queryWrapper.ne("ProType",param.get("ProTypeNot2"));
    	}
        return isoftProjectListMapper.selectList(queryWrapper);
    }
	@Override
	@Transactional
	public void batchEdit(List<IsoftProjectListDO> lstEdit) {
		isoftProjectListMapper.batchEdit(lstEdit);
		
	}
}
