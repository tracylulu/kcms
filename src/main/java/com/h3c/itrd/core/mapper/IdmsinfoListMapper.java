package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.IdmsinfoListDO;
import com.h3c.itrd.core.vo.IdmsResVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 问题单备份表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IdmsinfoListMapper extends BaseMapper<IdmsinfoListDO> {
	void batchInsertDefectInfo(@Param("list")List<IdmsinfoListDO> idmsinfoList);
	void batchUpdateDefectInfo(@Param("list")List<IdmsinfoListDO> idmsinfoList);
	

	//普通提单
	List<IdmsResVO> getIdmsPT1(@Param("param") Map<String,Object> param);
	//普通修改
	List<IdmsResVO> getIdmsPT2(@Param("param") Map<String,Object> param);
	//普通定位
	List<IdmsResVO> getIdmsPT3(@Param("param") Map<String,Object> param);
	//普通审核
	List<IdmsResVO> getIdmsPT4(@Param("param") Map<String,Object> param);
	//同步修改
    List<IdmsResVO> getIdmsTB1(@Param("param") Map<String,Object> param);
    //同步审核
    List<IdmsResVO> getIdmsTB2(@Param("param") Map<String,Object> param);
}
