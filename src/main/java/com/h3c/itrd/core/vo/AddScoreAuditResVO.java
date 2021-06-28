package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddScoreAuditResVO {
    private String userAccount;
    private String quarter;
    private String name;
    private Double score;
    private String auditStatus;
    private Double A;
    private Double B;
    private Double C;

}
