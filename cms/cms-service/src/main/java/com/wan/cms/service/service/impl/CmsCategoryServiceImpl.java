package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsCategoryMapper;
import com.wan.cms.service.service.CmsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by w1992wishes on 2017/7/15.
 */
public class CmsCategoryServiceImpl implements CmsCategoryService {
    private static Logger logger = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

    @Autowired
    private CmsCategoryMapper cmsCategoryMapper;

    @Override
    public CmsCategoryMapper getMapper() {
        return cmsCategoryMapper;
    }
}
