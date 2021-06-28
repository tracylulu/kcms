package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.PostTypeConstantDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工职类常量表 服务类
 * </p>
 *
 * @author c17740
 * @since 2021-06-21
 */
public interface PostTypeConstantService extends IService<PostTypeConstantDO> {

	List<PostTypeConstantDO> selectPostType(Integer deleteFlag);

}
