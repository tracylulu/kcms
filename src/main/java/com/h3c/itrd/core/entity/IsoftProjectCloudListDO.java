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
 * 项目信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("isoft_project_cloud_list")
public class IsoftProjectCloudListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String projectId;

    /**
     * 项目/特性名称
     */
    private String projectName;

    /**
     * 统计月份
     */
    private Date month;

    /**
     * 验收规模
     */
    private String acceptedCode;

    /**
     * 未验收规模
     */
    private String noAcceptedCode;

    /**
     * 进度偏差
     */
    private String progressDeviation;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 贡献度
     */
    private String contribution;

    /**
     * 项目DI密度
     */
    private String diDensity;

    /**
     * 项目URL路径
     */
    private String projectUrl;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
