package com.wan.cms.rpc.server.impl;

import com.wan.cms.dao.mapper.CmsPageMapper;
import com.wan.cms.dao.model.CmsPage;
import com.wan.cms.dao.model.CmsPageExample;
import com.wan.cms.rpc.api.CmsPageService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/8/18.
 */
@Service
@Transactional
public class CmsPageServiceImpl extends BaseServiceImpl<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {
}
