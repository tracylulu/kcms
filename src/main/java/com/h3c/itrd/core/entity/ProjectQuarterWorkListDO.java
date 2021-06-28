package com.h3c.itrd.core.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * project_quarter_work_list当季当量数据表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_quarter_work_list")
public class ProjectQuarterWorkListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称编号
     */
    private String projectId;

    /**
     * 季度
     */
    private String quarterStr;

    /**
     * 当季工作量（当量）
     */
    private BigDecimal proQuarWork;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
