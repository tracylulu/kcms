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
 * PDM信息表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pdminfo_list")
public class PdminfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 归档类型
     */
    @TableField("archiveType")
    private String archiveType;

    /**
     * 产品线
     */
    private String h3productline;

    /**
     * 产品
     */
    private String h3product;

    /**
     * 产品系列
     */
    private String projectname;

    /**
     * 产品PDT
     */
    private String pdt;

    /**
     * 归档时间
     */
    private Date actualreldate;

    /**
     * 产品名
     */
    private String projectname1;

    /**
     * TR点
     */
    private String s1passedtr;

    /**
     * 归档版本
     */
    private String h3phyfilerev;

    /**
     * 成品板或制成板编码与描述
     */
    @TableField("boardNumAndDesc")
    private String boardNumAndDesc;

    /**
     * 印制板编码
     */
    private String h3printedcircuitnum;

    /**
     * 印制板编码与描述
     */
    private String h3printedcircuitdesc;

    /**
     * 印制板编码状态
     */
    private String s1partstatus;

    /**
     * 对内型号
     */
    private String s1internalmodel;

    /**
     * PCB层数
     */
    private Integer h3pcblevelnum;

    /**
     * 已归档版本
     */
    @TableField("archiveVersion")
    private String archiveVersion;

    /**
     * 责任人
     */
    private String respparty;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
