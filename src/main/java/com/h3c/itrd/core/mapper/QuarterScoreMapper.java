package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.QuarterScoreDO;
import com.h3c.itrd.core.vo.DepartmentListResVO;
import com.h3c.itrd.core.vo.EmployeeListResVO;
import com.h3c.itrd.core.vo.OverviewResVO;
import com.h3c.itrd.core.vo.RankListResVO;
import com.h3c.itrd.core.vo.RankResVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 员工季度总得分表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface QuarterScoreMapper extends BaseMapper<QuarterScoreDO> {
	List<OverviewResVO> getOverviewList(@Param("param") Map<String,Object> param);

    List<RankResVO> getRankForTotal(@Param("param") Map<String,Object> param);
    List<RankResVO> getRankForDev(@Param("param") Map<String,Object> param);
    List<RankResVO> getRankForMaintain(@Param("param") Map<String,Object> param);
    List<RankResVO> getRankForAudit(@Param("param") Map<String,Object> param);
    List<RankResVO> getRankForAdd(@Param("param") Map<String,Object> param);
    List<RankResVO> getRankForPlat(@Param("param") Map<String,Object> param);

    List<RankListResVO> getRankList(@Param("param") Map<String,Object> param);
    
    
    //团队得分模块中使用过的方法
    //不含特殊岗位，统计单位为3级部门
    List<DepartmentListResVO> getListForNoSpecial3(@Param("param") Map<String,Object> param);
    //含特殊岗位，统计单位为3级部门
    List<DepartmentListResVO> getListForSpecial3(@Param("param") Map<String,Object> param);
    //获取排名
    List<DepartmentListResVO> getRankListFor3(@Param("param") Map<String,Object> param);
    
    //不含特殊岗位，统计单位为4级部门
    List<DepartmentListResVO> getListForNoSpecial4(@Param("param") Map<String,Object> param);
    //含特殊岗位，统计单位为4级部门
    List<DepartmentListResVO> getListForSpecial4(@Param("param") Map<String,Object> param);
    //获取排名
    List<DepartmentListResVO> getRankListFor4(@Param("param") Map<String,Object> param);
    
    //员工得分模块中使用过的方法
    List<EmployeeListResVO> getListForEmployee(@Param("param") Map<String,Object> param);
    //查询每个人的二级部门排名
    List<RankResVO> getRankForEmployee(@Param("param") Map<String,Object> param);
    //查询每个人的二级部门排名（包括特殊事件分）
    List<RankResVO> getRank1ForEmployee(@Param("param") Map<String,Object> param);
}
