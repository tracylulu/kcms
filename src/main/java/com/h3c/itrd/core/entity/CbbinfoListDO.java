package com.h3c.itrd.core.entity;

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
 * CBB视图信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cbbinfo_list")
public class CbbinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文档编号
     */
    private String itemId;

    /**
     * 文档小类
     */
    @TableField("nh3c_DocumentSubType")
    private String nh3cDocumentsubtype;

    /**
     * CBB名称
     */
    @TableField("nh3c_DocumentDescription")
    private String nh3cDocumentdescription;

    /**
     * 核心器件
     */
    @TableField("nh3c_KeyComp")
    private String nh3cKeycomp;

    /**
     * 升级归档原因
     */
    @TableField("nh3c_ReasonArchAgain")
    private String nh3cReasonarchagain;

    /**
     * CBB等级
     */
    @TableField("nh3c_CbbLevel")
    private String nh3cCbblevel;

    /**
     * 技术分类
     */
    @TableField("nh3c_TechClass")
    private String nh3cTechclass;

    /**
     * 发布日期
     */
    @TableField("ActualRelDate")
    private Date ActualRelDate;

    /**
     * CBB作者
     */
    @TableField("nh3c_CbbAuthor")
    private String nh3cCbbauthor;

    /**
     * 一级部门
     */
    @TableField("H3FirstLevelDept")
    private String H3FirstLevelDept;

    /**
     * 二级部门
     */
    @TableField("H3SecondLevelDept")
    private String H3SecondLevelDept;

    /**
     * 三级部门
     */
    @TableField("H3ThirdLevelDept")
    private String H3ThirdLevelDept;

    /**
     * 责任人
     */
    @TableField("RespParty")
    private String RespParty;

    /**
     * 生命周期状态
     */
    @TableField("LifeCycleState")
    private String LifeCycleState;

    /**
     * 是否新归档
     */
    private String revision;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
