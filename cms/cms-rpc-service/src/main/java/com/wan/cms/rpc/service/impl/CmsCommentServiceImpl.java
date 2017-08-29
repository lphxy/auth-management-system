package com.wan.cms.rpc.service.impl;

import com.wan.cms.dao.mapper.CmsCommentMapper;
import com.wan.cms.dao.model.CmsComment;
import com.wan.cms.dao.model.CmsCommentExample;
import com.wan.cms.rpc.api.CmsCommentService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评论service实现
 *
 * Created by w1992wishes on 2017/8/17.
 */
@Service
@Transactional
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {
}
