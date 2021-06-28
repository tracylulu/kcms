package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.CbbinfoListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * CBB视图信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface CbbinfoListService extends IService<CbbinfoListDO> {
	List<CbbinfoListDO> getCbbinfoList(String account,String start,String end);
	List<CbbinfoListDO> getListForPlatform(String start,String end);
}
