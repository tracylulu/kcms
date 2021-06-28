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
 * project_su_v  v模型的汇算中间表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_su_v")
public class ProjectSuVDO implements Serializable {

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
     * 当季得分
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
     * 当季度工作量
     */
    @TableField("quarterWork")
    private BigDecimal quarterWork;


}
