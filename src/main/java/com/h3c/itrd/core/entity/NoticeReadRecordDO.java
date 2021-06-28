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
 * notice_read_record 各个员工最新公告阅读记录表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("notice_read_record")
public class NoticeReadRecordDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工工号
     */
    private String employeeId;

    /**
     * 公告id
     */
    private String noticeId;

    /**
     * 公告是否阅读（0未阅读，1已阅读）
     */
    private Integer isRead;

    /**
     * 阅读日期
     */
    private Date readdate;


}
