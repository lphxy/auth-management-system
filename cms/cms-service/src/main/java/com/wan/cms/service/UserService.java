package com.wan.cms.service;

import com.wan.cms.mapper.UserMapper;
import com.wan.cms.model.User;
import com.wan.cms.model.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes on 2017/6/26.
 */
public interface UserService extends BaseService<UserMapper>{
    /**
     * 获取带book数据的用户
     * @param id
     * @return
     */
    UserVO selectUserWithBook(int id);
}
