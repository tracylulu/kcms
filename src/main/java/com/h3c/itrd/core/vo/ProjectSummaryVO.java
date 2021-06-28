package com.h3c.itrd.core.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectSummaryVO {
    private Double score;
    private Double developcount;
    private Double contribution;
    private String name;
    private String size;

    private Integer totalCount;
    private Double totalScore;
    private Double totalAmin;
    private Double totalBmin;
    private Double totalCmin;
    private Double totalDmin;
    
}
