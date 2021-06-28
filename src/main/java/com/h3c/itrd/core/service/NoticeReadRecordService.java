package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.NoticeReadRecordDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * notice_read_record 各个员工最新公告阅读记录表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-28
 */
public interface NoticeReadRecordService extends IService<NoticeReadRecordDO> {
	NoticeReadRecordDO getRecordByParam(String id,Integer noticeId);
	
	int add(NoticeReadRecordDO noticeReadRecordDO);
	
	int deleteByEmployeeId(String id);
}
