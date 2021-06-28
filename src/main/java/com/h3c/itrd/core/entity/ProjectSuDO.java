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
 * 项目数据汇总表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_su")
public class ProjectSuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称
     */
    @TableField("projectName")
    private String projectName;

    /**
     * 项目ID
     */
    @TableField("projectId")
    private String projectId;

    /**
     * 得分
     */
    @TableField("Sa")
    private BigDecimal Sa;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 二级部门编号
     */
    private String seconddptcode;

    /**
     * 项目验收得分
     */
    @TableField("A_su")
    private BigDecimal aSu;

    /**
     * 当季度实际工作天数
     */
    private Integer daysD;


}
