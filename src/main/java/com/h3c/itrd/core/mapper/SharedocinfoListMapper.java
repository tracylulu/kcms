package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.SharedocinfoListDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jsoup.select.Elements;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 共享技术信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface SharedocinfoListMapper extends BaseMapper<SharedocinfoListDO> {

	void batchUpdateShareDocInfo(@Param("list")List<SharedocinfoListDO> experienceInfoList);

	void batchInsertShareDocInfo(@Param("list")List<SharedocinfoListDO> experienceInfoList);

}
