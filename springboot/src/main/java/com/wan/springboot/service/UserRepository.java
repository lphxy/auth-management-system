package com.wan.springboot.service;

import com.wan.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by w1992wishes on 2017/7/20.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

//	@Query("from user u where u.name=:name")
//	User findUser(@Param("name") String name);
}
