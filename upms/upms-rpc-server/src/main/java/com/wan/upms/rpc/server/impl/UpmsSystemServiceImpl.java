package com.wan.upms.rpc.server.impl;

import com.wan.upms.dao.mapper.UpmsSystemMapper;
import com.wan.upms.rpc.api.UpmsSystemService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现
 *
 * Created by w1992wishes on 2017/8/14.
 */
public class UpmsSystemServiceImpl implements UpmsSystemService {

    private Logger logger = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    private UpmsSystemMapper upmsSystemMapper;

    @Override
    public UpmsSystemMapper getMapper() {
        return upmsSystemMapper;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKeys(String ids) {
        if (StringUtils.isBlank(ids)){
            return 0;
        }
        String[] idArray = ids.split("-");
        int count = 0;
        for (String id : idArray){
            if (StringUtils.isBlank(id))
                continue;
            try{
                count += upmsSystemMapper.deleteByPrimaryKey(Integer.parseInt(id));
            }catch (Exception e){
                logger.error("", e);
                return 0;
            }
        }
        return count;
    }
}
