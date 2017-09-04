package com.wan.upms.rpc.api;

import com.wan.upms.dao.model.UpmsPermission;

import java.util.List;

/**
 * upms系统接口
 *
 * Created by w1992wishes on 2017/9/4.
 */
public interface UpmsApiService {
    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);
}
