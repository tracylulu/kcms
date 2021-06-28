package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.PatentinfoListDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 专利信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface PatentinfoListMapper extends BaseMapper<PatentinfoListDO> {

	void batchInsertInfo(@Param("list")List<PatentinfoListDO> patentinfoList);
	void deleteAllInfo();
	int selectCount();
	int selectPatentNoCount();

}
