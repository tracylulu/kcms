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
 * 员工职类常量表
 * </p>
 *
 * @author c17740
 * @since 2021-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("post_type_constant")
public class PostTypeConstantDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职类名称1
     */
    private String postName1;

    /**
     * 职级类型，例如：管理－专业、技术类等
     */
    private String postionType;

    /**
     * 任职职称，例如高级开发工程师、高级测试工程师等
     */
    private String postionName;

    /**
     * 职类名称3
     */
    private String postName3;

    /**
     * 职类名称2
     */
    private String postName2;

    /**
     * 职类类型
     */
    private String postType;

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
