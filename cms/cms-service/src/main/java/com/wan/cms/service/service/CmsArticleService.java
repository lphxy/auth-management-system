package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsArticleMapper;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsArticleService extends BaseService<CmsArticleMapper> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
