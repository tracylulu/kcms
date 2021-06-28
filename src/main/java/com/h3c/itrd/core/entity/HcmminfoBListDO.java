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
 * HCMM-B板信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hcmminfo_b_list")
public class HcmminfoBListDO implements Serializable {

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
     * 单板项目分类
     */
    @TableField("BoardType")
    private String BoardType;

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
     * 需求跟踪表拟制输出初稿 计划开始时间
     */
    @TableField("plan_req_a01_Begin")
    private Date planReqA01Begin;

    /**
     * 详细设计报告（含逻辑）评审修改、基线化 计划结束时间
     */
    @TableField("plan_det_a08_End")
    private Date planDetA08End;

    /**
     * 原理图/规则驱动表单评审修改、基线化 计划结束时间
     */
    @TableField("plan_det_a02_End")
    private Date planDetA02End;

    /**
     * PCB布局（含布局评审）计划开始时间
     */
    @TableField("plan_pcb_a01_Begin")
    private Date planPcbA01Begin;

    /**
     * PCB布局（含布局评审）计划结束时间
     */
    @TableField("plan_pcb_a01_End")
    private Date planPcbA01End;

    /**
     * PCB布线（含布线评审）计划开始时间
     */
    @TableField("plan_pcb_a02_Begin")
    private Date planPcbA02Begin;

    /**
     * PCB布线（含布线评审）计划结束时间
     */
    @TableField("plan_pcb_a02_End")
    private Date planPcbA02End;

    /**
     * 调试及单元测试执行 计划开始时间
     */
    @TableField("plan_uni_a04_Begin")
    private Date planUniA04Begin;

    /**
     * 调试及单元测试执行 计划结束时间
     */
    @TableField("plan_uni_a04_End")
    private Date planUniA04End;

    /**
     * 硬件测试执行 计划开始时间
     */
    @TableField("plan_har_a01_Begin")
    private Date planHarA01Begin;

    /**
     * 硬件测试执行 计划结束时间
     */
    @TableField("plan_har_a01_End")
    private Date planHarA01End;

    /**
     * 检查人签名
     */
    private String requirementReqCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("requirement_req_check01Date")
    private Date requirementReqCheck01date;

    /**
     * 需求跟踪表拟制输出初稿 实际开始时间
     */
    @TableField("requirement_req_a01_Begin")
    private Date requirementReqA01Begin;

    /**
     * 检查人签名
     */
    private String generaldesignGenCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("generaldesign_gen_check01Date")
    private Date generaldesignGenCheck01date;

    /**
     * 单板规模重估计
     */
    private BigDecimal genExt01;

    /**
     * 单板等效新增规模重估计
     */
    private BigDecimal genExt02;

    /**
     * 检查人签名
     */
    private String detaileddesignDetCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("detaileddesign_det_check01Date")
    private Date detaileddesignDetCheck01date;

    /**
     * 单板规模
     */
    private BigDecimal detExt05;

    /**
     * 复用率指标
     */
    private BigDecimal detExt07;

    /**
     * 详细设计缺陷数
     */
    private Integer defSumDetA08;

    /**
     * 原理图缺陷数
     */
    private Integer defSumDetA02;

    /**
     * 详细设计报告（含逻辑）评审修改、基线化 实际结束时间
     */
    @TableField("detaileddesign_det_a08_End")
    private Date detaileddesignDetA08End;

    /**
     * 原理图/规则驱动表单评审修改、基线化 实际结束时间
     */
    @TableField("detaileddesign_det_a02_End")
    private Date detaileddesignDetA02End;

    /**
     * 检查人签名
     */
    private String pcbPcbCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("pcb_pcb_check01Date")
    private Date pcbPcbCheck01date;

    /**
     * 实际单板规模
     */
    private BigDecimal pcbExt01;

    /**
     * 实际单板等效新增规模
     */
    private BigDecimal pcbExt02;

    /**
     * 预估投板次数
     */
    @TableField("PCBoardFrequency")
    private String PCBoardFrequency;

    /**
     * PCB布局（含布局评审）实际开始时间
     */
    @TableField("pcb_pcb_a01_Begin")
    private Date pcbPcbA01Begin;

    /**
     * PCB布线（含布线评审）实际结束时间
     */
    @TableField("pcb_pcb_a02_End")
    private Date pcbPcbA02End;

    /**
     * 检查人签名
     */
    private String unittestUniCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("unittest_uni_check01Date")
    private Date unittestUniCheck01date;

    /**
     * 调试及单元测试执行 实际开始时间
     */
    @TableField("unittest_uni_a04_Begin")
    private Date unittestUniA04Begin;

    /**
     * 调试及单元测试执行 实际结束时间
     */
    @TableField("unittest_uni_a04_End")
    private Date unittestUniA04End;

    /**
     * 测试覆盖率(%)
     */
    @TableField("unittest_TestCoverage")
    private String unittestTestcoverage;

    /**
     * 单板调试及单元测试-测试人员
     */
    @TableField("unittest_Tester")
    private String unittestTester;

    /**
     * 单板调试及单元测试-测试比例
     */
    @TableField("unittest_TestProportion")
    private String unittestTestproportion;

    /**
     * 检查人签名
     */
    private String hardwaretestHarCheck01;

    /**
     * 检查人签名日期
     */
    @TableField("hardwaretest_har_check01Date")
    private Date hardwaretestHarCheck01date;

    /**
     * 硬件测试执行 实际开始时间
     */
    @TableField("hardwaretest_har_a01_Begin")
    private Date hardwaretestHarA01Begin;

    /**
     * 硬件测试执行 实际结束时间
     */
    @TableField("hardwaretest_har_a01_End")
    private Date hardwaretestHarA01End;

    /**
     * 测试用例总数
     */
    @TableField("hardwaretest_TestCasesSum")
    private Integer hardwaretestTestcasessum;

    /**
     * 单板硬件测试-测试人员
     */
    @TableField("hardwaretest_Tester")
    private String hardwaretestTester;

    /**
     * 单板硬件测试-测试比例
     */
    @TableField("hardwaretest_TestProportion")
    private String hardwaretestTestproportion;

    /**
     * pcb-设计审查责任人
     */
    @TableField("pcbDRR_Tester")
    private String pcbdrrTester;

    /**
     * pcb-设计审查分摊比例
     */
    @TableField("pcbDRR_TestProportion")
    private BigDecimal pcbdrrTestproportion;

    /**
     * 详细设计报告（含逻辑）拟制输出初稿 计划开始时间
     */
    @TableField("plan_det_a07_Begin")
    private Date planDetA07Begin;

    /**
     * 原理图/规则驱动表单拟制输出初稿 计划开始时间
     */
    @TableField("plan_det_a01_Begin")
    private Date planDetA01Begin;

    /**
     * 详细设计报告（含逻辑）拟制输出初稿 实际开始时间
     */
    @TableField("detaileddesign_det_a07_Begin")
    private Date detaileddesignDetA07Begin;

    /**
     * 原理图/规则驱动表单拟制输出初稿 实际开始时间
     */
    @TableField("detaileddesign_det_a01_Begin")
    private Date detaileddesignDetA01Begin;

    /**
     * 原理图/规则驱动表单评审修改、基线化 责任人
     */
    @TableField("detaileddesign_det_a02_Responsible")
    private String detaileddesignDetA02Responsible;

    /**
     * PCB布线（含布线评审） 实际开始时间
     */
    @TableField("pcb_pcb_a02_Begin")
    private Date pcbPcbA02Begin;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
