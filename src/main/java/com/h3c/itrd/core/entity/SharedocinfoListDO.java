package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 共享技术信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sharedocinfo_list")
public class SharedocinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文档编号
     */
    @TableField("DocumentCode")
    private String DocumentCode;

    /**
     * 文档名称
     */
    @TableField("DocumentMainType")
    private String DocumentMainType;

    /**
     * 文档申请人
     */
    @TableField("Applicant")
    private String Applicant;

    /**
     * 文档归档时间
     */
    @TableField("LastModifyDate")
    private Date LastModifyDate;

    /**
     * 一级部门
     */
    @TableField("DepartmentLevel1")
    private String DepartmentLevel1;

    /**
     * 二级部门
     */
    @TableField("DepartmentLevel2")
    private String DepartmentLevel2;

    /**
     * 三级部门
     */
    @TableField("DepartmentLevel3")
    private String DepartmentLevel3;

    /**
     * 文档评价等级
     */
    @TableField("EvaluationLevel")
    private String EvaluationLevel;

    /**
     * 技术贡献得分
     */
    @TableField("TechContributionScore")
    private BigDecimal TechContributionScore;

    /**
     * 主业务
     */
    @TableField("MainBusiness")
    private String MainBusiness;

    /**
     * 子业务
     */
    @TableField("SubBusiness")
    private String SubBusiness;

    /**
     * 摘要信息
     */
    @TableField("DocumentSubType")
    private String DocumentSubType;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
