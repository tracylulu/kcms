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
 * 评审信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("reviewinfo_list")
public class ReviewinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 评审id
     */
    private String reviewid;

    /**
     * 投入人时
     */
    private BigDecimal totaltime;

    /**
     * 严重问题个数
     */
    private Integer criticalnum;

    /**
     * 一般问题个数
     */
    private Integer ordinarynum;

    /**
     * 提示问题个数
     */
    private Integer tipnum;

    /**
     * 评审问题总数
     */
    private Integer totalnum;

    /**
     * 评审总DI
     */
    private BigDecimal totaldi;

    /**
     * 评审结束时间
     */
    private Date finishdate;

    /**
     * 评审链接
     */
    private String reviewurl;

    /**
     * 规模
     */
    private String scale;

    /**
     * 评审任务责任人 ID
     */
    private String personliable;

    /**
     * 20环节责任人 签名人ID
     */
    private String psshName;

    /**
     * 20环节责任人 签名时间
     */
    private Date psshTime;

    /**
     * 60评审评估环节签名人
     */
    private String pspgName;

    /**
     * 60评审评估环节签名时间
     */
    private Date pspgTime;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
