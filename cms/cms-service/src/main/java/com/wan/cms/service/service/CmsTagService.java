package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsTagMapper;
import com.wan.cms.dao.model.CmsTag;
import com.wan.cms.dao.model.CmsTagExample;
import com.wan.common.base.BaseService;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsTagService extends BaseService<CmsTag, CmsTagExample> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
