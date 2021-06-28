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
 * 测试设计得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("design_score")
public class DesignScoreDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 域账号
     */
    private String account;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 总分
     */
    private BigDecimal totalScore;

    /**
     * 测试点得分
     */
    private BigDecimal pointScore;

    /**
     * 测试用例得分
     */
    private BigDecimal exampleScore;

    /**
     * 测试设计得分
     */
    private BigDecimal scriptScore;

    /**
     * 测试点数量
     */
    private Integer pointNum;

    /**
     * 测试用例数量
     */
    private Integer exampleNum;

    /**
     * 测试设计数量
     */
    private Integer scriptNum;

    /**
     * 接口函数得分
     */
    private BigDecimal functionScore;

    /**
     *  接口函数得分
     */
    private Integer functionNum;

    /**
     * 私有测试设计得分
     */
    private BigDecimal privateScore;

    /**
     * 私有测试设计数量
     */
    private Integer priveteNum;


}
