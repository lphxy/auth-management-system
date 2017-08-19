package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsUserMapper;
import com.wan.cms.dao.model.CmsUser;
import com.wan.cms.dao.model.CmsUserExample;
import com.wan.cms.service.service.CmsUserService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/22.
 */
@Service
@Transactional
public class CmsUserServiceImpl extends BaseServiceImpl<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

}
