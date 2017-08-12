package com.wan.upms.service;

import com.wan.upms.dao.mapper.UpmsSystemMapper;

/**
 * Created by w1992wishes on 2017/8/12.
 */
public interface UpmsSystemService extends BaseService<UpmsSystemMapper> {

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByPrimaryKeys(String ids);
}
