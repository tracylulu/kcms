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
 * 员工信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("employee_list")
public class EmployeeListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 员工域账号
     */
    private String domainid;

    /**
     * Notes id信息
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 员工类型，例如正式员工、外协员工、合作员工
     */
    private String type;

    /**
     * 一级部门编码
     */
    private String firstdptcode;

    /**
     * 一级部门
     */
    private String firstdpt;

    /**
     * 二级部门编码
     */
    private String seconddptcode;

    /**
     * 二级部门
     */
    private String seconddpt;

    /**
     * 三级部门编码
     */
    private String thirddptcode;

    /**
     * 三级部门
     */
    private String thirddpt;

    /**
     * 当前部门编码
     */
    private String currentdptcode;

    /**
     * 当前部门，如果有四级部门则体现四级部门，没有则体现三级部门
     */
    private String currentdpt;

    /**
     * 五级部门
     */
    private String fivedpt;

    /**
     * 五级部门编码
     */
    private String fivedptcode;

    /**
     * 工作常驻地
     */
    private String position;

    /**
     * 入职时间
     */
    @TableField("entryDate")
    private Date entryDate;

    /**
     * 职级类型，例如：管理－专业、技术类等
     */
    private String positiontype;

    /**
     * 任职职称，例如高级开发工程师、高级测试工程师等
     */
    private String positionname;

    /**
     * 任职等级，例如T10
     */
    private String positionlevel;

    /**
     * 部门岗位一级
     */
    @TableField("postName1")
    private String postName1;

    /**
     * 部门岗位二级
     */
    @TableField("postName2")
    private String postName2;

    /**
     * 部门岗位三级
     */
    @TableField("postName3")
    private String postName3;

    /**
     * 职类
     */
    @TableField("postType")
    private String postType;

    /**
     * 是否为特殊岗位（1：不是，0：是））
     */
    private Integer special;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;

    /**
     * kcms系统是否修改：1是 0否
     */
    private String sysModify;

    /**
     * 系统的四级部门名称,未修改为currentdptcode值
     */
    private String sysDept4Code;
    
    /**
     * 系统的四级部门名称,未修改为currentdpt值
     */
    private String sysDept4Name;

    /**
     * 系统编辑的城市字段,未修改为position值
     */
    private String sysCity;


}
