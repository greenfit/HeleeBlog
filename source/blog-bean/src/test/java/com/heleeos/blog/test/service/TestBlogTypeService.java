package com.heleeos.blog.test.service;

import com.heleeos.blog.test.TestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.service.BlogTypeService;

public class TestBlogTypeService extends TestConfig {

    @Autowired
    private BlogTypeService blogTypeService;
    
    @Test
    public void gets() {
        blogTypeService.gets().forEach(type -> System.out.println(type));        
    }
}
