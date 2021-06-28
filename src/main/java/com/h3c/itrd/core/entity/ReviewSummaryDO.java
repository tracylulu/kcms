package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评审得分统计表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("review_summary")
public class ReviewSummaryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 总数量
     */
    private Integer totalnum;

    /**
     * 鎬籇I
     */
    private BigDecimal totaldi;

    /**
     * 员工域账号
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

    private BigDecimal totaltime;

    /**
     * 严重问题数量
     */
    private Integer criticalnum;

    /**
     * 一般问题数量
     */
    private Integer ordinarynum;

    /**
     * 提示问题数量
     */
    private Integer tipnum;

    /**
     * 二级部门编码
     */
    private String seconddptcode;

    /**
     * 员工职类
     */
    private String type;

    private String domainid;

    /**
     * 汇算日期
     */
    @TableField("countDate")
    private Date countDate;
}
