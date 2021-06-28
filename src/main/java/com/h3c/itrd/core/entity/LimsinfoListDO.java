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
 * LIMS测试信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("limsinfo_list")
public class LimsinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 11步关闭日期
     */
    @TableField("CreateTime")
    private Date CreateTime;

    /**
     * 试验编号
     */
    @TableField("SnID")
    private String SnID;

    /**
     * 申请人
     */
    @TableField("User")
    private String User;

    /**
     * 试验大项
     */
    @TableField("EntryType")
    private String EntryType;

    /**
     * 产品线
     */
    @TableField("PDTName")
    private String PDTName;

    /**
     * 产品版本
     */
    @TableField("PDTSubName")
    private String PDTSubName;

    /**
     * 产品型号
     */
    @TableField("ProductType")
    private String ProductType;

    /**
     * EUT数量
     */
    @TableField("EUTNum")
    private Integer EUTNum;

    /**
     * 08B测试配置数
     */
    @TableField("ConfigNum")
    private String ConfigNum;

    /**
     * 试验类别
     */
    @TableField("ExpType")
    private String ExpType;

    /**
     * 试验周期
     */
    @TableField("ExpTime")
    private Integer ExpTime;

    /**
     * 当期状态
     */
    @TableField("Status")
    private String Status;

    /**
     * 08A.试验执行结束签名时间
     */
    @TableField("ExpEndTime")
    private Date ExpEndTime;

    /**
     * 试验实际天数QA
     */
    @TableField("ExpDayQA")
    private String ExpDayQA;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
