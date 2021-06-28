package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ReviewinfoCloudListDO;
import com.h3c.itrd.core.mapper.ReviewinfoCloudListMapper;
import com.h3c.itrd.core.service.ReviewinfoCloudListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 云产品评审信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class ReviewinfoCloudListServiceImpl extends ServiceImpl<ReviewinfoCloudListMapper, ReviewinfoCloudListDO> implements ReviewinfoCloudListService {
	@Autowired
    private ReviewinfoCloudListMapper reviewinfoCloudListMapper;

    @Override
    public int getReviewCountByParam(Map<String, Object> param) {
        return reviewinfoCloudListMapper.getReviewCountByParam(param);
    }


    @Override
    public List<ReviewinfoCloudListDO> getReviewinfoCloudList(String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("review_date",start,end);
        return reviewinfoCloudListMapper.selectList(queryWrapper);
    }
}
