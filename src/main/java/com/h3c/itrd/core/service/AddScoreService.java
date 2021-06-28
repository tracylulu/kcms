package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.AddScoreDO;
import com.h3c.itrd.core.vo.AddScoreResVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 关键事件得分表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface AddScoreService extends IService<AddScoreDO> {
	List<AddScoreResVO> getAddScoreListByParam(Map<String, Object> param);

    List<AddScoreDO> getListBy3Param(String id, String quarter);

    List<AddScoreDO> getListBy2Param(String id, String quarter);

    List<AddScoreDO> getListByInsertUserId(int insertUserId);

    int add(AddScoreDO addScoreDO);

    List<AddScoreResVO> getListForTotalScore(Map<String, Object> param);
    
    AddScoreDO getByAuditId(String id);
    
    List<AddScoreResVO> getAddScoreDetailByParam(Map<String, Object> param);
}
