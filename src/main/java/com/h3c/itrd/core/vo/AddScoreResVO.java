package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddScoreResVO {
    private Double score;
    private String scoreType;
    private String description;
    private String type;
    private String account;




}
