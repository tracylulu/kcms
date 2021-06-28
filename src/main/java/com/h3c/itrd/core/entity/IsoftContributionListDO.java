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
 * 项目人员贡献度
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("isoft_contribution_list")
public class IsoftContributionListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目/特性ID
     */
    @TableField("projectId")
    private String projectId;

    /**
     * 项目各Release号/特性为空
     */
    @TableField("releaseNo")
    private String releaseNo;

    /**
     * 人员
     */
    private String person;

    /**
     * 角色
     */
    private String role;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 贡献度
     */
    private Integer contributevalue;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
