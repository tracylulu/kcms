package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.NoticeListDO;
import com.h3c.itrd.core.mapper.NoticeListMapper;
import com.h3c.itrd.core.service.NoticeListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统通知表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class NoticeListServiceImpl extends ServiceImpl<NoticeListMapper, NoticeListDO> implements NoticeListService {
	@Autowired
    private NoticeListMapper noticeListMapper;
    /*@Override
    public int addNotice(NoticeListDO noticeListDO) {
        int row = noticeListMapper.insert(noticeListDO);
        return row;
    }*/

    @Override
    public boolean saveBatch(Collection<NoticeListDO> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public List<NoticeListDO> getNoticeList() {
        return noticeListMapper.selectList(new QueryWrapper<>());
    }
}
