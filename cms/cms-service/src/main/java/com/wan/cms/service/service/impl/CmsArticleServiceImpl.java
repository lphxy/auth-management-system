package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsArticleMapper;
import com.wan.cms.service.service.CmsArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public class CmsArticleServiceImpl implements CmsArticleService {
    private static Logger logger = LoggerFactory.getLogger(CmsArticleServiceImpl.class);

    @Autowired
    private CmsArticleMapper cmsArticleMapper;

    @Override
    public CmsArticleMapper getMapper() {
        return cmsArticleMapper;
    }
}
