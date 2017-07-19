package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsCommentMapper;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsCommentService extends BaseService<CmsCommentMapper> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
