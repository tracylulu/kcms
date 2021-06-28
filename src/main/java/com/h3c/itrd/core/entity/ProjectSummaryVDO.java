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
 * project_summary_V  v模型的汇算中间表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_summary_v")
public class ProjectSummaryVDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 季度
     */
    private String quarter;

    /**
     * 人员
     */
    private String account;

    /**
     * 项目截至当日的总工作量
     */
    @TableField("ProTotalWorkd")
    private BigDecimal ProTotalWorkd;

    /**
     * 项目截至当日的得分Bx
     */
    @TableField("ProScored")
    private BigDecimal ProScored;

    /**
     * 项目截至当日的员工的贡献度百分比Rx
     */
    @TableField("ContributeValued")
    private BigDecimal ContributeValued;

    /**
     * 个人Cx截至当日实际总得分
     */
    @TableField("TotalScored")
    private BigDecimal TotalScored;

    /**
     * 个人当季贡献度百分比
     */
    @TableField("ContributeValue")
    private BigDecimal ContributeValue;

    /**
     * 员工Cx当季总得分Sdx
     */
    @TableField("Sa_count")
    private BigDecimal saCount;

    /**
     * 个人当季工作量（当量）
     */
    @TableField("ProQuarWork")
    private BigDecimal ProQuarWork;

    /**
     * 汇算日期
     */
    @TableField("countDate")
    private String countDate;


}
