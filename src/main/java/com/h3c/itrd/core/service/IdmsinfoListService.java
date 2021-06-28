package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IdmsinfoListDO;
import com.h3c.itrd.core.vo.IdmsResVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 问题单备份表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IdmsinfoListService extends IService<IdmsinfoListDO> {
    List<IdmsinfoListDO> getList(Map<String,Object> param);
    List<IdmsinfoListDO> getListForModelV();
	void batchInsertDefectInfo(List<IdmsinfoListDO> idmsinfoList);
	void batchUpdateDefectInfo(List<IdmsinfoListDO> idmsinfoList);
	void syncDefectInfo(List<Map<String, Object>> defectDetailList);
	
	//普通提单
	List<IdmsResVO> getIdmsPT1(Map<String,Object> param);
	//普通修改
	List<IdmsResVO> getIdmsPT2(Map<String,Object> param);
	//普通定位
	List<IdmsResVO> getIdmsPT3(Map<String,Object> param);
	//普通审核
	List<IdmsResVO> getIdmsPT4(Map<String,Object> param);
	//同步修改
    List<IdmsResVO> getIdmsTB1(Map<String,Object> param);
    //同步审核
    List<IdmsResVO> getIdmsTB2(Map<String,Object> param);
}
