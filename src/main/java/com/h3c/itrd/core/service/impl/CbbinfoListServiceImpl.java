package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.CbbinfoListDO;
import com.h3c.itrd.core.mapper.CbbinfoListMapper;
import com.h3c.itrd.core.service.CbbinfoListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * CBB视图信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class CbbinfoListServiceImpl extends ServiceImpl<CbbinfoListMapper, CbbinfoListDO> implements CbbinfoListService {
	@Autowired
    private CbbinfoListMapper cbbinfoListMapper;

    

    @Override
    public List<CbbinfoListDO> getCbbinfoList(String account, String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("RespParty",account);
        queryWrapper.between("ActualRelDate",start,end);
        return cbbinfoListMapper.selectList(queryWrapper);
    }



	@Override
	public List<CbbinfoListDO> getListForPlatform(String start, String end) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("ActualRelDate",start,end);
        return cbbinfoListMapper.selectList(queryWrapper);
	}
}
