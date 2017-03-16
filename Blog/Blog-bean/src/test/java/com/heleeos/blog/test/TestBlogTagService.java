package com.heleeos.blog.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.heleeos.blog.service.BlogTagService;

public class TestBlogTagService {
    
    @Autowired
    private BlogTagService blogTagService;
    
    @Test
    public void getTags() {
        blogTagService.getCache().forEach((key, value) -> System.out.println(String.format("key:%s,value:%s", key, value)));
    }
    
}
