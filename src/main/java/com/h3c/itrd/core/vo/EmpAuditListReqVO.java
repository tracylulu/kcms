package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmpAuditListReqVO {
    private String id;
    private String quarter;
    private Integer pageNum;
    private Integer pageSize;

    //发现问题项传1，未发现问题项传0
    private Integer type;



}
