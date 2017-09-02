package com.wan.upms.rpc.service.impl;

import com.wan.common.annotation.BaseService;
import com.wan.common.base.BaseServiceImpl;
import com.wan.upms.dao.mapper.UpmsPermissionMapper;
import com.wan.upms.dao.model.UpmsPermission;
import com.wan.upms.dao.model.UpmsPermissionExample;
import com.wan.upms.rpc.api.UpmsPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/8/31.
 */
@Service
@Transactional
@BaseService
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {
}
