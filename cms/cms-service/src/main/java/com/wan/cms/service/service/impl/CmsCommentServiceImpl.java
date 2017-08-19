package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsCommentMapper;
import com.wan.cms.dao.model.CmsComment;
import com.wan.cms.dao.model.CmsCommentExample;
import com.wan.cms.service.service.CmsCommentService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Service
@Transactional
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

}
