package com.wan.api.common.constant;

import com.wan.common.base.BaseResult;

/**
 * Created by w1992wishes on 2017/9/9.
 */
public class ApiResult extends BaseResult {
    public ApiResult(int code, String message, Object data) {
        super(code, message, data);
    }

    public ApiResult(ApiResultConstant apiResultConstant, Object data) {
        super(apiResultConstant.getCode(), apiResultConstant.getMessage(), data);
    }
}
