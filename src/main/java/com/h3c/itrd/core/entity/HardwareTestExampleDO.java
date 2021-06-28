package com.h3c.itrd.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hardware_test_example")
public class HardwareTestExampleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String exampleName;

    private String quarter;


}
