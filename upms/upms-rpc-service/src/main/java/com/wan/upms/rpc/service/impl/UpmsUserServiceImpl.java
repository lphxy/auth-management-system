package com.wan.upms.rpc.service.impl;

import com.wan.common.base.BaseServiceImpl;
import com.wan.upms.dao.mapper.UpmsUserMapper;
import com.wan.upms.dao.model.UpmsUser;
import com.wan.upms.dao.model.UpmsUserExample;
import com.wan.upms.rpc.api.UpmsUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/8/24.
 */
@Service
@Transactional
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {
}
