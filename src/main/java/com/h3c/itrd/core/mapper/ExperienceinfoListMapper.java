package com.h3c.itrd.core.mapper;

import com.h3c.itrd.core.entity.ExperienceinfoListDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jsoup.select.Elements;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 经验案例信息表 Mapper 接口
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface ExperienceinfoListMapper extends BaseMapper<ExperienceinfoListDO> {

	void batchUpdateExperienceInfo(@Param("list")List<ExperienceinfoListDO> experienceInfoList);

	void batchInsertExperienceInfo(@Param("list")List<ExperienceinfoListDO> experienceInfoList);

}
