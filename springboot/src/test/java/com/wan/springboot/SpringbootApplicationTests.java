package com.wan.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by w1992wishes on 2017/7/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class SpringbootApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        System.out.println("=============================== redis start ===============================");
        // 保存字符串
//		stringRedisTemplate.opsForValue().set("aaa", "111");
//		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
        System.out.println("=============================== redis end ===============================");
    }

}