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
 * 问题单硬件得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("idm_hardware")
public class IdmHardwareDO implements Serializable {

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
     * 总分
     */
    private BigDecimal totalScore;

    /**
     * 提单数量
     */
    @TableField("SubmitNum")
    private Integer SubmitNum;

    /**
     * 修改实施数量
     */
    @TableField("EditNum")
    private Integer EditNum;

    /**
     * 问题定位数量
     */
    @TableField("LocationNum")
    private Integer LocationNum;

    /**
     * 回归不通过次数
     */
    @TableField("TestNotPassNum")
    private Integer TestNotPassNum;

    /**
     * 问题单总得分
     */
    private BigDecimal idmScore;

    /**
     * 待分析件得分
     */
    private BigDecimal analyzeScore;

    /**
     * 待分析件数量
     */
    private Integer analyzeNum;

    /**
     * 质量问题得分
     */
    private BigDecimal qmsScore;

    /**
     * 质量问题数量
     */
    private Integer qmsNum;

    private Integer testNum;

    private BigDecimal testScore;

    private String type;


}
