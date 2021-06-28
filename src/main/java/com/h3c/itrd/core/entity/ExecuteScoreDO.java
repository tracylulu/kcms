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
 * 软件测试测试执行得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("execute_score")
public class ExecuteScoreDO implements Serializable {

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
    private BigDecimal totalScore;

    /**
     * 主线单DI
     */
    private BigDecimal mainDi;

    /**
     * 项目单总DI
     */
    private BigDecimal projectDi;

    /**
     * 总提单数
     */
    private Integer totalSubmit;

    /**
     * 总审核单数
     */
    private Integer totalAudit;

    /**
     * 同步单数量
     */
    private Integer syncNum;

    /**
     * 主线单数量
     */
    private Integer mainNum;

    /**
     * 项目单数量
     */
    private Integer projectNum;

    /**
     * 回归测试数量
     */
    private Integer huiguiNum;

    /**
     * 审核单得分
     */
    private BigDecimal auditScore;

    /**
     * 主线单得分
     */
    private BigDecimal mainScore;

    /**
     * 项目单得分
     */
    private BigDecimal projectScore;

    /**
     * 同步单得分
     */
    private BigDecimal syncScore;

    /**
     * 回归测试得分
     */
    private BigDecimal huiguiScore;


}
