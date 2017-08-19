package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsCategoryMapper;
import com.wan.cms.dao.model.CmsCategory;
import com.wan.cms.dao.model.CmsCategoryExample;
import com.wan.common.base.BaseService;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsCategoryService extends BaseService<CmsCategory, CmsCategoryExample> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
