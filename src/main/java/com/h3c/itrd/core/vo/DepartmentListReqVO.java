package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentListReqVO {
	//季度集合（新的ui界面设计改成传单值了）
	//private List<String> quarterList;
	private String quarter;
	//二级部门code
    private String seconddpt;
    //三级部门code集合
    private List<String> thirddptList;
    //四级部门code集合
    private List<String> currentdptList;
    //统计单位
    private String group;
    //员工职类
    private String postType;
    //城市
    private String city;
    //是否含特殊岗位
    private String special;
    //加一个排序字段（初始默认是部门排名）
    private int sortedFlag; 
    //升序降序的标志（0表示升序，1表示降序）
    private int flag;
    
    //当前页
    private Integer pageNum;
    //每页显示条目数
    private Integer pageSize;
}
