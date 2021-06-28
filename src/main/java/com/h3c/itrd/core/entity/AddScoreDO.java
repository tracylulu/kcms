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
 * 关键事件得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("add_score")
public class AddScoreDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工工号
     */
    private String userId;

    /**
     * 得分
     */
    private BigDecimal score;

    /**
     * 姓名
     */
    private String name;

    /**
     * 二级部门工号
     */
    private String dptcode;

    /**
     * 加减分项目
     */
    private String project;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核时间
     */
    private String auditTime;

    /**
     * 审核编号
     */
    private Integer auditId;

    /**
     * 数据导入时间
     */
    private String insertTime;

    /**
     * 操作人id
     */
    private Integer insertUserId;

    /**
     * 审核简述
     */
    private String detail;


}
