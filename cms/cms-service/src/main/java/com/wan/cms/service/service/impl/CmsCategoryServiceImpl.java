package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.CmsCategoryMapper;
import com.wan.cms.service.service.CmsCategoryService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/7/15.
 */
@Service
@Transactional
public class CmsCategoryServiceImpl implements CmsCategoryService {
    private static Logger logger = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

    @Autowired
    private CmsCategoryMapper cmsCategoryMapper;

    @Override
    public CmsCategoryMapper getMapper() {
        return cmsCategoryMapper;
    }

    @Override
    public int deleteByPrimaryKeys(String ids) {
        if(StringUtils.isBlank(ids)){
            return 0;
        }
        String[] idArray = ids.split("-");
        int count = 0;
        for (String id : idArray){
            if (StringUtils.isBlank(id)){
                continue;
            }
            try{
                count += cmsCategoryMapper.deleteByPrimaryKey(Integer.parseInt(id));
            }catch (Exception e){
                logger.error("", e);
                return 0;
            }
        }
        return 0;
    }
}
