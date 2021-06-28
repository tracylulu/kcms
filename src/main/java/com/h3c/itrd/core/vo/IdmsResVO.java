package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IdmsResVO implements Comparable{

    private String num;
    private String url;
    private String resume;
    private String status;
    private String product;
    private String severity;
    private String type;
    private String baseline;
    private String baseline1;
    private String nameB;
    private String nameC;
    private String scoretype;
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}




}
