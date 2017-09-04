package com.wan.upms.dao.mapper;

import com.wan.upms.dao.model.UpmsPermission;

import java.util.List;

/**
 * Created by w1992wishes on 2017/9/4.
 */
public interface UpmsApiMapper {
    // 根据用户id获取所拥有的权限
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);
}
