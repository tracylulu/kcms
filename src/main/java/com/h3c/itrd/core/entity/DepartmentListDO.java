package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门列表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("department_list")
public class DepartmentListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门编码
     */
    private String dptcode;

    /**
     * 部门名称
     */
    private String dptname;

    /**
     * 上级部门编码
     */
    private String subdptcode;

    /**
     * 部门层级
     */
    private Integer level;

    /**
     * 标记该部门是否是新增/删除的。0－删除，1－存在
     */
    private Integer remark;

    /**
     * 是否使用
     */
    private Integer used;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;

    /**
     * kcms系统是否新增：1是 0否
     */
    private String sysModify;


}
