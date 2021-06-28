package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 项目信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("isoft_project_new_list")
public class IsoftProjectNewListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目/特性ID
     */
    @TableField("projectId")
    private String projectId;

    /**
     * 项目/特性名称
     */
    @TableField("projectName")
    private String projectName;

    /**
     * 项目/特性类型，0－项目，1－特性
     */
    @TableField("proType")
    private String proType;

    /**
     * 开发负责人对应的二级部门编码
     */
    private String seconddptcode;

    /**
     * 开发负责人对应的当前部门编码
     */
    private String currentdptcode;

    /**
     * 项目/特性状态
     */
    private String state;

    /**
     * PCB类型
     */
    @TableField("PCBType")
    private String PCBType;

    /**
     * 项目负责人
     */
    private String pm;

    /**
     * 设计负责人
     */
    private String tl;

    /**
     * 评审责任人
     */
    @TableField("Review")
    private String Review;

    /**
     * 项目成员
     */
    @TableField("Members")
    private String Members;

    /**
     * 估计规模
     */
    @TableField("PlanScale")
    private BigDecimal PlanScale;

    /**
     * 实际规模
     */
    @TableField("ActualScale")
    private BigDecimal ActualScale;

    /**
     * 计划人时
     */
    @TableField("PlanManHours")
    private BigDecimal PlanManHours;

    /**
     * 实际人时
     */
    @TableField("FactManHours")
    private BigDecimal FactManHours;

    /**
     * 计划生产率
     */
    @TableField("PlanProRate")
    private BigDecimal PlanProRate;

    /**
     * 实际生产率
     */
    @TableField("FactProRate")
    private BigDecimal FactProRate;

    /**
     * 计划开始时间
     */
    @TableField("PlanStartTime")
    private Date PlanStartTime;

    /**
     * 实际开始时间
     */
    @TableField("StartTime")
    private Date StartTime;

    /**
     * 计划验收时间
     */
    @TableField("PlanCheckEndTime")
    private Date PlanCheckEndTime;

    /**
     * 实际验收时间
     */
    @TableField("CheckEndTime")
    private Date CheckEndTime;

    /**
     * 计划结束时间
     */
    @TableField("PlanEndTime")
    private Date PlanEndTime;

    /**
     * 实际结束时间
     */
    @TableField("EndTime")
    private Date EndTime;

    /**
     * 第一次暂停时间
     */
    @TableField("FirstPauseTime")
    private Date FirstPauseTime;

    /**
     * 第一次恢复时间
     */
    @TableField("FirstRecoverTime")
    private Date FirstRecoverTime;

    /**
     * 第二次暂停时间
     */
    @TableField("SecondPauseTime")
    private Date SecondPauseTime;

    /**
     * 第二次恢复时间
     */
    @TableField("SecondRecoverTime")
    private Date SecondRecoverTime;

    /**
     * 验收说明，免验收、验收合入、特批合入等
     */
    @TableField("ATDescription")
    private String ATDescription;

    /**
     * 验收评价
     */
    @TableField("ATEvaluation")
    private String ATEvaluation;

    /**
     * 验收DI
     */
    private String atdi;

    /**
     * 总体DI
     */
    @TableField("TotalDI")
    private String TotalDI;

    /**
     * ST进度偏差
     */
    @TableField("ScheduleForST")
    private String ScheduleForST;

    /**
     * 整体进度偏差
     */
    @TableField("ScheduleForAll")
    private String ScheduleForAll;

    /**
     * 扩展1字段
     */
    @TableField("Ext1")
    private String Ext1;

    /**
     * 特性扩展2，参与人贡献度
     */
    @TableField("ContributeValue")
    private String ContributeValue;

    /**
     * 难度系数
     */
    @TableField("Difficult")
    private BigDecimal Difficult;

    /**
     * isoft的url链接
     */
    @TableField("Url")
    private String Url;

    /**
     * 代码鉴定评价
     */
    @TableField("CAEvaluation")
    private String CAEvaluation;

    @TableField("AuditScore")
    private BigDecimal AuditScore;

    /**
     * 开发组
     */
    @TableField("ResGroupName")
    private String ResGroupName;

    /**
     * 开发小组
     */
    @TableField("FourthOrg")
    private String FourthOrg;

    /**
     * 估计新增规模
     */
    @TableField("PlanNewScale")
    private BigDecimal PlanNewScale;

    /**
     * 估计移植规模
     */
    @TableField("PlanMoveScale")
    private BigDecimal PlanMoveScale;

    /**
     * 实际新增规模
     */
    @TableField("FactNewScale")
    private BigDecimal FactNewScale;

    /**
     * 实际移植规模
     */
    @TableField("FactMoveScale")
    private BigDecimal FactMoveScale;

    /**
     * 代码鉴定ID
     */
    @TableField("AppraiseID")
    private String AppraiseID;

    @TableField("AppraiseEva")
    private String AppraiseEva;

    /**
     * 代码鉴定评价
     */
    @TableField("AppraisalProjectDI")
    private String AppraisalProjectDI;

    /**
     * 代码鉴定DI值
     */
    @TableField("AppraiseDI")
    private BigDecimal AppraiseDI;

    /**
     * TC
     */
    private String tc;


}
