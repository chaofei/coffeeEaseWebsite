package com.cew.result;

/**
 * Created by chenchaofei on 2017/3/11.
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS(200, "成功"),

    /** 验证码错误 */
    FORBIDDEN(403, "拒绝访问"),

    /** 页面不存在 */
    PAGE_NOT_FOUND(404, "页面不存在"),

    /** 验证码错误 */
    INVALID_CAPCODE(444, "无效的验证码"),

    /**
     * 登录失败
     */
    LOGIN_FAIL(500, "登录失败"),

    /**
     * 登录成功
     */
    LOGIN_SUCCESS(600, "登录成功"),

    ;

    private ResultCode(int value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public int val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private int val;
    private String msg;
}
