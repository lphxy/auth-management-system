package com.wan.upms.service.impl;

import com.wan.upms.dao.mapper.UpmsSystemMapper;
import com.wan.upms.service.UpmsSystemService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/8/12.
 */
@Service
@Transactional
public class UpmsSystemServiceImpl implements UpmsSystemService {

    private static Logger logger = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    private UpmsSystemMapper upmsSystemMapper;

    @Override
    public UpmsSystemMapper getMapper() {
        return upmsSystemMapper;
    }

    // 批量删除
    @Override
    public int deleteByPrimaryKeys(String ids) {
        if (StringUtils.isBlank(ids)) {
            return 0;
        }
        String[] idArray = ids.split("-");
        int count = 0;
        for (String id : idArray) {
            if (StringUtils.isBlank(id)) {
                continue;
            }
            try {
                count += upmsSystemMapper.deleteByPrimaryKey(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return count;
    }
}
