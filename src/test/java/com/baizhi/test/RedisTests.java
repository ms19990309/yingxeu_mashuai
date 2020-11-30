package com.baizhi.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisTests {



    @Resource
    RedisTemplate redisTemplate;

    @Test
    void queryPhone(){

        redisTemplate.opsForValue().set("age","18");



    }
}
