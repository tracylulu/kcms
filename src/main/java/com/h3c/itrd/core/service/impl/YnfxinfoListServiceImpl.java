package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.YnfxinfoListDO;
import com.h3c.itrd.core.mapper.YnfxinfoListMapper;
import com.h3c.itrd.core.service.YnfxinfoListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 疑难复现电子流信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class YnfxinfoListServiceImpl extends ServiceImpl<YnfxinfoListMapper, YnfxinfoListDO> implements YnfxinfoListService {
	@Autowired
    private YnfxinfoListMapper ynfxinfoListMapper;
    @Override
    public List<YnfxinfoListDO> getYnfxinfoList(String account, String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("USERID_06B",account);
        queryWrapper.between("ENDTIME_06B",start,end);
        return ynfxinfoListMapper.selectList(queryWrapper);
    }
	@Override
	public List<YnfxinfoListDO> getListForPlatform(String start, String end) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("ENDTIME_06B",start,end);
        return ynfxinfoListMapper.selectList(queryWrapper);
	}
}
