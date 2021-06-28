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
 * 规范表
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("specinfo_list")
public class SpecinfoListDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规范编号
     */
    private String gdwjbh;

    /**
     * 规范名称
     */
    private String gfmc;

    /**
     * 规范作者
     */
    private String wfAddname;

    /**
     * 创建时间
     */
    private Date wfDoccreated;

    /**
     * 规范分类
     */
    private String gffl;

    /**
     * 归档选择
     */
    private String gdxz;

    /**
     * 规范级别
     */
    private String gfjb;

    /**
     * 主类别
     */
    private String zlb1;

    /**
     * 子类别
     */
    private String zlb2;

    /**
     * 发布时间
     */
    private Date fbtime;

    /**
     * 实施时间
     */
    private Date sstime;

    /**
     * 规范主要制定人员
     */
    private String gfzyry;

    /**
     * 关键词
     */
    private String gjc;

    /**
     * 发放部门
     */
    private String dept1;

    /**
     * 当前状态
     */
    private String sffz;

    /**
     * 规范标准升级补充、更改内容说明
     */
    private String gfbzsj;

    /**
     * 同步状态标识
     */
    private String syncStatus;

    /**
     * 同步时间标识
     */
    private Date syncTime;


}
