package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsCategoryMapper;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsCategoryService extends BaseService<CmsCategoryMapper> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
