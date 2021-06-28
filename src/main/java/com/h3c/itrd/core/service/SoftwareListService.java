package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.AddScoreAuditDO;
import com.h3c.itrd.core.vo.AddScoreAuditListResVO;
import com.h3c.itrd.core.vo.AddScoreAuditResVO;
import com.h3c.itrd.core.vo.AddScoreReplyListVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 软件开发定时汇算任务汇总类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-31
 */
public interface SoftwareListService  {
	//软件开发--开发得分
	Boolean devSu(String quarter) throws Exception;
	//软件开发--云数开发得分
	Boolean develop(String quarter) throws Exception;
	//软件开发--维护得分
	Boolean weihu(String quarter) throws Exception;
	//软件开发--评审得分
	Boolean audit(String quarter) throws Exception;
	//软件开发--当季得分
	Boolean totalScore(String quarter) throws Exception;
	//平台贡献得分
	Boolean platform(String quarter) throws Exception;
		
	//软件开发--开发得分(isoftV模型项目)
	Boolean devModelV(String quarter) throws Exception;
}
