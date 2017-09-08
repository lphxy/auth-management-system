package com.wan.upms.common.constant;

import com.wan.common.base.BaseResult;

/**
 * Created by w1992wishes on 2017/9/8.
 */
public class UpmsResult extends BaseResult {
    public UpmsResult(int code, String message, Object data) {
        super(code, message, data);
    }

    public UpmsResult(UpmsResultConstant upmsResultConstant, Object data) {
        super(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }
}
