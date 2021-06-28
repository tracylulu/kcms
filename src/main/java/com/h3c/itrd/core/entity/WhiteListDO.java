package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 白名单
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("white_list")
public class WhiteListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


}
