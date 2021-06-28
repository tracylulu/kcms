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
 * 关键事件选项表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("add_score_select")
public class AddScoreSelectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 满分
     */
    private BigDecimal score;

    /**
     * “好”得分比例
     */
    private BigDecimal a;

    /**
     * 良好得分比例
     */
    private BigDecimal b;

    /**
     * 一般得分比例
     */
    private BigDecimal c;

    /**
     * 适用二级部门编号
     */
    private String seconddptcode;

    /**
     * 适用三级部门编号
     */
    private String thirddptcode;

    /**
     * 是否可用：1:可用 2：删除
     */
    private Integer status;

    /**
     * 三级部门
     */
    private String thirddpt;

    /**
     * 二级部门
     */
    private String seconddpt;


}
