package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门领导表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dptmanager_list")
public class DptmanagerListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工号
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门码
     */
    private String dptcode;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;

    /**
     * 是否为系统修改：1是 0否
     */
    private String sysModify;

}
