package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.IsoftProjectCloudListDO;
import com.h3c.itrd.core.mapper.IsoftProjectCloudListMapper;
import com.h3c.itrd.core.service.IsoftProjectCloudListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class IsoftProjectCloudListServiceImpl extends ServiceImpl<IsoftProjectCloudListMapper, IsoftProjectCloudListDO> implements IsoftProjectCloudListService {
	@Autowired
    IsoftProjectCloudListMapper isoftProjectCloudListMapper;

    @Override
    public List<IsoftProjectCloudListDO> getIsoftCloudList(String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("month",start,end);
        return isoftProjectCloudListMapper.selectList(queryWrapper);
    }
}
