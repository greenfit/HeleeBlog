package com.heleeos.cms.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.cms.service.RedisService;

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
