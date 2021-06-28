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
 * 二级部门季度平均得分表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dptaveragescore_list")
public class DptaveragescoreListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 二级部门编码
     */
    private String dptcode;

    /**
     * 考核季度
     */
    private String quarter;

    /**
     * 验收平均得分
     */
    @TableField("ATaveragescore")
    private BigDecimal ATaveragescore;

    /**
     * 代码鉴定平均得分
     */
    @TableField("Auditaveragescore")
    private BigDecimal Auditaveragescore;


}
