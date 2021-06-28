package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.ReviewSummaryDO;
import com.h3c.itrd.core.mapper.ReviewSummaryMapper;
import com.h3c.itrd.core.service.ReviewSummaryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评审得分统计表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class ReviewSummaryServiceImpl extends ServiceImpl<ReviewSummaryMapper, ReviewSummaryDO> implements ReviewSummaryService {
	@Autowired
    private ReviewSummaryMapper reviewSummaryMapper;

    @Override
    public boolean saveBatch(Collection<ReviewSummaryDO> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public List<ReviewSummaryDO> getReviewListByAccountAndQuarter(String account, String quarter) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("quarter",quarter);
        queryWrapper.select("totalscore","criticalnum","ordinarynum","totalnum","totaldi","tipnum","seconddptcode");
        return reviewSummaryMapper.selectList(queryWrapper);

    }

    @Override
    public int deleteByQuarterAndType(Map<String, Object> param) {
        return reviewSummaryMapper.deleteByQuarterAndType(param);
    }

    @Override
    public List<ReviewSummaryDO> getReviewSummaryList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (param.get("quarter") != null) {
            queryWrapper.eq("quarter",param.get("quarter"));
        }
        return reviewSummaryMapper.selectList(queryWrapper);
    }
}
