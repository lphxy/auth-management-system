package com.wan.upms.rpc.service.impl;

import com.wan.common.annotation.BaseService;
import com.wan.common.base.BaseServiceImpl;
import com.wan.upms.dao.mapper.UpmsRoleMapper;
import com.wan.upms.dao.model.UpmsRole;
import com.wan.upms.dao.model.UpmsRoleExample;
import com.wan.upms.rpc.api.UpmsRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/8/31.
 */
@Service
@Transactional
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {
}
