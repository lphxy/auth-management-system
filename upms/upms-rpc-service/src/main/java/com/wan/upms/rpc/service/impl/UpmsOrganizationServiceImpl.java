package com.wan.upms.rpc.service.impl;

import com.wan.common.annotation.BaseService;
import com.wan.common.base.BaseServiceImpl;
import com.wan.upms.dao.mapper.UpmsOrganizationMapper;
import com.wan.upms.dao.model.UpmsOrganization;
import com.wan.upms.dao.model.UpmsOrganizationExample;
import com.wan.upms.rpc.api.UpmsOrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/8/31.
 */
@Service
@Transactional
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {
}
