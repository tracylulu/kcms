package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.PlatformSummaryDO;
import com.h3c.itrd.core.mapper.PlatformSummaryMapper;
import com.h3c.itrd.core.service.PlatformSummaryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台贡献得分表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class PlatformSummaryServiceImpl extends ServiceImpl<PlatformSummaryMapper, PlatformSummaryDO> implements PlatformSummaryService {
	@Autowired
    private PlatformSummaryMapper platformSummaryMapper;
    @Override
    public List<PlatformSummaryDO> getPlatformListByAccountAndQuarter(String account, String quarter) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("quarter",quarter);
        return platformSummaryMapper.selectList(queryWrapper);
    }

    @Override
    public List<PlatformSummaryDO> getPlatformList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (param.get("quarter") != null) {
            queryWrapper.eq("quarter",param.get("quarter"));
        }
        return platformSummaryMapper.selectList(queryWrapper);
    }

	@Override
	public int deleteQuarterByMap(Map<String, Object> param) {
		return platformSummaryMapper.deleteByMap(param);
	}
}
