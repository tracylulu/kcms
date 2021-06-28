package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.SpecinfoListDO;
import com.h3c.itrd.core.mapper.SpecinfoListMapper;
import com.h3c.itrd.core.service.SpecinfoListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 规范表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class SpecinfoListServiceImpl extends ServiceImpl<SpecinfoListMapper, SpecinfoListDO> implements SpecinfoListService {
	@Autowired
    private SpecinfoListMapper specinfoListMapper;
    @Override
    public List<SpecinfoListDO> getSpecinfoList(String account, String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("WF_ADDNAME",account);
        queryWrapper.between("SSTIME",start,end);
        queryWrapper.select("WF_ADDNAME","GFJB","ZLB1","ZLB2","GDWJBH","FBTIME","GJC","GDXZ");
        return specinfoListMapper.selectList(queryWrapper);
    }
	@Override
	public List<SpecinfoListDO> getListForPlatform(String start, String end) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("SSTIME",start,end);
        return specinfoListMapper.selectList(queryWrapper);
	}
}
