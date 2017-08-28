package com.wan.cms.rpc.dao.mapper;

import com.wan.cms.rpc.dao.UserVO;

/**
 * 用户VOMapper
 *
 * Created by w1992wishes on 2017/8/17.
 */
public interface UserVOMapper {
    UserVO selectUserWithBook(int id);
}
