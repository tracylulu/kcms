package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddScoreReplyListVO {
    private Integer status;
    private String quarter;
    private String reason;
    private String name;
    private Date replyDate;
    private Date auditDate;
    private String auditStatus;
    private Double score;
    private Integer id;



}
