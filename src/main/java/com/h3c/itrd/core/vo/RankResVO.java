package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RankResVO {
    private String account;
    private String type;
    private Double score;
    private Integer rownum;



}
