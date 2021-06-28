package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ReviewinfoCloudListDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 云产品评审信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ReviewinfoCloudListService extends IService<ReviewinfoCloudListDO> {
	int getReviewCountByParam(Map<String, Object> param);

    List<ReviewinfoCloudListDO> getReviewinfoCloudList(String start, String end);
}
