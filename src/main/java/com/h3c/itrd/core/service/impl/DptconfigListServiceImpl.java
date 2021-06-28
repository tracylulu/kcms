package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.DptconfigListDO;
import com.h3c.itrd.core.mapper.DptconfigListMapper;
import com.h3c.itrd.core.service.DptconfigListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 软件开发算法参数配置表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class DptconfigListServiceImpl extends ServiceImpl<DptconfigListMapper, DptconfigListDO> implements DptconfigListService {
	
	@Autowired
    private DptconfigListMapper dptconfigListMapper;
   
	
	@Override
    public DptconfigListDO getDptconfigListByDptcode(String dptcode) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("dptcode",dptcode);
        return dptconfigListMapper.selectOne(queryWrapper);
    }

    @Override
    public DptconfigListDO getByDptcodeAndconfigtype(String dptcode, String configtype) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("dptcode",dptcode);
        queryWrapper.eq("configtype",configtype);
        return dptconfigListMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateDptconfigListById(DptconfigListDO dptconfigListDO) {
        return dptconfigListMapper.updateById(dptconfigListDO);
    }

    @Override
    public List<DptconfigListDO> getList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (!param.isEmpty()) {

        }


        return dptconfigListMapper.selectList(queryWrapper);
    }
}
