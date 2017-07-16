package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsTagMapper;
import com.wan.cms.service.service.CmsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Service
@Transactional
public class CmsTagServiceImpl implements CmsTagService {

    @Autowired
    private CmsTagMapper cmsTagMapper;

    @Override
    public CmsTagMapper getMapper() {
        return cmsTagMapper;
    }
}
