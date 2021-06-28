package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.common.consts.SystemConstant;
import com.h3c.itrd.core.entity.DepartmentConfigDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.PostTypeConstantDO;
import com.h3c.itrd.core.mapper.EmployeeListMapper;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.PostTypeConstantService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class EmployeeListServiceImpl extends ServiceImpl<EmployeeListMapper, EmployeeListDO> implements EmployeeListService {

    @Autowired
    private EmployeeListMapper employeeListMapper;
    @Autowired
    private PostTypeConstantService postTypeConstantService;
    
    @Override
    public EmployeeListDO getEmployeeById(String id) {
        EmployeeListDO employeeListDO = employeeListMapper.selectById(id);
        return employeeListDO;
    }

    @Override
    public List<EmployeeListDO> getSpecialList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (param.get("seconddptcode") != null) {
            queryWrapper.eq("seconddptcode",param.get("seconddptcode"));
        }
        queryWrapper.eq("special",param.get("special"));

        List list = employeeListMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<EmployeeListDO> getList(Map<String, Object> param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (param.get("domainid") != null) {
            queryWrapper.eq("domainid",param.get("domainid"));
        }
        if (param.get("postTypeSoft") != null) {
            queryWrapper.in("postType",SystemConstant.SOFTWARE_DEV,SystemConstant.SOFTWARE_TEST);
        }
        if (param.get("postType") != null) {
            queryWrapper.eq("postType",param.get("postType"));
        }
        return employeeListMapper.selectList(queryWrapper);
    }

    @Override
    public EmployeeListDO getEmployeeByAccount(String account) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        return employeeListMapper.selectOne(queryWrapper);
    }



    @Override
    public EmployeeListDO getByAccountAndType(String account, String postType) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        queryWrapper.eq("postType",postType);
        return employeeListMapper.selectOne(queryWrapper);
    }

    @Override
    public int getCount(String seconddptcode, String postType) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("seconddptcode",seconddptcode);
        queryWrapper.eq("postType",postType);
        Integer count = employeeListMapper.selectCount(queryWrapper);
        return count;
    }

	@Override
	public int getCountGroupByDeptCode(String seconddptcode, String postType, String group) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("seconddptcode",seconddptcode);
        queryWrapper.eq("postType",postType);
        if(group.equals("thirddptcode")) {
        	queryWrapper.groupBy("thirddptcode");
        }else {
        	queryWrapper.groupBy("currentdptcode");
        }
        
        Integer count = employeeListMapper.selectList(queryWrapper).size();
        return count;
	}

	@Override
	public EmployeeListDO getEmployeeByDomainid(String domainid) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("domainid",domainid);
        return employeeListMapper.selectOne(queryWrapper);
	}

	@Override
	public List<EmployeeListDO> getListByQuery(String thirddptcode, String query) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("thirddptcode",thirddptcode);
        queryWrapper.like("account", query);
        return employeeListMapper.selectList(queryWrapper);
	}
	
	@Override
	public void batchSyncEmployeeInfo(List<Map<String, Object>> userInfoList,List<DepartmentConfigDO> departmentConfigList) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeListDO> employeeInfoList = new ArrayList<EmployeeListDO>();
		try {
			List<PostTypeConstantDO> postTypeConstantList = postTypeConstantService.selectPostType(1);
			for (Map<String, Object> userInfo : userInfoList) {
				Boolean flag = false;
				String firstdptcode = (String) userInfo.get("firstDeptCode");
				String seconddptcode = (String) userInfo.get("secondDeptCode");
				Optional<DepartmentConfigDO> departmentConfigInfo= departmentConfigList.stream().filter(o -> firstdptcode.equals((String) o.getDept1Code())).findAny();
				if(departmentConfigInfo.isPresent()) {
					String dept2Code = departmentConfigInfo.get().getDept2Code();
					if (!"all".equals(dept2Code.toLowerCase())) {
						String[] dept2CodeArray = dept2Code.split(",");
						for(String dept2CodeString:dept2CodeArray) {
							flag = dept2CodeString.equals(seconddptcode);
							if(flag) break;
						}
					}
					else {
						flag = true;
					}
				}
				if (flag) {
					String id = (String) userInfo.get("empCode");
					String domainid = (String) userInfo.get("domainAccount");
					String account = (String) userInfo.get("notesId");
					String name = (String) userInfo.get("empName");
					String type = (String) userInfo.get("empType");
					String firstdpt = (String) userInfo.get("firstDeptName");
					String seconddpt = (String) userInfo.get("secondDeptName");
					String thirddptcode = (String) userInfo.get("thirdDeptCode");
					String thirddpt = (String) userInfo.get("thirdDeptName");
					String currentdptcode = (String) userInfo.get("deptCode");
					String currentdpt = (String) userInfo.get("deptName");
					String position = (String) userInfo.get("workPlace");
					String entryDateString = (String) userInfo.get("entryDate");
					String positiontype = (String) userInfo.get("positionType");
					String positionname = (String) userInfo.get("positionName");
					String postName1 = (String) userInfo.get("postName1");
					String postName2 = (String) userInfo.get("postName2");
					String postName3 = (String) userInfo.get("postName3");
					Date EntryDate = null;
					EntryDate = entryDateString == null ? null : sdf.parse(entryDateString);
					EmployeeListDO employeeInfo = new EmployeeListDO();
					employeeInfo.setId(id);
					employeeInfo.setDomainid(domainid);
					employeeInfo.setAccount(account);
					employeeInfo.setName(name);
					employeeInfo.setType(type);
					employeeInfo.setFirstdptcode(firstdptcode);
					employeeInfo.setFirstdpt(firstdpt);
					employeeInfo.setSeconddptcode(seconddptcode);
					employeeInfo.setSeconddpt(seconddpt);
					employeeInfo.setThirddptcode(thirddptcode);
					employeeInfo.setThirddpt(thirddpt);
					employeeInfo.setCurrentdptcode(currentdptcode);
					employeeInfo.setCurrentdpt(currentdpt);
					employeeInfo.setPosition(position);
					employeeInfo.setEntryDate(EntryDate);
					employeeInfo.setPositiontype(positiontype);
					employeeInfo.setPositionname(positionname);
					employeeInfo.setPostName1(postName1);
					employeeInfo.setPostName2(postName2);
					employeeInfo.setPostName3(postName3);
					employeeInfo.setSyncTime(new Date());
					employeeInfo.setSysDept4Code(currentdptcode);
					employeeInfo.setSysDept4Name(currentdpt);
					employeeInfo.setSysCity(position);
					String postType ="其他";
					postType = getPostType(postTypeConstantList,employeeInfo);
					employeeInfo.setPostType(postType);
					employeeInfoList.add(employeeInfo);	
				}
			}
//			deleteAllSyncEmployeeInfo();
			batchUpdateEmployeeInfoForSys(employeeInfoList);
			batchUpdateEmployeeInfo(employeeInfoList);
			batchInsertEmployeeInfo(employeeInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public int batchInsertEmployeeInfo(List<EmployeeListDO> employeeInfoList) {
		return employeeListMapper.batchInsertEmployeeInfo(employeeInfoList);
	}
	
	@Override
	public int batchUpdateEmployeeInfo(List<EmployeeListDO> employeeInfoList) {
		return employeeListMapper.batchUpdateEmployeeInfo(employeeInfoList);
	}
	
	@Override
	public int batchUpdateEmployeeInfoForSys(List<EmployeeListDO> employeeInfoList) {
		return employeeListMapper.batchUpdateEmployeeInfoForSys(employeeInfoList);
	}
	
	@Override
	public int deleteAllSyncEmployeeInfo() {
		return employeeListMapper.deleteAllSyncEmployeeInfo();
	}
	
	@Override
	public String getPostType(List<PostTypeConstantDO> postTypeConstantList, EmployeeListDO employeeInfo) {
		String postType ="其他";
		String postName1 = employeeInfo.getPostName1();
		String postName2 = employeeInfo.getPostName2();
		String postName3 = employeeInfo.getPostName3();
		String positionType = employeeInfo.getPositiontype();
		String positionName = employeeInfo.getPositionname();
		if (postName1 == null || "".equals(postName1)) {
			if (positionType == null || positionName ==null) {
				return postType;
			} else {
				Optional<PostTypeConstantDO> postTypeConstantDO = postTypeConstantList.stream().filter(o-> positionType.equals((String) o.getPostionType()) && positionName.contains((String) o.getPostionName())).findAny();
				if (postTypeConstantDO.isPresent()) {
					postType = postTypeConstantDO.get().getPostType();
				}
			}
		} else {
			List<PostTypeConstantDO> postTypeNotPostName3List = postTypeConstantList.stream().filter(o-> o.getPostName3()==null &&  o.getPostName1() !=null).collect(Collectors.toList());
			Optional<PostTypeConstantDO> postTypeConstantNotPostName3 = postTypeNotPostName3List.stream().filter(o-> o.getPostName1().equals(postName1) && o.getPostName2().equals(postName2)).findAny();
			if(postTypeConstantNotPostName3.isPresent()) {
				postType = postTypeConstantNotPostName3.get().getPostType();
			}
			List<PostTypeConstantDO> postTypePostName3List = postTypeConstantList.stream().filter(o-> o.getPostName3() !=null &&  o.getPostName1() !=null).collect(Collectors.toList());
			Optional<PostTypeConstantDO> postTypeConstantPostName3 = postTypePostName3List.stream().filter(o-> o.getPostName1().equals(postName1) && o.getPostName2().equals(postName2) && o.getPostName3().equals(postName3)).findAny();
			if(postTypeConstantPostName3.isPresent()) {
				postType = postTypeConstantPostName3.get().getPostType();
			}
		}
		return postType;
	}

	@Override
	public List<EmployeeListDO> getListByQueryParam(Map<String, Object> param) {
		QueryWrapper queryWrapper=new QueryWrapper();
		if (param.get("seconddptcode") != null) {
			queryWrapper.eq("seconddptcode",param.get("seconddptcode"));
		}
        if (param.get("thirddptcode") != null) {
        	queryWrapper.eq("thirddptcode",param.get("thirddptcode"));
		}
        if (param.get("currentdptcode") != null) {
        	queryWrapper.eq("currentdptcode",param.get("currentdptcode"));	
		}
        if (param.get("fivedptcode") != null) {
        	queryWrapper.eq("fivedptcode",param.get("fivedptcode"));
		}
        if (param.get("position") != null) {
        	queryWrapper.eq("position",param.get("position"));
		}
        if (param.get("postType") != null) {
        	queryWrapper.eq("postType",param.get("postType"));
		}
        if (param.get("type") != null) {
        	queryWrapper.eq("type",param.get("type"));
		}
        if (param.get("employeeSearchKey") != null) {
        	queryWrapper.like("account", param.get("employeeSearchKey"));
		}
        return employeeListMapper.selectList(queryWrapper);
	}
    
}
