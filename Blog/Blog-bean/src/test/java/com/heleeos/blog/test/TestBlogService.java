package com.heleeos.blog.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.service.BlogService;

public class TestBlogService extends TestConfig {
    
    @Autowired
    private BlogService blogService;
        
    @Test
    public void add() {
        String tags = "Spring,Spring 生命周期";
        
        Blog blog = new Blog();
        blog.setTitle("标题");
        blog.setDisp("spring-test");
        blog.setSummary("摘要");
        blog.setType("Spring精通之路");
        blog.setTags(tags);
        blog.setContent("内容");
        blog.setManagerid(1);
        
        
        blogService.save(blog);
    }
    
    @Test
    public void getList() {
        System.out.println("Count:" + blogService.getCount(0, "", false));
        blogService.gets(0, "", false, 1, 10).forEach((blog) -> {
            System.out.println(blog);
        });
    }
    
    @Test
    public void changeIndex() {
        blogService.changeIndex(2, -1);
    }
}
