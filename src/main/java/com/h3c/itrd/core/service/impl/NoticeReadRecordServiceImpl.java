package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.NoticeReadRecordDO;
import com.h3c.itrd.core.mapper.NoticeReadRecordMapper;
import com.h3c.itrd.core.service.NoticeReadRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * notice_read_record 各个员工最新公告阅读记录表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-28
 */
@Service
public class NoticeReadRecordServiceImpl extends ServiceImpl<NoticeReadRecordMapper, NoticeReadRecordDO> implements NoticeReadRecordService {

	@Autowired
	private NoticeReadRecordMapper noticeReadRecordMapper;
	
	@Override
	public NoticeReadRecordDO getRecordByParam(String id, Integer noticeId) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("employee_id",id);
        queryWrapper.eq("notice_id",noticeId);
        return noticeReadRecordMapper.selectOne(queryWrapper);
	}

	@Override
	public int add(NoticeReadRecordDO noticeReadRecordDO) {
		return noticeReadRecordMapper.insert(noticeReadRecordDO);
	}

	@Override
	public int deleteByEmployeeId(String id) {
		QueryWrapper queryWrapper=new QueryWrapper();
		Map<String, Object> columnMap=new HashMap<>();
		columnMap.put("employee_id", id);
		return noticeReadRecordMapper.deleteByMap(columnMap);
	}

}
