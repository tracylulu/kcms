package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ReviewSummaryDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评审得分统计表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ReviewSummaryService extends IService<ReviewSummaryDO> {
	List<ReviewSummaryDO> getReviewListByAccountAndQuarter(String account, String quarter);

    int deleteByQuarterAndType(Map<String, Object> param);

    List<ReviewSummaryDO> getReviewSummaryList(Map<String, Object> param);
}
