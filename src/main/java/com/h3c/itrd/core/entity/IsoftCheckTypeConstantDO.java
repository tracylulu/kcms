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
 * isoft_check状态常量表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("isoft_check_type_constant")
public class IsoftCheckTypeConstantDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String proType;

    private String proTypeCn;

    private String proState;

    private String proStateCn;

    /**
     * 删除标识，0无效 1有效
     */
    private Integer deleteFlag;

    /**
     * 创建人工号
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人工号
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifiTime;


}
