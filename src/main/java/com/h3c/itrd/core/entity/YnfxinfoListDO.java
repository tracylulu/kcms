package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 疑难复现电子流信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ynfxinfo_list")
public class YnfxinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问题单号
     */
    private String problemno;

    /**
     * 产品
     */
    private String productinfo;

    /**
     * 产品线
     */
    private String plipmt;

    /**
     * 05复现状态
     */
    private String status;

    /**
     * 05签名
     */
    @TableField("USERID_05")
    private String userid05;

    /**
     * 05签名时间
     */
    @TableField("ENDTIME_05")
    private Date endtime05;

    /**
     * 06b签名
     */
    @TableField("USERID_06B")
    private String userid06b;

    /**
     * 06b签名时间
     */
    @TableField("ENDTIME_06B")
    private Date endtime06b;

    /**
     * 投入时间（小时）
     */
    private String totaltimes;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
