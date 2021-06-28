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
 * 员工季度总得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("quarter_score")
public class QuarterScoreDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工
     */
    private String account;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 季度总得分
     */
    private BigDecimal totalScore;

    /**
     * 审计得分
     */
    private BigDecimal reviewScore;

    /**
     * 维护总得分
     */
    private BigDecimal maintainScore;

    /**
     * 开发总得分
     */
    private BigDecimal developScore;

    /**
     * 其他总得分
     */
    private BigDecimal addScore;

    private BigDecimal platformScore;

    private String type;

    /**
     * 领导加分
     */
    private BigDecimal chargeScore;

    /**
     * 修改时间
     */
    private String updateTime;

    private String firstdptcode;

    private String seconddptcode;

    private String thirddptcode;

    private String currentdptcode;

    private String fivedptcode;

    private String firstdpt;

    private String seconddpt;

    private String thirddpt;

    private String currentdpt;

    private String fivedpt;

    private String special;


}
