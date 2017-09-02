package com.wan.cms.rpc.service.impl;

import com.wan.cms.dao.mapper.CmsCategoryMapper;
import com.wan.cms.dao.model.CmsCategory;
import com.wan.cms.dao.model.CmsCategoryExample;
import com.wan.cms.rpc.api.CmsCategoryService;
import com.wan.common.annotation.BaseService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类目service实现
 *
 * Created by w1992wishes on 2017/8/17.
 */
@Service
@Transactional
@BaseService
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {
}
