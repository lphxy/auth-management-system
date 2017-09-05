package com.wan.upms.rpc.api;

import com.wan.upms.dao.model.UpmsPermission;
import com.wan.upms.dao.model.UpmsRole;

import java.util.List;

/**
 * upms系统接口
 *
 * Created by w1992wishes on 2017/9/4.
 */
public interface UpmsApiService {
    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);
}
