package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsCategoryMapper;
import com.wan.cms.dao.model.CmsCategory;
import com.wan.cms.dao.model.CmsCategoryExample;
import com.wan.cms.service.service.CmsCategoryService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Service
@Transactional
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {
}
