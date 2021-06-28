package com.h3c.itrd.common.consts;

/**
 * @author s17742
 *
 */
public enum StatusCodeEnum {
    // 204** 未系统代码，201** 预留，202** 通用编号，203** log专用编码，205** face专用编码，206** rpa专用编码，207** esb专用编码，一般 偶数是肯定，奇数是否定
    NORMAL(20000, "成功"),
    TEST_EXCEP_ERROR(20401, "测试异常"),
    UNKNOW_ERROR(20402, "未知错误"),
    FUSE_ERROR(20403, "熔断器触发保护"),
    EXCEP_ERROR(20404, "有异常数据"),

    USERID_NOT_FIND(20201, "userId不能为空"),
    AUTH_NOT_FIND(20202, "Authorization不能为空"),
    ERROR_FORMAT(202021, "Authorization格式不对"),
    TOKEN_NOT_FIND(20203, "token不能为空"),
    TOKEN_FAIL(20204, "token失效"),
    ROLE_NOT_FIND(20205, "用户角色不能为空"),
    UN_AUTH(20206, "没有权限"),
    PWD_ERROR(20207, "密码错误"),
    SEND_SUCCESS(20208, "发送错误"),
    VERIFY_CODE_NOT_FIND(20209, "验证码不能为空"),
    VERIFY_CODE_FAIL(202091, "验证码错误"),
    USER_NOT_EXIST(202092, "此用户不存在，请联系管理员添加"),
    USER_LOCKED(202093, "此账号已失效，请联系管理员激活"),
    
    ADD_SUCCESS(20210, "添加成功"),
    ADD_FAIl(20211, "添加失败"),
    REMVOE_SUCCESS(20212, "删除成功"),
    REMVOE_FAIl(20213, "删除失败"),
    UPDATE_SUCCESS(20214, "修改成功"),
    UPDATE_FAIl(20215, "修改失败"),
    SEARCH_SUCCESS(20216, "查询成功"),
    SEARCH_FAIl(20217, "查询失败"),
    
    TOKEN_SUCCESS(20812,"token获取成功！"),
    TOKEN_CREATE_FAIL(20813,"token创建失败！"),
    
    
    LDAP_GROUP_NOT_EXISTS(20930,"域中不存在该群组，请验证！"),
    
    SYNC_SUCCESS(20931, "同步成功"),
    SYNC_FAIL(20932, "同步失败"),
	    
    
    DOMAIN_GROUP_NOT_EXISTS(21001,"域群组在域控中不存在，请联系管理员处理"),
    CMO_NOT_EXISTS(21003, "cmoList有成员不是CMO，请联系管理员处理"),
    NOT_CMO_PARAM(21005, "CMO不能为空");
    //209** 已存在

    private Integer code;


    private String massage;

    StatusCodeEnum(Integer code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public Integer getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }
    
    
}
