package com.heleeos.blog.test.service;

import com.heleeos.blog.test.TestConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.service.BlogTypeService;

public class TestBlogTypeService extends TestConfig {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private BlogTypeService blogTypeService;

    @Test
    public void save() {
        toLogger(logger, blogTypeService.save(blogTypeService.get(2)));
    }

    @Test
    public void get() {
        toLogger(logger, blogTypeService.get(2));
    }
    
    @Test
    public void getList() {
        toLogger(logger, blogTypeService.getList());
    }
}
