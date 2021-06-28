package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.PatentinfoListDO;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 专利信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface PatentinfoListService extends IService<PatentinfoListDO> {
	List<PatentinfoListDO> getPatentinfoList(String account, String start, String end);

	List<PatentinfoListDO> getListForPlatform(String start, String end);
	
	void batchInsertInfo(List<PatentinfoListDO> patentinfoList);

	void deleteAllInfo();

	int selectCount();

	int selectPatentNoCount();

	void batchRenewInfo(List<PatentinfoListDO> patentinfoList);
}
