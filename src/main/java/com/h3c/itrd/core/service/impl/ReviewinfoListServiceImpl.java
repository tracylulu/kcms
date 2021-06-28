package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ReviewinfoListDO;
import com.h3c.itrd.core.mapper.ReviewinfoListMapper;
import com.h3c.itrd.core.service.ReviewinfoListService;
import com.h3c.itrd.core.vo.ReviewinfoListResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评审信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class ReviewinfoListServiceImpl extends ServiceImpl<ReviewinfoListMapper, ReviewinfoListDO> implements ReviewinfoListService {
	@Autowired
    private ReviewinfoListMapper reviewinfoListMapper;

    @Override
    public List<ReviewinfoListResVO> getReviewinfoListByParam(Map<String, Object> param) {
        return reviewinfoListMapper.getReviewinfoListByParam(param);
    }

    @Override
    public List<ReviewinfoListDO> getReviewinfoList(String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("finishdate",start,end);
        return reviewinfoListMapper.selectList(queryWrapper);
    }
}
