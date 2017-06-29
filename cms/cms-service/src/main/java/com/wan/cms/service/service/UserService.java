package com.wan.cms.service.service;

import com.wan.cms.dao.mapper.UserMapper;
import com.wan.cms.service.model.UserVO;

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
