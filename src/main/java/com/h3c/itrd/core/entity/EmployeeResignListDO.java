package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 离职员工信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("employee_resign_list")
public class EmployeeResignListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工工号
     */
    private String id;

    /**
     * 离职时间
     */
    private Date leavedate;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
