package com.wan.upms.rpc.api;

/**
 * Created by w1992wishes on 2017/8/14.
 */

import com.wan.upms.dao.mapper.UpmsSystemMapper;

/**
 * 系统service接口
 * Created by shuzheng on 2016/12/18.
 */
public interface UpmsSystemService extends BaseService<UpmsSystemMapper> {

    // 批量删除
    int deleteByPrimaryKeys(String ids);
}
