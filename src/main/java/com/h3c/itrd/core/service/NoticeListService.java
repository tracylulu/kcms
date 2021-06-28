package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.NoticeListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统通知表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface NoticeListService extends IService<NoticeListDO> {
	List<NoticeListDO> getNoticeList();
}
