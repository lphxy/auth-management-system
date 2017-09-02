package com.wan.cms.rpc.service.impl;

import com.wan.cms.dao.mapper.CmsTagMapper;
import com.wan.cms.dao.model.CmsTag;
import com.wan.cms.dao.model.CmsTagExample;
import com.wan.cms.rpc.api.CmsTagService;
import com.wan.common.annotation.BaseService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标签service实现
 *
 * Created by w1992wishes on 2017/8/17.
 */
@Service
@Transactional
@BaseService
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {
}
