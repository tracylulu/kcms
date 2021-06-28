package com.h3c.itrd.core.entity;

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
 * 经验案例信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("experienceinfo_list")
public class ExperienceinfoListDO implements Serializable {

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
     * 案例名称
     */
    @TableField("CaseName")
    private String CaseName;

    /**
     * 案例作者
     */
    @TableField("CaseAuthor")
    private String CaseAuthor;

    /**
     * 中文姓名
     */
    @TableField("ChineseName")
    private String ChineseName;

    /**
     * 案例归档时间
     */
    @TableField("ApplicatEndTime")
    private Date ApplicatEndTime;

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
     * 案例价值评价
     */
    @TableField("CaseValueEvaluation")
    private String CaseValueEvaluation;

    /**
     * 文档申请时间
     */
    @TableField("ApplicatTime")
    private Date ApplicatTime;

    /**
     * 摘要和关键字信息
     */
    @TableField("SummaryAndkeyword")
    private String SummaryAndkeyword;

    /**
     * 文档大类
     */
    @TableField("DcMainCategory")
    private String DcMainCategory;

    /**
     * 文档小类
     */
    @TableField("DcSubCategory")
    private String DcSubCategory;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
