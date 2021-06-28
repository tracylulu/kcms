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
 * 软件测试算法参数配置表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dptconfig_softtest_list")
public class DptconfigSofttestListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 二级部门编号
     */
    private String dptcode;

    /**
     * 测试执行得分提单数量得分系数
     */
    private BigDecimal idmAudit1;

    /**
     * 测试执行得分DI得分系数
     */
    private BigDecimal idmDi1;

    /**
     * 项目单提单数量得分系数
     */
    private BigDecimal idmAudit2;

    /**
     * 项目单DI得分得分系数
     */
    private BigDecimal idmDi2;

    /**
     * 总审核单得分系数
     */
    private BigDecimal idmNum1;

    /**
     * 同步单提单得分系数
     */
    private BigDecimal idmNum2;

    /**
     * 测试点得分系数
     */
    private BigDecimal pointNum;

    /**
     * 测试用例得分系数
     */
    private BigDecimal exampleNum;

    /**
     * 测试脚本得分
     */
    private BigDecimal scriptNum;

    private String bugSyncprefix;

    private BigDecimal idmHuigui;

    private String configtype;


}
