package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.CmsCommentMapper;
import com.wan.cms.dao.model.CmsComment;
import com.wan.cms.dao.model.CmsCommentExample;
import com.wan.common.base.BaseService;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public interface CmsCommentService extends BaseService<CmsComment, CmsCommentExample> {
    //批量删除
    int deleteByPrimaryKeys(String ids);
}
