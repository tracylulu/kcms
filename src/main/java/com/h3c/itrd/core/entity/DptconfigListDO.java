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
 * 软件开发算法参数配置表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dptconfig_list")
public class DptconfigListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 二级部门编码
     */
    private String dptcode;

    /**
     * 配置类型，例如：软件开发、软件测试等
     */
    private String configtype;

    /**
     * 无效问题单简述字段过滤内容
     */
    private String bugValidprefix;

    /**
     * 非无效问题单的筛选条件
     */
    private String bugInvalidprefix;

    /**
     * 无效问题单基线子版本过滤条件
     */
    private String bugValidbaseline;

    /**
     * 同步问题单简述字段过滤内容
     */
    private String bugSyncprefix;

    /**
     * 非同步问题单筛选条件
     */
    private String bugAsyncprefix;

    /**
     * 普通单提单数量得分系数
     */
    @TableField("bug_normal_SubmitCNTprefix")
    private BigDecimal bugNormalSubmitcntprefix;

    /**
     * 普通单提单DI得分系数
     */
    @TableField("bug_normal_SubmitDIprefix")
    private BigDecimal bugNormalSubmitdiprefix;

    /**
     * 普通单定位数量得分系数
     */
    @TableField("bug_normal_LocationCNTprefix")
    private BigDecimal bugNormalLocationcntprefix;

    /**
     * 普通单定位DI得分系数
     */
    @TableField("bug_normal_LocationDIprefix")
    private BigDecimal bugNormalLocationdiprefix;

    /**
     * 普通单修改数量得分系数
     */
    @TableField("bug_normal_ModifyCNTprefix")
    private BigDecimal bugNormalModifycntprefix;

    /**
     * 普通单审核数量得分系数
     */
    @TableField("bug_normal_ReviewCNTprefix")
    private BigDecimal bugNormalReviewcntprefix;

    /**
     * 同步单修改数量得分系数
     */
    @TableField("bug_sync_ModifyCNTprefix")
    private BigDecimal bugSyncModifycntprefix;

    /**
     * 同步单审核数量得分系数
     */
    @TableField("bug_sync_ReviewCNTprefix")
    private BigDecimal bugSyncReviewcntprefix;

    /**
     * 评审DI得分系数
     */
    @TableField("review_DIprefix")
    private BigDecimal reviewDiprefix;

    /**
     * 评审数量得分系数
     */
    @TableField("review_CNTprefix")
    private BigDecimal reviewCntprefix;

    /**
     * 项目得分的基础规模
     */
    private BigDecimal prjBasecode;

    /**
     * 开发规模得分倍数系数
     */
    private BigDecimal prjDevscaleprefix1;

    /**
     * 开发规模得分幂系数
     */
    private BigDecimal prjDevscaleprefix2;

    /**
     * 验收得分系数
     */
    @TableField("prj_ATprefix")
    private BigDecimal prjAtprefix;

    /**
     * 季度平均验收得分
     */
    @TableField("prj_ATaverageQuarter")
    private BigDecimal prjAtaveragequarter;

    /**
     * 进度得分系数
     */
    @TableField("prj_Scheduleprefix")
    private BigDecimal prjScheduleprefix;

    /**
     * 代码鉴定得分系数
     */
    @TableField("prj_CodeAuthprefix")
    private BigDecimal prjCodeauthprefix;

    /**
     * 项目审计得分系数
     */
    @TableField("prj_Auditprefix")
    private BigDecimal prjAuditprefix;

    /**
     * 项目工作量系数
     */
    private BigDecimal vProWork;

    /**
     * 系统测试DI系数A
     */
    @TableField("v_systestDI_a")
    private BigDecimal vSystestdiA;

    /**
     * 员工ST提单DI系数
     */
    @TableField("v_subQuestDI")
    private BigDecimal vSubquestdi;

    /**
     * 员工ST解单DI系数
     */
    @TableField("v_solveQuestDI")
    private BigDecimal vSolvequestdi;

    /**
     * 验收测试解单DI系数B
     */
    @TableField("v_checktestDI_b")
    private BigDecimal vChecktestdiB;
    
    /**
     * 验证得分公式幂方中的系数
     */
    @TableField("prj_ATDIprefix")
    private BigDecimal prjAtdiprefix;
}
