package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.DptmanagerListDO;
import com.h3c.itrd.core.entity.EmployeeResignListDO;
import com.h3c.itrd.core.mapper.EmployeeResignListMapper;
import com.h3c.itrd.core.service.EmployeeResignListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 离职员工信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class EmployeeResignListServiceImpl extends ServiceImpl<EmployeeResignListMapper, EmployeeResignListDO> implements EmployeeResignListService {
	@Autowired
	private EmployeeResignListMapper employeeResignListMapper;
	@Override
	@Transactional
	public void batchSyncDismissionUser(List<Map<String, Object>> dismissionUserList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeResignListDO> employeeResignList = new ArrayList<EmployeeResignListDO>();
		try {
			for (Map<String, Object> dismissionUser : dismissionUserList) {
			String id =(String) dismissionUser.get("emp_code");
			String leavedateString =(String)dismissionUser.get("leave_date");
			Date leavedate = leavedateString==null?null:sdf.parse(leavedateString);
			EmployeeResignListDO employeeResign = new EmployeeResignListDO();
			employeeResign.setId(id);
			employeeResign.setLeavedate(leavedate);
			employeeResign.setSyncTime(new Date());
			employeeResignList.add(employeeResign);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		deleteAllInfo();
		batchInsertDismissionUser(employeeResignList);
	}
	
	@Override
	public void batchInsertDismissionUser(List<EmployeeResignListDO> employeeResignList) {
		employeeResignListMapper.batchInsertDismissionUser(employeeResignList);
	}
	
	@Override
	public int deleteAllInfo() {
		return employeeResignListMapper.deleteAllInfo();
	}

}
