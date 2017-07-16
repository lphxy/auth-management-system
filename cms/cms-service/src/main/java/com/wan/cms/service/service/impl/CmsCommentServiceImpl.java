package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsCommentMapper;
import com.wan.cms.service.service.CmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Service
@Transactional
public class CmsCommentServiceImpl implements CmsCommentService {

    @Autowired
    private CmsCommentMapper cmsCommentMapper;

    @Override
    public CmsCommentMapper getMapper() {
        return cmsCommentMapper;
    }
}
