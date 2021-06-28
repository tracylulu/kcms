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
 * 系统问题反馈表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("feedback")
public class FeedbackDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工ID
     */
    private String userId;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 问题状态：1.已解决 0.待解决 2.非问题 3.转需求
     */
    private Integer status;

    /**
     * 反馈时间
     */
    private Date time;


}
