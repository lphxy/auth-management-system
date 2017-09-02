package com.wan.cms.rpc.service.impl;

import com.wan.cms.dao.mapper.CmsArticleMapper;
import com.wan.cms.dao.model.CmsArticle;
import com.wan.cms.dao.model.CmsArticleExample;
import com.wan.cms.rpc.api.CmsArticleService;
import com.wan.common.annotation.BaseService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章service实现
 *
 * Created by w1992wishes on 2017/8/17.
 */
@Service
@Transactional
@BaseService
public class CmsArticleServiceImpl extends BaseServiceImpl<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {
}
