package com.h3c.itrd.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Created by chenlulu on 2021/5/21
 * 补录审核页面个人关键事件得分审核功能中的传参
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddScoreAuditListReqVO {
    private String id;
    private List<Integer> itemIdList;
    private List<String> auditStatusList;
    

}
