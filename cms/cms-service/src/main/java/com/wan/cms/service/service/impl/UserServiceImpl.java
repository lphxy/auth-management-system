package com.wan.cms.service.service.impl;

import com.wan.cms.dao.mapper.UserMapper;
import com.wan.cms.service.mapper.UserVOMapper;
import com.wan.cms.service.model.UserVO;
import com.wan.cms.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/6/26.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserVOMapper userVOMapper;

    /**
     * 获取基本操作mapper
     * @return
     */
    @Override
    public UserMapper getMapper() {
        return userMapper;
    }

    /**
     * 获取带book数据的用户
     * @param id
     * @return
     */
    @Override
    public UserVO selectUserWithBook(int id) {
        return userVOMapper.selectUserWithBook(id);
    }
}
