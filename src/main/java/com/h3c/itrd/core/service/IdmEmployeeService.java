package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.IdmEmployeeDO;
import com.h3c.itrd.core.vo.IdmEmployeeVO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 软件开发问题单得分表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface IdmEmployeeService extends IService<IdmEmployeeDO> {
	List<IdmEmployeeDO> getSoftListByAccountAndQuarter(String account, String quarter);

    List<IdmEmployeeVO> getSoftMaintainList(Map<String, Object> param);

    int deleteIdmEmployeeByQuarter(String quarter);

    List<IdmEmployeeDO> getIdmEmployeeList(Map<String, Object> param);
}
