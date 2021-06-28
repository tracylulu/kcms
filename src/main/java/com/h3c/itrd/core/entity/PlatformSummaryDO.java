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
 * 平台贡献得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("platform_summary")
public class PlatformSummaryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
    private BigDecimal totalScore;

    /**
     * 经验案例得分
     */
    private BigDecimal experienceScore;

    /**
     * 专利得分
     */
    private BigDecimal patentScore;

    /**
     * 规范得分
     */
    private BigDecimal specScore;

    /**
     * 共享技术得分
     */
    private BigDecimal sharedocScore;

    /**
     * cbb得分
     */
    private BigDecimal cbbScore;

    /**
     * 经验案例数量
     */
    private Integer experienceNum;

    /**
     * 专利数量
     */
    private Integer patentNum;

    /**
     * 规范数量
     */
    private Integer specNum;

    /**
     * 共享文档数量
     */
    private Integer sharedocNum;

    /**
     * 疑难复现得分
     */
    private Integer ynfxScore;

    /**
     * 疑难复现成功数量
     */
    private Integer ynfxSuccess;

    /**
     * 疑难复现失败数量
     */
    private Integer ynfxError;

    /**
     * CBB数量
     */
    private Integer cbbNum;

    /**
     * 汇算日期
     */
    @TableField("countDate")
    private Date countDate;
    
    /**
     * 域账号
     */
    private String domainid;

    /**
     * 工号
     */
    @TableField("userId")
    private String userId;

}
