package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsTagMapper;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsTagService extends BaseService<CmsTagMapper> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
