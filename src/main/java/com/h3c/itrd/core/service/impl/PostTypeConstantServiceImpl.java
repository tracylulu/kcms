package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.PostTypeConstantDO;
import com.h3c.itrd.core.mapper.PostTypeConstantMapper;
import com.h3c.itrd.core.service.PostTypeConstantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工职类常量表 服务实现类
 * </p>
 *
 * @author c17740
 * @since 2021-06-21
 */
@Service
public class PostTypeConstantServiceImpl extends ServiceImpl<PostTypeConstantMapper, PostTypeConstantDO> implements PostTypeConstantService {
	@Autowired
	PostTypeConstantMapper postTypeConstantMapper;
	@Override
	public List<PostTypeConstantDO> selectPostType(Integer deleteFlag) {
		Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("delete_flag", deleteFlag);
        return  postTypeConstantMapper.selectByMap(columnMap);
	}

}
