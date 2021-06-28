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
 * 硬件测试设计表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("design_hardware_score")
public class DesignHardwareScoreDO implements Serializable {

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
     * 测试用例数量
     */
    private Integer exampleNum;

    /**
     * 测试脚本数量
     */
    private Integer toolNum;

    /**
     * 器件建库数量
     */
    private Integer storeNum;

    /**
     * 测试用例得分
     */
    private BigDecimal exampleScore;

    /**
     *  测试脚本得分
     */
    private BigDecimal toolScore;

    /**
     * 器件建库得分
     */
    private BigDecimal storeScore;

    /**
     * 总得分
     */
    private BigDecimal totalScore;


}
