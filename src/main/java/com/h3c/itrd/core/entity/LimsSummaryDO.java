package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 专业实验汇总表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lims_summary")
public class LimsSummaryDO implements Serializable {

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
    private BigDecimal score;

    /**
     * 气候类数量
     */
    private Integer qihouNum;

    /**
     * halt类数量
     */
    private Integer haltNum;

    /**
     * emc类数量
     */
    private Integer emcNum;

    /**
     * 包材类数量
     */
    private Integer baocaiNum;

    /**
     * 安规类数量
     */
    private Integer anguiNum;

    /**
     * 裸机机械类数量
     */
    private Integer luojiNum;

    /**
     * 噪声类数量
     */
    private Integer zaoshengNum;

    /**
     * 其他类数量
     */
    private Integer otherNum;


}
