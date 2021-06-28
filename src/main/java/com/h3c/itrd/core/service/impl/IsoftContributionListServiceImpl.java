package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.IsoftContributionListDO;
import com.h3c.itrd.core.mapper.IsoftContributionListMapper;
import com.h3c.itrd.core.service.IsoftContributionListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目人员贡献度 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class IsoftContributionListServiceImpl extends ServiceImpl<IsoftContributionListMapper, IsoftContributionListDO> implements IsoftContributionListService {

	@Autowired
	private IsoftContributionListMapper isoftContributionListMapper;
	@Override
	public List<IsoftContributionListDO> getContributionListByQuarter(String quarter) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("quarter",quarter);
        queryWrapper.orderByDesc("projectId");
        return isoftContributionListMapper.selectList(queryWrapper);
	}

}
