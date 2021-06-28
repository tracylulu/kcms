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
 * 项目得分汇总表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_summary")
public class ProjectSummaryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 项目名称
     */
    @TableField("projectName")
    private String projectName;

    /**
     * 项目规模
     */
    @TableField("Scale")
    private String Scale;

    /**
     * 项目ID
     */
    @TableField("projectId")
    private String projectId;

    /**
     * 人员
     */
    private String account;

    /**
     * 开发得分
     */
    @TableField("Sa")
    private BigDecimal Sa;

    /**
     * 验收扣分
     */
    @TableField("A_min")
    private BigDecimal aMin;

    /**
     * 进度扣分
     */
    @TableField("B_min")
    private BigDecimal bMin;

    /**
     * 鉴定扣分
     */
    @TableField("C_min")
    private BigDecimal cMin;

    /**
     * 审计扣分
     */
    @TableField("D_min")
    private BigDecimal dMin;

    /**
     * 项目满分
     */
    private BigDecimal s1x;

    /**
     * 贡献度
     */
    private BigDecimal contributevalue;

    /**
     * 项目总得分
     */
    @TableField("Sa_count")
    private BigDecimal saCount;

    /**
     * 项目类型
     */
    private String type;

    /**
     * V模型项目还是普通项目
     */
    @TableField("projectType")
    private String projectType;

    /**
     * 员工当季工作量：当季总工作量(项目当量表)*当季员工贡献度百分比
     */
    @TableField("accountWork")
    private BigDecimal accountWork;
    
    /**
     * 汇算日期
     */
    @TableField("countDate")
    private Date countDate;
}
