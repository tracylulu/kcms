package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.QuarterScoreDO;
import com.h3c.itrd.core.vo.DepartmentListResVO;
import com.h3c.itrd.core.vo.EmployeeListResVO;
import com.h3c.itrd.core.vo.OverviewResVO;
import com.h3c.itrd.core.vo.RankListResVO;
import com.h3c.itrd.core.vo.RankResVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工季度总得分表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface QuarterScoreService extends IService<QuarterScoreDO> {
	List<OverviewResVO> getOverviewList(Map<String, Object> param);

    List<RankResVO> getRankForTotal(Map<String,Object> param);
    List<RankResVO> getRankForDev(Map<String,Object> param);
    List<RankResVO> getRankForMaintain(Map<String,Object> param);
    List<RankResVO> getRankForAudit(Map<String,Object> param);
    List<RankResVO> getRankForAdd(Map<String,Object> param);
    List<RankResVO> getRankForPlat(Map<String,Object> param);

    QuarterScoreDO getByAccountAndQuarter(String account, String quarter);

    List<RankListResVO> getRankList(Map<String,Object> param);

    int deleteQuarterScoreByMap(Map<String,Object> param);
    
    List<DepartmentListResVO> getListForNoSpecial3(Map<String,Object> param);
    List<DepartmentListResVO> getListForSpecial3(Map<String,Object> param);
    List<DepartmentListResVO> getRankListFor3(Map<String,Object> param);
    List<DepartmentListResVO> getListForNoSpecial4(Map<String,Object> param);
    List<DepartmentListResVO> getListForSpecial4(Map<String,Object> param);
    List<DepartmentListResVO> getRankListFor4(Map<String,Object> param);
    
    List<EmployeeListResVO> getListForEmployee(Map<String,Object> param);
    List<RankResVO> getRankForEmployee(Map<String,Object> param);
    List<RankResVO> getRank1ForEmployee(Map<String,Object> param);
    
    
}
