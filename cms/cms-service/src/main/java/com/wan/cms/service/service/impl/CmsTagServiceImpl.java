package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsTagMapper;
import com.wan.cms.dao.model.CmsTag;
import com.wan.cms.dao.model.CmsTagExample;
import com.wan.cms.service.service.CmsTagService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Service
@Transactional
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

}
