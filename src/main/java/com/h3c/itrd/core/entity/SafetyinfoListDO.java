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
 * 安规认证信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("safetyinfo_list")
public class SafetyinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程状态
     */
    private String wfCurrentnodename;

    private String introduction;

    private String wfAddname;

    private String prodline;

    /**
     * PDT
     */
    private String pdt;

    /**
     * R版本
     */
    private String release1;

    /**
     * 流程单号
     */
    private String wfDocnumber;

    /**
     * 认证型号
     */
    private String certifyModel;

    /**
     * 认证种类
     */
    private String certifyKind;

    /**
     * 认证类型
     */
    private String certifyType;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 报告名称
     */
    private String reportName;

    /**
     * 71环节签名时间
     */
    @TableField("SigningTime71")
    private Date SigningTime71;

    /**
     * 72环节签名时间
     */
    @TableField("SigningTime72")
    private Date SigningTime72;

    /**
     * 73环节签名时间
     */
    @TableField("SigningTime73")
    private Date SigningTime73;

    /**
     * 74环节签名时间
     */
    @TableField("SigningTime74")
    private Date SigningTime74;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
