package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 软件开发问题单得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("idm_employee")
public class IdmEmployeeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 二级部门编码
     */
    private String seconddptcode;

    /**
     * 账户
     */
    private String account;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 总得分
     */
    private BigDecimal totalscore;

    /**
     * 普通单得分总数
     */
    @TableField("normalTotalScore")
    private BigDecimal normalTotalScore;

    /**
     * 普通单提单数量
     */
    @TableField("normalSubmitNum")
    private Integer normalSubmitNum;

    /**
     * 普通单修改数量
     */
    @TableField("normalEditNum")
    private Integer normalEditNum;

    /**
     * 普通单审核数量
     */
    @TableField("normalExamineNum")
    private Integer normalExamineNum;

    /**
     * 同单得分数量
     */
    @TableField("syncTotalScore")
    private BigDecimal syncTotalScore;

    /**
     * 同步单修改得分
     */
    @TableField("syncEditNum")
    private Integer syncEditNum;

    /**
     * 同步单审核数量
     */
    @TableField("syncExamineNum")
    private Integer syncExamineNum;

    /**
     * 普通单定位数量
     */
    @TableField("normalLocationNum")
    private Integer normalLocationNum;

    /**
     * 汇算日期
     */
    @TableField("countDate")
    private Date countDate;

}
