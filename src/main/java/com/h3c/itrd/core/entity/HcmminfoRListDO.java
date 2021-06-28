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
 * HCMM-R板信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hcmminfo_r_list")
public class HcmminfoRListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流水号
     */
    @TableField("FlowSerialNo")
    private String FlowSerialNo;

    /**
     * 流程状态
     */
    @TableField("FlowStatusName")
    private String FlowStatusName;

    /**
     * 印制板板名
     */
    @TableField("PrintName")
    private String PrintName;

    /**
     * 印制板版本
     */
    @TableField("PrintVersion")
    private String PrintVersion;

    /**
     * 产品线
     */
    @TableField("ProductionLineName")
    private String ProductionLineName;

    /**
     * PDT
     */
    @TableField("PDTName")
    private String PDTName;

    /**
     * 路标版本
     */
    @TableField("ReleaseName")
    private String ReleaseName;

    /**
     * B版本
     */
    @TableField("BVersion")
    private String BVersion;

    /**
     * 检查人签名
     */
    private String planPlaCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("plan_pla_check01Date")
    private Date planPlaCheck01date;

    /**
     * 单板规模估计
     */
    private BigDecimal planPlaExt01;

    /**
     * 单板等效新增规模估计
     */
    private BigDecimal planPlaExt02;

    /**
     * 改板方案评审修改、基线化缺陷数
     */
    private Integer defSumPlaA03;

    /**
     * 收集改板需求 计划开始时间
     */
    @TableField("plan_pla_a01_Begin")
    private Date planPlaA01Begin;

    /**
     * 收集改板需求 实际开始时间
     */
    @TableField("pla_a01_Begin")
    private Date plaA01Begin;

    /**
     * 改板方案评审修改、基线化 计划开始时间
     */
    @TableField("plan_pla_a03_Begin")
    private Date planPlaA03Begin;

    /**
     * 改板方案评审修改、基线化 计划结束时间
     */
    @TableField("plan_pla_a03_End")
    private Date planPlaA03End;

    /**
     * 改板方案评审修改、基线化 实际开始时间
     */
    @TableField("pla_a03_Begin")
    private Date plaA03Begin;

    /**
     * 改板方案评审修改、基线化 实际结束时间
     */
    @TableField("pla_a03_End")
    private Date plaA03End;

    /**
     * 详细设计/原理图/规则驱动表单 更新 计划开始时间
     */
    @TableField("plan_des_a01_Begin")
    private Date planDesA01Begin;

    /**
     * 详细设计/原理图/规则驱动表单 更新 计划结束时间
     */
    @TableField("plan_des_a01_End")
    private Date planDesA01End;

    /**
     * PCB布局、布线（含评审） 计划开始时间
     */
    @TableField("plan_des_a05_Begin")
    private Date planDesA05Begin;

    /**
     * PCB布局、布线（含评审） 计划结束时间
     */
    @TableField("plan_des_a05_End")
    private Date planDesA05End;

    /**
     * 调试及单元测试执行 计划开始时间
     */
    @TableField("plan_tes_a04_Begin")
    private Date planTesA04Begin;

    /**
     * 调试及单元测试执行 计划结束时间
     */
    @TableField("plan_tes_a04_End")
    private Date planTesA04End;

    /**
     * 硬件系统测试执行 计划开始时间
     */
    @TableField("plan_tes_a05_Begin")
    private Date planTesA05Begin;

    /**
     * 硬件系统测试执行 计划结束时间
     */
    @TableField("plan_tes_a05_End")
    private Date planTesA05End;

    /**
     * 检查人签名
     */
    private String designDesCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("design_des_check01Date")
    private Date designDesCheck01date;

    /**
     * 单板规模重估计
     */
    private BigDecimal desExt01;

    /**
     * 单板等效新增规模重估计
     */
    private BigDecimal desExt02;

    /**
     * PCB布局、布线（含评审）缺陷数
     */
    private Integer defSumDesA05;

    /**
     * 详细设计/原理图/规则驱动表单 更新 实际开始时间
     */
    @TableField("des_a01_Begin")
    private Date desA01Begin;

    /**
     * 详细设计/原理图/规则驱动表单 更新 实际结束时间
     */
    @TableField("des_a01_End")
    private Date desA01End;

    /**
     * PCB布局、布线（含评审） 实际开始时间
     */
    @TableField("des_a05_Begin")
    private Date desA05Begin;

    /**
     * PCB布局、布线（含评审） 实际结束时间
     */
    @TableField("des_a05_End")
    private Date desA05End;

    /**
     * 检查人签名
     */
    private String testTesCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("test_tes_check01Date")
    private Date testTesCheck01date;

    /**
     * 调试及单元测试执行 实际开始时间
     */
    @TableField("tes_a04_Begin")
    private Date tesA04Begin;

    /**
     * 调试及单元测试执行 实际结束时间
     */
    @TableField("tes_a04_End")
    private Date tesA04End;

    /**
     * 硬件系统测试执行 实际开始时间
     */
    @TableField("tes_a05_Begin")
    private Date tesA05Begin;

    /**
     * 硬件系统测试执行 实际结束时间
     */
    @TableField("tes_a05_End")
    private Date tesA05End;

    /**
     * 调试及单元测试执行缺陷数
     */
    private Integer defSumTesA04;

    /**
     * 硬件系统测试执行缺陷数
     */
    private Integer defSumTesA05;

    /**
     * 专业试验执行缺陷数
     */
    private Integer defSumTesA06;

    /**
     * 改板方案拟制输出初稿 计划开始时间
     */
    @TableField("plan_pla_a02_Begin")
    private Date planPlaA02Begin;

    /**
     * 改板方案拟制输出初稿 计划结束时间
     */
    @TableField("plan_pla_a02_End")
    private Date planPlaA02End;

    /**
     * 改板方案拟制输出初稿 实际开始时间
     */
    @TableField("pla_a02_Begin")
    private Date plaA02Begin;

    /**
     * 改板方案拟制输出初稿 实际结束时间
     */
    @TableField("pla_a02_End")
    private Date plaA02End;

    /**
     * 预估投板次数
     */
    private Integer desExt10;

    /**
     * 单板重用率
     */
    private String desExt11;

    /**
     * 调试及单元测试执行-测试覆盖率
     */
    private BigDecimal tesExt04;

    /**
     * 调试及单元测试执行-测试人员
     */
    @TableField("unittest_Tester")
    private String unittestTester;

    /**
     * 调试及单元测试执行-测试比例
     */
    @TableField("unittest_TestProportion")
    private BigDecimal unittestTestproportion;

    /**
     * 硬件系统测试执行-测试用例总数
     */
    private Integer tesExt05;

    /**
     * 硬件系统测试执行-测试人员
     */
    @TableField("hardwaretest_Tester")
    private String hardwaretestTester;

    /**
     * 硬件系统测试执行-测试比例
     */
    @TableField("hardwaretest_TestProportion")
    private BigDecimal hardwaretestTestproportion;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
