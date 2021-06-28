package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeListReqVO {
	
	private String seconddpt;
	private String thirddpt;
	private String currentdpt;
	private String fivedpt;
	private String postType;
	private String city;
    private String positiontype;
    //季度集合（新的ui界面设计改成传单值了）
  	//private List<String> quarterList;
  	private String quarter;
  	
    private String employeeSearchKey;
   
    //加一个排序字段（初始默认是部门排名）
    private int sortedFlag; 
    //升序降序的标志（0表示升序，1表示降序）
    private int flag;
    
    //当前页
    private Integer pageNum;
    //每页显示条目数
    private Integer pageSize;
}
