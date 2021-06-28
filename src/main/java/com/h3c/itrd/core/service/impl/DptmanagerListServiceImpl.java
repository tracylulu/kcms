package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.DptmanagerListDO;
import com.h3c.itrd.core.mapper.DptmanagerListMapper;
import com.h3c.itrd.core.service.DptmanagerListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 部门领导表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class DptmanagerListServiceImpl extends ServiceImpl<DptmanagerListMapper, DptmanagerListDO> implements DptmanagerListService {
	@Autowired
	private DptmanagerListMapper dptmanagerListMapper;

	@Override
	public String getLevelById(String id) {
		return dptmanagerListMapper.getLevelById(id);
	}
	
	@Override
    public List<DptmanagerListDO> getDptManagerBydptcode(String dptcode) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("dptcode",dptcode);
        return dptmanagerListMapper.selectList(queryWrapper);
    }
	
	@Override
	@Transactional
	public void batchSyncDeptManager(List<Map<String, Object>> deptManagerList) {
		List<DptmanagerListDO> dptmanagerList = new ArrayList<DptmanagerListDO>();
		try {
			for (Map<String, Object> deptManager : deptManagerList) {
				String deptCode = (String) deptManager.get("dept_code");
				String empCode = (String) deptManager.get("emp_code");
				String empName = (String) deptManager.get("emp_name");
				DptmanagerListDO  dptmanager = new DptmanagerListDO();
				dptmanager.setDptcode(deptCode);
				dptmanager.setId(empCode);
				dptmanager.setName(empName);
				dptmanager.setSyncTime(new Date());
				dptmanagerList.add(dptmanager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		deleteAllSyncInfo();
		batchInsertDeptManager(dptmanagerList);
	}
	
	@Override
	public void batchInsertDeptManager(List<DptmanagerListDO> dptmanagerList) {
		dptmanagerListMapper.batchInsertDeptManager(dptmanagerList);
	}
	
	@Override
	public int deleteAllInfo() {
		return dptmanagerListMapper.deleteAllInfo();
	}
	
	@Override
	public int deleteAllSyncInfo() {
		return dptmanagerListMapper.deleteAllSyncInfo();
	}

	@Override
	public List<DptmanagerListDO> getById(String id) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        return dptmanagerListMapper.selectList(queryWrapper);
	}
	
}
