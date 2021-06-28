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
 * 认证测试汇总表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("safety_summary")
public class SafetySummaryDO implements Serializable {

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
     * ccc认证总数量
     */
    private Integer cccNum;

    /**
     * cqc认证总数量
     */
    private Integer cqcNum;

    /**
     * 十环认证总数量
     */
    private Integer tenNum;


}
