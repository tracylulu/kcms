package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.ReviewinfoListDO;
import com.h3c.itrd.core.vo.ReviewinfoListResVO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评审信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ReviewinfoListService extends IService<ReviewinfoListDO> {
	List<ReviewinfoListResVO> getReviewinfoListByParam(Map<String, Object> param);
	
    List<ReviewinfoListDO> getReviewinfoList(String start,String end);
}
