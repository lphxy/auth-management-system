package com.wan.upms.rpc.server.impl;

import com.wan.common.base.BaseServiceImpl;
import com.wan.upms.dao.mapper.UpmsSystemMapper;
import com.wan.upms.dao.model.UpmsSystem;
import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 实现
 * <p>
 * Created by w1992wishes on 2017/8/14.
 */
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

    private Logger logger = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    UpmsSystemMapper upmsSystemMapper;

}
