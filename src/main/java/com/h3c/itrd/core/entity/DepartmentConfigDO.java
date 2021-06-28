package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门数据配置表
 * </p>
 *
 * @author c17740
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("department_config")
public class DepartmentConfigDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 需同步的一级部门编码
     */
    @TableId(value = "dept1_code", type = IdType.AUTO)
    private String dept1Code;

    /**
     * 需同步的一级部门名称
     */
    private String dept1Name;

    /**
     * 需同步的二级部门编码,逗号分隔(ALL代表所有)
     */
    private String dept2Code;

    /**
     * 需同步的二级部门名称(ALL代表所有)
     */
    private String dept2Name;
    
    /**
     * 修改时间
     */
    private Date modifyTime;
    
    /**
     * 修改人工号
     */
    private String modifier;


}
