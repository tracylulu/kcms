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
 * Comware产品的特性审计结果
 * </p>
 *
 * @author c17740
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("isoft_product_feature")
public class IsoftProductFeatureDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 特性名称
     */
    private String projectName;

    /**
     * 业务组
     */
    private String businessGroup;

    /**
     * 特性团队
     */
    private String featureTeam;

    /**
     * 开发责任人
     */
    private String devPerson;

    /**
     * 自动审计结果
     */
    private String autoAuditResult;

    /**
     * 人工审计
     */
    private String manAuditResult;

    /**
     * 自动审计扣分
     */
    private BigDecimal autoAuditDeduct;

    /**
     * 人工审计扣分
     */
    private BigDecimal manAuditDeduct;

    /**
     * 总得分
     */
    private BigDecimal totalScore;

    /**
     * 审计得分
     */
    private BigDecimal auditScore;

    /**
     * 同步时间
     */
    private Date syncTime;


}
