package com.wan.common.base;

/**
 * 统一返回结果类
 *
 * Created by w1992wishes on 2017/9/8.
 */
public class BaseResult {
    // 状态码：1成功，其他为失败
    protected int code;

    // 成功为success，其他为失败原因
    protected String message;

    // 数据结果集
    protected Object data;

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
