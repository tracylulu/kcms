package com.h3c.itrd.core.service;

import com.h3c.itrd.core.entity.DepartmentConfigDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.PostTypeConstantDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
public interface EmployeeListService extends IService<EmployeeListDO> {
	EmployeeListDO getEmployeeById(String id);
	
	EmployeeListDO getEmployeeByDomainid(String domainid);

    List<EmployeeListDO> getSpecialList(Map<String, Object> param);

    EmployeeListDO getEmployeeByAccount(String account);

    List<EmployeeListDO> getList(Map<String, Object> param);

    EmployeeListDO getByAccountAndType(String account,String postType);

    int getCount(String seconddptcode,String postType);
    //团队得分模块相关
    int getCountGroupByDeptCode(String seconddptcode,String postType,String group );
    
    List<EmployeeListDO> getListByQuery(String thirddptcode,String query);

    List<EmployeeListDO> getListByQueryParam(Map<String, Object> param);

	void batchSyncEmployeeInfo(List<Map<String, Object>> employeeInfoList,List<DepartmentConfigDO> departmentConfigList);

	int batchUpdateEmployeeInfo(List<EmployeeListDO> employeeInfoList);

	int batchInsertEmployeeInfo(List<EmployeeListDO> employeeInfoList);

	int batchUpdateEmployeeInfoForSys(List<EmployeeListDO> employeeInfoList);

	int deleteAllSyncEmployeeInfo();

	String getPostType(List<PostTypeConstantDO> postTypeConstantList, EmployeeListDO employeeInfo);
    
}
