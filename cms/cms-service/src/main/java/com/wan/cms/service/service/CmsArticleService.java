package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsArticleMapper;
import com.wan.cms.dao.model.CmsArticle;
import com.wan.cms.dao.model.CmsArticleExample;
import com.wan.common.base.BaseService;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsArticleService extends BaseService<CmsArticle, CmsArticleExample> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
