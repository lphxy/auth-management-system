package com.wan.cms.rpc.server.impl;

import com.wan.cms.dao.mapper.UserMapper;
import com.wan.cms.dao.model.User;
import com.wan.cms.dao.model.UserExample;
import com.wan.cms.rpc.api.UserService;
import com.wan.cms.rpc.dao.UserVO;
import com.wan.cms.rpc.dao.mapper.UserVOMapper;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

/**
 * 用户service实现
 *
 * Created by w1992wishes on 2017/8/17.
 */
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

    @Autowired
    private UserVOMapper userVOMapper;

    /**
     * 获取带book数据的用户
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value="ehcache")
    public UserVO selectUserWithBook(int id) {
        return userVOMapper.selectUserWithBook(id);
    }
}
