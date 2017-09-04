package com.wan.cms.rpc.service.impl;

import com.wan.cms.dao.mapper.CmsUserMapper;
import com.wan.cms.dao.model.CmsUser;
import com.wan.cms.dao.model.CmsUserExample;
import com.wan.cms.rpc.api.CmsUserService;
import com.wan.cms.rpc.dao.UserVO;
import com.wan.cms.rpc.dao.mapper.UserVOMapper;
import com.wan.common.annotation.BaseService;
import com.wan.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现
 *
 * Created by w1992wishes on 2017/8/17.
 */
@Service
@Transactional
@BaseService
public class UserServiceImpl extends BaseServiceImpl<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

    @Autowired
    private UserVOMapper userVOMapper;

    /**
     * 获取带book数据的用户
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value="cms-ehcache")
    public UserVO selectUserWithBook(int id) {
        return userVOMapper.selectUserWithBook(id);
    }
}
