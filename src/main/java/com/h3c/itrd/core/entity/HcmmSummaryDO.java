package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 单板得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hcmm_summary")
public class HcmmSummaryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 季度
     */
    private String account;

    /**
     * 单板规模
     */
    private BigDecimal boardSize;

    /**
     * 等效新增规模
     */
    private BigDecimal addSize;

    /**
     * 项目满分
     */
    private BigDecimal fullScore;

    /**
     * 灵活字段（四种得分类型存储不同内容）
     */
    private BigDecimal flexible;

    /**
     * 项目得分
     */
    private BigDecimal score;

    /**
     * 贡献度
     */
    private BigDecimal contribution;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 得分类型
     */
    private String scoreType;

    /**
     * 印制板名
     */
    @TableField("PrintName")
    private String PrintName;

    /**
     * 开发比例
     */
    private String rx;


}
