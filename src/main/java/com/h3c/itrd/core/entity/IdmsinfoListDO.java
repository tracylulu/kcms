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
 * 问题单备份表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("idmsinfo_list")
public class IdmsinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题单号
     */
    @TableId(value = "DefectNO", type = IdType.AUTO)
    private String DefectNO;

    /**
     * 简述
     */
    @TableField("Summery")
    private String Summery;

    /**
     * 项目id
     */
    @TableField("ProjectID")
    private String ProjectID;

    /**
     * 问题单类型，硬件问题单、鉴定问题单等
     */
    @TableField("DefectType")
    private String DefectType;

    /**
     * 问题报告质量
     */
    @TableField("ReportQuality")
    private String ReportQuality;

    /**
     * 当前处理人
     */
    @TableField("CurrentPerson")
    private String CurrentPerson;

    /**
     * 当前状态
     */
    @TableField("CurrentNode")
    private String CurrentNode;

    /**
     * 产品线
     */
    private String plipmt;

    /**
     * 产品
     */
    private String product;

    /**
     * 路标版本
     */
    private String release1;

    /**
     * 基线版本
     */
    private String baseline;

    /**
     * 平台版本号
     */
    @TableField("BASELINE_1")
    private String baseline1;

    /**
     * 责任项目组
     */
    private String team;

    /**
     * 缺陷年龄
     */
    @TableField("ODC_Age")
    private String odcAge;

    /**
     * 提交日期
     */
    @TableField("SubmitDate")
    private Date SubmitDate;

    /**
     * 提交人
     */
    @TableField("SubmitBy")
    private String SubmitBy;

    /**
     * 严重程度
     */
    @TableField("ODC_Severity")
    private String odcSeverity;

    /**
     * 开发定位人员
     */
    @TableField("Locationer")
    private String Locationer;

    /**
     * 问题修改人
     */
    @TableField("Name_B")
    private String nameB;

    /**
     * 问题修改时间
     */
    @TableField("DefectModifiedTime")
    private Date DefectModifiedTime;

    /**
     * 修改审核人
     */
    @TableField("Name_C")
    private String nameC;

    /**
     * 授权修改人
     */
    @TableField("ModifyAuthorizedBy")
    private String ModifyAuthorizedBy;

    /**
     * 版本归档人
     */
    @TableField("ArchiveBy")
    private String ArchiveBy;

    /**
     * 原问题单号
     */
    @TableField("BatchCopyFromNo")
    private String BatchCopyFromNo;

    /**
     * 同步序号
     */
    @TableField("SyNo")
    private String SyNo;

    /**
     * CMO归档时间
     */
    @TableField("CMOArchiveTime")
    private Date CMOArchiveTime;

    /**
     * 测试经理组织测试时间
     */
    @TableField("TestArrangedTime")
    private Date TestArrangedTime;

    /**
     * 回归测试人员
     */
    @TableField("TestBy")
    private String TestBy;

    /**
     * 测试时间
     */
    @TableField("TestTime")
    private Date TestTime;

    /**
     * 回归不过次数
     */
    @TableField("TestNotPass")
    private Integer TestNotPass;

    /**
     * 测试（项目）经理  签名人
     */
    @TableField("TestApprover")
    private String TestApprover;

    /**
     * 测试（项目）经理  签名时间
     */
    @TableField("TMApproveTime")
    private Date TMApproveTime;

    /**
     * 问题单链接
     */
    @TableField("DefectUrl")
    private String DefectUrl;

    /**
     * 问题单类型，需要后台判定是普通单还是同步单
     */
    @TableField("IdmsType")
    private String IdmsType;

    /**
     * 项目流水号（与Isoft对应）
     */
    @TableField("ProjectSerialNum")
    private String ProjectSerialNum;

    /**
     * 是否为新单
     */
    @TableField("isNew")
    private String isNew;

    /**
     * 更新日期
     */
    @TableField("UpdateDate")
    private Date UpdateDate;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
