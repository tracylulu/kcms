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
 * Comware产品的代码鉴定结果
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("isoft_product_identification")
public class IsoftProductIdentificationDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日期
     */
    private Date createDate;

    /**
     * 状态
     */
    private String pdStatus;

    /**
     * 代码鉴定ID
     */
    private String appraiseId;

    /**
     * 项目
     */
    private String appraise;

    /**
     * 地点
     */
    private String place;

    /**
     * 组件
     */
    private String assembly;

    /**
     * 模块
     */
    private String module;

    /**
     * 规模
     */
    private BigDecimal scale;

    /**
     * 抽查
     */
    private BigDecimal spotCheck;

    /**
     * 开发责任人
     */
    private String devPerson;

    /**
     * 鉴定责任人
     */
    private String identPerson;

    /**
     * DI
     */
    private BigDecimal di;

    /**
     * DI评价
     */
    private String diEva;

    /**
     * 代码鉴定评价
     */
    private String appraiseEva;

    /**
     * 说明
     */
    private String description;

    /**
     * 致命
     */
    private BigDecimal deadly;

    /**
     * 严重
     */
    private BigDecimal serious;

    /**
     * 一般
     */
    private BigDecimal common;

    /**
     * 提示
     */
    private BigDecimal prompt;

    /**
     * 表单
     */
    private String form;

    /**
     * tag
     */
    private String tag;

    /**
     * 不通过
     */
    private Integer failed;

    /**
     * 告警
     */
    private String warning;

    /**
     * 特性团队
     */
    private String featureTeam;

    /**
     * 密度
     */
    private String density;

    /**
     * 归属组件
     */
    private String beComponent;

    /**
     * BASE
     */
    private Integer base;

    /**
     * 开始鉴定天数
     */
    private Integer identifyDays;

    /**
     * 扩展1字段
     */
    @TableField("Ext1")
    private String Ext1;

    /**
     * 其他
     */
    private Integer other;

    /**
     * V7&V9
     */
    private String v7V9;

    /**
     * 同步时间
     */
    private Date syncTime;


}
