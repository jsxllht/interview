package com.csqf.common.result;

public enum ResponseEnum {

    SUCCESS("200","成功003"),
    USERNAME_NOT_FOUND("300","用户名不存在"),
    USERNAME_OR_PASSWORD_INVALIDATE("301","用户名或者密码错误"),
    ROLE_NO_MENUS("302","此角色没有任何菜单权限，请尽早分配"),
    HAS_NO_TOKEN("303","还没有登录"),
    TOKEN_ERROR("304","别想试图伪造令牌"),
    TOKEN_TIMEOUT("305","对不起登录状态已经失效，请重新登录"),
    HAS_NO_RIGHT("306","权限不足"),
    SYSTEM_ERROR("500","发生未知异常。。。"),
    FEIGN_BSUY("307","系统正忙，稍后再试。。"),
    FLOW_BLOCK("308","流量被限制了，请稍后再使"),
    DEG_BLOCK("309","系统很忙，稍后再试。。。"),
    SMS_ERROR("310","短信发送失败，请稍后再试。。。"),
    SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL("311","发送短信太频繁，过会再发。。。"),
    CODE_ERROR("312","验证码错误请重试"),
    CODE_EXCPTION("313","验证码超时"),
    ACCOUNT_NO_EXISTS("314","用户不存在")
    ;

    private String code;
    private String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
