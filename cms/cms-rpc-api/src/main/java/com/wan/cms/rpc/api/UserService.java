package com.wan.cms.rpc.api;

import com.wan.cms.dao.model.User;
import com.wan.cms.dao.model.UserExample;
import com.wan.cms.rpc.dao.UserVO;
import com.wan.common.base.BaseService;

/**
 * Created by w1992wishes on 2017/8/17.
 */
public interface UserService  extends BaseService<User, UserExample> {
    /**
     * 获取带book数据的用户
     *
     * @param id
     * @return
     */
    UserVO selectUserWithBook(int id);
}
