package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评审信息备份表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("reviewinfo_bak_list")
public class ReviewinfoBakListDO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Float totaltime;

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
    private Float totaldi;

    /**
     * 评审结束时间
     */
    private Date finishdate;

    /**
     * 评审链接
     */
    private String reviewurl;


}
