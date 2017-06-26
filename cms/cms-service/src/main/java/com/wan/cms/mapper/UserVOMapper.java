package com.wan.cms.mapper;

import com.wan.cms.model.User;
import com.wan.cms.model.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes on 2017/6/26.
 */
public interface UserVOMapper extends UserMapper {
    UserVO selectUserWithBook(int id);

    List<User> selectAll(Map<String, Object> map);

    void insertAutoKey(User user);
}
