package com.heleeos.blog.test.service;

import com.heleeos.blog.test.TestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.service.RedisService;

public class TestRedisServer extends TestConfig {

    @Autowired
    private RedisService redisService;
    
    @Test
    public void testGet() {
        System.out.println(redisService.get("test"));
        System.out.println(redisService.get("test1"));
        System.out.println(redisService.get("test2"));
    }
}
