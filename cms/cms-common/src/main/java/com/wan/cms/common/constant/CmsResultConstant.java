package com.wan.cms.common.constant;

/**
 * cms系统接口结果常量枚举类
 *
 * Created by w1992wishes on 2017/9/9.
 */
public enum CmsResultConstant {
    SUCCESS(1, "success"),
    FILE_TYPE_ERROR(20001, "File type not supported!");

    private int code;
    private String message;

    private CmsResultConstant(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
