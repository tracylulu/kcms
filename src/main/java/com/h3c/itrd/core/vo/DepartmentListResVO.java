package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentListResVO implements Comparable{
	//总分/人数
	private Double totalScore;
	//开发得分
	private Double developScore;
	//维护得分
	private Double maintainScore;
	//评审得分
	private Double reviewScore;
	//关键事件得分
	private Double addScore;
	//平台贡献得分
	private Double platformScore;
	//当前部门
	private String currentdpt;
	//城市
    private String city;
    //总人数
    private Integer member;
    //排名
    private Integer rank;
    //特殊岗位人数
    private Integer specialMember;
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
    
   
}
