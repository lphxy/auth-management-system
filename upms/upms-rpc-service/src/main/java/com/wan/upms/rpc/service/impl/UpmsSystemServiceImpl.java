package com.wan.upms.rpc.service.impl;

import com.wan.common.base.BaseServiceImpl;
import com.wan.upms.dao.mapper.UpmsSystemMapper;
import com.wan.upms.dao.model.UpmsSystem;
import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实现
 * <p>
 * Created by w1992wishes on 2017/8/14.
 */
@Service
@Transactional
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {
}
