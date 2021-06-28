package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.DepartmentListDO;
import com.h3c.itrd.core.mapper.DepartmentListMapper;
import com.h3c.itrd.core.service.DepartmentListService;
import com.h3c.itrd.core.vo.DepartmentListForAdminVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 部门列表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class DepartmentListServiceImpl extends ServiceImpl<DepartmentListMapper, DepartmentListDO> implements DepartmentListService {
	@Autowired
	private DepartmentListMapper departmentListMapper;

	@Override
	public DepartmentListDO getListByDptcode(String dptcode) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("dptcode",dptcode);
        return departmentListMapper.selectOne(queryWrapper);
	}

	@Override
	public List<DepartmentListDO> getListBySubDptcode(String subdptcode) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("subdptcode",subdptcode);
        queryWrapper.eq("remark",1);
        return departmentListMapper.selectList(queryWrapper);
	}

	@Override
	public List<DepartmentListForAdminVO> getListForAdmin() {
		return departmentListMapper.getListForAdmin();
	}
	
	@Override
	@Transactional
	public void batchSyncDeptInfo(List<Map<String, Object>> deptInfoList) {
		List<DepartmentListDO> dptInfoList = new ArrayList<DepartmentListDO>();
		for (Map<String, Object> deptInfo : deptInfoList) {
			String dptcode= (String)deptInfo.get("dept_code");
			String dptname= (String)deptInfo.get("dept_name");
			String subdptcode= (String)deptInfo.get("sup_dept_code");
			String level= (String)deptInfo.get("dept_level");
			DepartmentListDO dptInfo =new DepartmentListDO();
			dptInfo.setDptcode(dptcode);
			dptInfo.setDptname(dptname);
			dptInfo.setSubdptcode(subdptcode);
			dptInfo.setLevel(Integer.valueOf(level));
			dptInfo.setRemark(1);
			dptInfo.setSyncTime(new Date());
			dptInfoList.add(dptInfo);
		}
		
		batchLogicDelDeptInfo(dptInfoList);
		batchUpdateDeptInfo(dptInfoList);
		batchInsertDeptInfo(dptInfoList);
	}

	@Override
	public void batchInsertDeptInfo(List<DepartmentListDO> deptInfoList) {
		departmentListMapper.batchInsertDeptInfo(deptInfoList);
	}
	
	@Override
	public int deleteAllInfo() {
		return departmentListMapper.deleteAllInfo();
	}
	
	@Override
	public int batchLogicDelDeptInfo(List<DepartmentListDO> dptInfoList) {
		return departmentListMapper.batchLogicDelDeptInfo(dptInfoList);
		
	}
	
	@Override
	public int batchUpdateDeptInfo(List<DepartmentListDO> dptInfoList) {
		return departmentListMapper.batchUpdateDeptInfo(dptInfoList);
		
	}
	
}
