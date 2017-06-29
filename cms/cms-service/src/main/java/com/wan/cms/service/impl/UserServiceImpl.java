package com.wan.cms.service.impl;

import com.wan.cms.mapper.UserMapper;
import com.wan.cms.mapper.UserVOMapper;
import com.wan.cms.model.UserVO;
import com.wan.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
