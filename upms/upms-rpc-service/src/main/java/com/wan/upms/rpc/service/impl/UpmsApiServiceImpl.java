package com.wan.upms.rpc.service.impl;

import com.wan.upms.dao.mapper.UpmsApiMapper;
import com.wan.upms.dao.mapper.UpmsUserMapper;
import com.wan.upms.dao.model.UpmsPermission;
import com.wan.upms.dao.model.UpmsUser;
import com.wan.upms.rpc.api.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by w1992wishes on 2017/9/4.
 */
@Service
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService {
    private Logger logger = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;
    @Autowired
    UpmsApiMapper upmsApiMapper;

    /**
     *  根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            logger.info("根据用户id获取所拥有的权限，用户不存在或锁定状态：upmsUserId={}", upmsUserId);
            return null;
        }
        // 根据用户查询所拥有所有权限
        List<UpmsPermission> upmsPermissions = upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
        return upmsPermissions;
    }
}
