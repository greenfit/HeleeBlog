package com.heleeos.blog.test.service;

import com.heleeos.blog.common.BlogState;
import com.heleeos.blog.test.TestConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.service.BlogService;

public class TestBlogService extends TestConfig {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private BlogService blogService;

    @Test
    public void save() {
        toLogger(logger, blogService.save(blogService.get(1)));
    }

    @Test
    public void getBlog() {
        toLogger(logger, blogService.get(1));
        toLogger(logger, blogService.getByURL("spring-info"));
    }

    @Test
    public void getList() {
        int count = blogService.getCount(null, null, null);
        logger.info("Count:" + count);
        toLogger(logger, blogService.getList(null, null, null, 1, 10));
    }
    
    @Test
    public void changeIndex() {
        boolean flag1 = blogService.changeIndex(2, (byte) 0);
        boolean flag2 = blogService.changeState(2, BlogState.NORMAL);
        logger.info("Change index :" + flag1);
        logger.info("Change state :" + flag2);
    }
}
