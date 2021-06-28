package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 关键事件审核表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("add_score_audit")
public class AddScoreAuditDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 得分人域账号
     */
    private String userAccount;

    /**
     * 审核人域账号
     */
    private String auditAccount;

    /**
     * 申请加分选项ID
     */
    private Integer addScoreId;

    /**
     * 最终得分
     */
    private BigDecimal score;

    /**
     * 当前状态
     */
    private Integer status;

    /**
     * 抄送人域账号
     */
    private String copyAccount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 申请时间
     */
    private Date replyDate;

    /**
     * 审核时间
     */
    private Date auditDate;

    /**
     * 审批状态
     */
    private String auditStatus;

    /**
     * 季度
     */
    private String quarter;


}
