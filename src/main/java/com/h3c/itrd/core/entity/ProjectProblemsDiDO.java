package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
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
 * project_problems_di 项目总问题单DI汇算表
 * </p>
 *
 * @author cYS1425
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_problems_di")
public class ProjectProblemsDiDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目流水号
     */
    @TableField("ProjectSerialNum")
    private String ProjectSerialNum;

    /**
     * 项目ID
     */
    @TableField("projectId")
    private String projectId;

    /**
     * 项目名称
     */
    @TableField("projectName")
    private String projectName;

    /**
     * 员工工号
     */
    private String account;

    /**
     * 个人提单总DI
     */
    @TableField("SubQuestDI")
    private BigDecimal SubQuestDI;

    /**
     * 个人解单总DI
     */
    @TableField("SolveQuestDI")
    private BigDecimal SolveQuestDI;

    /**
     * 个人验收测试解单总DI
     */
    @TableField("CheckSolveQDI")
    private BigDecimal CheckSolveQDI;

    /**
     * 项目系统测试总DI
     */
    @TableField("ProQuestDI")
    private BigDecimal ProQuestDI;

    /**
     * 项目系统验证测试总DI
     */
    @TableField("ProCheckDI")
    private BigDecimal ProCheckDI;

    /**
     * 汇算日期
     */
    @TableField("countDate")
    private Date countDate;


}
