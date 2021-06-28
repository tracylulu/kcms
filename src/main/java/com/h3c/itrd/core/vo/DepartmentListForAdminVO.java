package com.h3c.itrd.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DepartmentListForAdminVO {
	
	private String name;
	private String code;
	private String dptname;
    
}
