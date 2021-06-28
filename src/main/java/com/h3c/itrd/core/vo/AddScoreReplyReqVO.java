package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Created by chenlulu on 2021/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddScoreReplyReqVO {
    private String quarter;
    private String id;
    private String auditAccount;
    private List<String> copyAccount;
    private List<Integer> addScoreId;
    private List<String> remark;



}
