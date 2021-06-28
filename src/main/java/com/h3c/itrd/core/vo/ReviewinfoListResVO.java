package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewinfoListResVO implements Comparable{
    private Double totaldi;
    private String serialNumber;
    private Integer problemNumber;
    private Integer criticalnum;
    private Integer ordinarynum;
    private Integer tipnum;
    private Integer totalnum;
    private String url;

    private Double reviewScore;

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
