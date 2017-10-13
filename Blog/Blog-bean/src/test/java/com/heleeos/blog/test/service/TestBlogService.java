package com.heleeos.blog.test.service;

import com.google.gson.Gson;
import com.heleeos.blog.constant.BlogState;
import com.heleeos.blog.test.TestConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.service.BlogService;

public class TestBlogService extends TestConfig {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private BlogService blogService;

    private Gson gson = new Gson();
        
    @Test
    public void save() {
        String tags = "Spring,Spring 生命周期";
        
        Blog blog = new Blog();
        blog.setBlogTitle("标题");
        blog.setDispUrl("spring-test");
        blog.setBlogSummary("摘要");
        blog.setBlogType("Spring精通之路");
        blog.setBlogTags(tags);
        blog.setBlogContent("内容");
        blog.setManagerId(1);

        boolean flag = blogService.save(blog);
        logger.info("保存成功？" + flag);
    }

    @Test
    public void getBlog() {
        Blog blog1 = blogService.get(1);
        Blog blog2 = blogService.getByURL("spring-info");
        logger.info(gson.toJson(blog1));
        logger.info(gson.toJson(blog2));
    }

    @Test
    public void getList() {
        int count = blogService.getCount(null, null, null);
        logger.info("Count:" + count);
        blogService.getList(null, null, null, 1, 10).forEach((blog) -> logger.info(gson.toJson(blog)));
    }
    
    @Test
    public void changeIndex() {
        boolean flag1 = blogService.changeIndex(2, (byte) 0);
        boolean flag2 = blogService.changeState(2, BlogState.NORMAL);
        logger.info("Change index :" + flag1);
        logger.info("Change state :" + flag2);
    }
}
