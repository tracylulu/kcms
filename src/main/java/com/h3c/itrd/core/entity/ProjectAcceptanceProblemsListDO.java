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
 * project_acceptance_problems_list项目验收问题单视图表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_acceptance_problems_list")
public class ProjectAcceptanceProblemsListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目编号id
     */
    private String projectId;

    /**
     * 问题单号
     */
    private String defectNo;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
