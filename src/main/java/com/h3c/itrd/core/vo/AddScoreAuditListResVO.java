package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddScoreAuditListResVO {
    private Integer id;
    private String name;
    private String selectName;
    private String remark;
    private Date auditDate;
    private String auditStatus;
    private Date replyDate;
    private Double A;
    private Double B;
    private Double C;

}
