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
 * 专利信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("patentinfo_list")
public class PatentinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专利编号
     */
    @TableField("patentNo")
    private String patentNo;

    /**
     * 申请日期
     */
    @TableField("applyDate")
    private Date applyDate;

    /**
     * 专利名称
     */
    @TableField("patentName")
    private String patentName;

    /**
     * 专利类型
     */
    @TableField("patentType")
    private String patentType;

    /**
     * 三级部门
     */
    private String thirddptname;

    /**
     * 所属业务部门
     */
    private String dptname;

    /**
     * 发明人姓名
     */
    private String inventorname;

    /**
     * 发明人工号
     */
    private String inventorid;

    /**
     * 发明人占比
     */
    private BigDecimal weight;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
