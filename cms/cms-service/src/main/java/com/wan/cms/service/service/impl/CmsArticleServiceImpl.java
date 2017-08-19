package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsArticleMapper;
import com.wan.cms.dao.model.CmsArticle;
import com.wan.cms.dao.model.CmsArticleExample;
import com.wan.cms.service.service.CmsArticleService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Service
@Transactional
public class CmsArticleServiceImpl extends BaseServiceImpl<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {
}
