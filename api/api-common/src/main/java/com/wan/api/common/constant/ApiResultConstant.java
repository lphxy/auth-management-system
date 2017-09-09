package com.wan.api.common.constant;

/**
 * api系统接口结果常量枚举类
 *
 * Created by w1992wishes on 2017/9/9.
 */
public enum ApiResultConstant {
    SUCCESS(1, "success");

    public int code;

    public String message;

    ApiResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
