package com.wan.cms.rpc.api;

import com.wan.cms.dao.mapper.CmsUserMapper;
import com.wan.cms.dao.model.CmsUser;
import com.wan.cms.dao.model.CmsUserExample;
import com.wan.cms.rpc.dao.UserVO;
import com.wan.common.base.BaseServiceMock;

/**
 * 降级实现UserService接口
 *
 * Created by w1992wishes on 2017/9/7.
 */
public class UserServiceMock extends BaseServiceMock<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {
    @Override
    public UserVO selectUserWithBook(int id) {
        return null;
    }
}

