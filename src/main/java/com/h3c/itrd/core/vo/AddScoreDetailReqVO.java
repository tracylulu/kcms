package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddScoreDetailReqVO {
    private String id;
    private String quarter;
    //private String project;
    private Integer pageNum;
    private Integer pageSize;




}
