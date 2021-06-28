package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.AddScoreDO;
import com.h3c.itrd.core.mapper.AddScoreMapper;
import com.h3c.itrd.core.service.AddScoreService;
import com.h3c.itrd.core.vo.AddScoreResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 关键事件得分表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class AddScoreServiceImpl extends ServiceImpl<AddScoreMapper, AddScoreDO> implements AddScoreService {
	@Autowired
    private AddScoreMapper addScoreMapper;


    @Override
    public List<AddScoreResVO> getListForTotalScore(Map<String, Object> param) {
        return addScoreMapper.getListForTotalScore(param);
    }

    @Override
    public List<AddScoreResVO> getAddScoreListByParam(Map<String, Object> param) {
        return addScoreMapper.getAddScoreListByParam(param);
    }

    @Override
    public List<AddScoreDO> getListBy3Param(String id, String quarter) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",id);
        queryWrapper.eq("quarter",quarter);
        //queryWrapper.eq("project",project);
        queryWrapper.select("score","project","remark","detail");
        return addScoreMapper.selectList(queryWrapper);
    }

    @Override
    public List<AddScoreDO> getListBy2Param(String id, String quarter) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",id);
        queryWrapper.eq("quarter",quarter);
        queryWrapper.select("score");
        return addScoreMapper.selectList(queryWrapper);
    }

    @Override
    public List<AddScoreDO> getListByInsertUserId(int insertUserId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("insert_user_id",insertUserId);
        return addScoreMapper.selectList(queryWrapper);
    }

    @Override
    public int add(AddScoreDO addScoreDO) {
        return addScoreMapper.insert(addScoreDO);
    }

	@Override
	public AddScoreDO getByAuditId(String id) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("audit_id",id);
        return addScoreMapper.selectOne(queryWrapper);
	}

	@Override
	public List<AddScoreResVO> getAddScoreDetailByParam(Map<String, Object> param) {
		return addScoreMapper.getAddScoreDetailByParam(param);
	}
}
