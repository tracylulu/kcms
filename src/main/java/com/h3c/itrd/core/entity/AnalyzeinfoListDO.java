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
 * 待分析件信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("analyzeinfo_list")
public class AnalyzeinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    @TableField("WF_DocNumber")
    private String wfDocnumber;

    /**
     * 产品线
     */
    private String productline;

    /**
     * 产品
     */
    private String product;

    /**
     * 物料编码
     */
    @TableField("materielNo")
    private String materielNo;

    /**
     * 物料描述
     */
    private String veneer;

    /**
     * 故障现象简单描述
     */
    private String trouble;

    /**
     * 问题定位难度
     */
    private String fixDifficulty;

    /**
     * 维护经理签名
     */
    private String fillname19;

    /**
     * 维护经理审批时间
     */
    private Date filltime19;

    /**
     * 板件分析人签名
     */
    private String fillname7;

    /**
     * 板件分析人处理时间
     */
    private Date filltime7;

    /**
     * 流程当前状态
     */
    @TableField("WF_currentnodename")
    private String wfCurrentnodename;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
