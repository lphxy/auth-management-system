package com.wan.cms.service.mapper;

import com.wan.cms.dao.mapper.UserMapper;
import com.wan.cms.service.model.UserVO;

/**
 * Created by w1992wishes on 2017/6/26.
 */
public interface UserVOMapper extends UserMapper {
    UserVO selectUserWithBook(int id);
}
