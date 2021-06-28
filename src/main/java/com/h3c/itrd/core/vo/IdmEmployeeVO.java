package com.h3c.itrd.core.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IdmEmployeeVO {
    private Integer count;
    private Integer synccount;
    private Double maintainscore;
    private Double score;
    private Double syncscore;
}
