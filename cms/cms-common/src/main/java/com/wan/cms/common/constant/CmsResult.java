package com.wan.cms.common.constant;

import com.wan.common.base.BaseResult;

/**
 * Created by w1992wishes on 2017/9/9.
 */
public class CmsResult extends BaseResult {
    public CmsResult(int code, String message, Object data) {
        super(code, message, data);
    }

    public CmsResult(CmsResultConstant cmsResultConstant, Object data){
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }
}
