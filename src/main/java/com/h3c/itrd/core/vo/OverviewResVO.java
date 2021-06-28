package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OverviewResVO {
    private String city;
    private String name;
    private String id;
    private String level2;
    private String level3;
    private String level4;
    private String careerLevel;
    private String quarter;
    private Double total;
    private Double additional;
    private Double platform;
    private Double develop;
    private Double maintain;
    private Double review;

    private Integer headcount;
    private Integer rank;
}
