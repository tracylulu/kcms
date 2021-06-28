package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 生产质量问题QMS信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("qmsinfo_list")
public class QmsinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程单号
     */
    private String billNo;

    /**
     * 问题发生地点
     */
    private String questionLocation;

    /**
     * 问题描述
     */
    private String questionDesc;

    /**
     * 问题编码
     */
    private String materialCode;

    /**
     * 产品描述
     */
    private String materialDesc;

    /**
     * 产品版本
     */
    private String materialVersion;

    /**
     * 产品种类
     */
    private String materialMiddleType;

    /**
     * 产品系列
     */
    private String materialSeries;

    /**
     * 产品状态
     */
    private String materialStatus;

    /**
     * 分析人
     */
    private String handler;

    /**
     * 二级部门
     */
    private String secondDeptName;

    /**
     * 三级部门
     */
    private String thirdDeptName;

    /**
     * 问题级别
     */
    private String questionLevel;

    /**
     * 原因大类
     */
    private String reasonBig;

    /**
     * 原因小类
     */
    private String reasonSmall;

    /**
     * 原因子类
     */
    private String reasonSub;

    /**
     * 原因
     */
    private String reason;

    /**
     * 处理措施
     */
    private String treatmentMeasures;

    /**
     * 预防措施
     */
    private String preventiveMeasure;

    /**
     * 处理人分析最后提交时间
     */
    private Date lastUpdateDate;

    /**
     * 流程结束时间
     */
    private Date endTime;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
