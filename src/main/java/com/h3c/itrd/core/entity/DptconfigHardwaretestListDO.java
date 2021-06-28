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
 * 硬件测试算法参数表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dptconfig_hardwaretest_list")
public class DptconfigHardwaretestListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 二级部门名称
     */
    private String dptname;

    /**
     * 二级部门编号
     */
    private String dptcode;

    /**
     * 维护得分提单系数(严重)
     */
    private BigDecimal idmSubmitA;

    /**
     * 提单定位得分系数(严重)
     */
    private BigDecimal idmLocationA;

    /**
     * 提单修改实施得分系数(严重)
     */
    private BigDecimal idmEditA;

    /**
     * 提单得分问题不通过系数
     */
    private BigDecimal idmNotpass;

    /**
     * 维护得分提单系数(普通)
     */
    private BigDecimal idmSubmitB;

    /**
     * 维护得分提单系数（提示）
     */
    private BigDecimal idmSubmitC;

    /**
     * 提单定位得分系数(普通)
     */
    private BigDecimal idmLocationB;

    /**
     * 提单定位得分系数（提示）
     */
    private BigDecimal idmLocationC;

    /**
     * 提单修改实施得分系数(普通)
     */
    private BigDecimal idmEditB;

    /**
     * 提单修改实施得分系数(提示)
     */
    private BigDecimal idmEditC;

    /**
     * hcmm详细设计得分系数
     */
    private BigDecimal hcmmCd;

    /**
     * hcmmUT得分系数
     */
    private BigDecimal hcmmCu;

    /**
     * 待分析件得分基数
     */
    private BigDecimal analyzeBasic;

    /**
     * 待分析件难度系数A
     */
    @TableField("analyze_A")
    private BigDecimal analyzeA;

    /**
     * 待分析件得分系数B
     */
    @TableField("analyze_B")
    private BigDecimal analyzeB;

    /**
     * 待分析件得分系数C
     */
    @TableField("analyze_C")
    private BigDecimal analyzeC;

    /**
     * 待分析件得分系数D
     */
    @TableField("analyze_D")
    private BigDecimal analyzeD;

    /**
     * 生产质量问题得分基数
     */
    private BigDecimal qmsBasic;

    /**
     * 重大质量问题得分系数
     */
    private BigDecimal qmsVaryhard;

    /**
     * 重要质量问题得分系数
     */
    private BigDecimal qmsHard;

    /**
     * 一般质量问题得分系数
     */
    private BigDecimal qmsNormal;

    /**
     * 提示质量问题得分系数
     */
    private BigDecimal qmsTip;

    /**
     * 评审基数
     */
    private BigDecimal reviewBasic;


}
