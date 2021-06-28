package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.IdmEmployeeDO;
import com.h3c.itrd.core.mapper.IdmEmployeeMapper;
import com.h3c.itrd.core.service.IdmEmployeeService;
import com.h3c.itrd.core.vo.IdmEmployeeVO;
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
 * 软件开发问题单得分表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class IdmEmployeeServiceImpl extends ServiceImpl<IdmEmployeeMapper, IdmEmployeeDO> implements IdmEmployeeService {
	@Autowired
    private IdmEmployeeMapper idmEmployeeMapper;

    @Override
    public boolean saveBatch(Collection<IdmEmployeeDO> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public List<IdmEmployeeDO> getSoftListByAccountAndQuarter(String account, String quarter) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("quarter",quarter);
        return idmEmployeeMapper.selectList(queryWrapper);
    }

    @Override
    public List<IdmEmployeeVO> getSoftMaintainList(Map<String, Object> param) {
        return idmEmployeeMapper.getSoftMaintainList(param);
    }

    @Override
    public int deleteIdmEmployeeByQuarter(String quarter) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("quarter", quarter);
        return  idmEmployeeMapper.deleteByMap(columnMap);
    }

    @Override
    public List<IdmEmployeeDO> getIdmEmployeeList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (param.get("quarter") != null) {
            queryWrapper.eq("quarter",param.get("quarter"));
        }
        return idmEmployeeMapper.selectList(queryWrapper);
    }
}
