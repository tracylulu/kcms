package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.PlatformSummaryDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 平台贡献得分表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface PlatformSummaryService extends IService<PlatformSummaryDO> {
	List<PlatformSummaryDO> getPlatformListByAccountAndQuarter(String account, String quarter);

    List<PlatformSummaryDO> getPlatformList(Map<String, Object> param);
    
    int deleteQuarterByMap(Map<String, Object> param);
}
