package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeListResVO implements Comparable{
	
	
	private String account;
	private String name;
	private String id;
	private String position;
	private String postType;
	private String positiontype;
	private Double totalScore;
	private Double reviewScore;
	private Double maintainScore;
	private Double developScore;
	private Double addScore;
	private Double platformScore;
	private Double chargeScore;
	private String positionlevel;
	private String firstdpt;
	private String seconddpt;
	private String thirddpt;
	private String currentdpt;
	private String fivedpt;
	private String quarter;
	private String special;
	private Integer rank;
	private Integer rank1;
	private String detailUrl;
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
   
	
}