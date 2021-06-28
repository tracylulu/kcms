package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.QuarterScoreDO;
import com.h3c.itrd.core.mapper.QuarterScoreMapper;
import com.h3c.itrd.core.service.QuarterScoreService;
import com.h3c.itrd.core.vo.DepartmentListResVO;
import com.h3c.itrd.core.vo.EmployeeListResVO;
import com.h3c.itrd.core.vo.OverviewResVO;
import com.h3c.itrd.core.vo.RankListResVO;
import com.h3c.itrd.core.vo.RankResVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工季度总得分表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class QuarterScoreServiceImpl extends ServiceImpl<QuarterScoreMapper, QuarterScoreDO> implements QuarterScoreService {
	@Autowired
    private QuarterScoreMapper quarterScoreMapper;

    @Override
    public boolean saveBatch(Collection<QuarterScoreDO> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public List<OverviewResVO> getOverviewList(Map<String, Object> param) {
        return quarterScoreMapper.getOverviewList(param);
    }

    @Override
    public List<RankResVO> getRankForTotal(Map<String, Object> param) {
        return quarterScoreMapper.getRankForTotal(param);
    }

    @Override
    public List<RankResVO> getRankForDev(Map<String, Object> param) {
        return quarterScoreMapper.getRankForDev(param);
    }

    @Override
    public List<RankResVO> getRankForMaintain(Map<String, Object> param) {
        return quarterScoreMapper.getRankForMaintain(param);
    }

    @Override
    public List<RankResVO> getRankForAudit(Map<String, Object> param) {
        return quarterScoreMapper.getRankForAudit(param);
    }

    @Override
    public List<RankResVO> getRankForAdd(Map<String, Object> param) {
        return quarterScoreMapper.getRankForAdd(param);
    }

    @Override
    public List<RankResVO> getRankForPlat(Map<String, Object> param) {
        return quarterScoreMapper.getRankForPlat(param);
    }

    @Override
    public QuarterScoreDO getByAccountAndQuarter(String account, String quarter) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("quarter",quarter);
        return quarterScoreMapper.selectOne(queryWrapper);
    }
    @Override
    public List<RankListResVO> getRankList(Map<String, Object> param) {
        return quarterScoreMapper.getRankList(param);
    }

    @Override
    public int deleteQuarterScoreByMap(Map<String, Object> param) {
        return quarterScoreMapper.deleteByMap(param);
    }

	@Override
	public List<DepartmentListResVO> getListForNoSpecial3(Map<String, Object> param) {
		return quarterScoreMapper.getListForNoSpecial3(param);
	}

	@Override
	public List<DepartmentListResVO> getListForSpecial3(Map<String, Object> param) {
		return quarterScoreMapper.getListForSpecial3(param);
	}

	@Override
	public List<DepartmentListResVO> getRankListFor3(Map<String, Object> param) {
		return quarterScoreMapper.getRankListFor3(param);
	}

	@Override
	public List<DepartmentListResVO> getListForNoSpecial4(Map<String, Object> param) {
		return quarterScoreMapper.getListForNoSpecial4(param);
	}

	@Override
	public List<DepartmentListResVO> getListForSpecial4(Map<String, Object> param) {
		return quarterScoreMapper.getListForSpecial4(param);
	}

	@Override
	public List<DepartmentListResVO> getRankListFor4(Map<String, Object> param) {
		return quarterScoreMapper.getRankListFor4(param);
	}

	@Override
	public List<EmployeeListResVO> getListForEmployee(Map<String, Object> param) {
		return quarterScoreMapper.getListForEmployee(param);
	}

	@Override
	public List<RankResVO> getRankForEmployee(Map<String, Object> param) {
		return quarterScoreMapper.getRankForEmployee(param);
	}

	@Override
	public List<RankResVO> getRank1ForEmployee(Map<String, Object> param) {
		return quarterScoreMapper.getRank1ForEmployee(param);
	}
	
	
	
	
	
	
}
