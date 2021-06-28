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
 * 特殊事件得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("charge_score")
public class ChargeScoreDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 得分
     */
    private BigDecimal score;

    /**
     * 事由
     */
    private String reason;

    /**
     * 加分时间
     */
    private String time;

    /**
     * 操作人ID
     */
    private String chargeId;

    /**
     * 季度
     */
    private String quarter;


}
