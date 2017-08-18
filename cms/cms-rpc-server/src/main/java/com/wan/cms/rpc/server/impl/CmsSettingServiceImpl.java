package com.wan.cms.rpc.server.impl;

import com.wan.cms.dao.mapper.CmsSettingMapper;
import com.wan.cms.dao.model.CmsSetting;
import com.wan.cms.dao.model.CmsSettingExample;
import com.wan.cms.rpc.api.CmsSettingService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/8/18.
 */
@Service
@Transactional
public class CmsSettingServiceImpl extends BaseServiceImpl<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {
}
