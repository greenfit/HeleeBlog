package com.heleeos.blog.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.bean.BlogTag;
import com.heleeos.blog.service.BlogService;
import com.heleeos.blog.service.BlogTagService;

public class TestBlogService extends TestConfig {
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private BlogTagService blogTagService;
    
    @Test
    public void add() {
        String tags = "Spring,Spring 生命周期";
        
        Blog blog = new Blog();
        blog.setTitle("Spring 生命周期");
        blog.setTypeid(1);
        blog.setTags(getTag(tags).toString());
        blog.setContent("");
        blog.setIstop(true);
        blog.setManagerid(1);
        
        blogService.save(blog);
    }
    
    private List<BlogTag> getTag(String tags) {
        final List<BlogTag> lists = new LinkedList<>();
        Arrays.asList(tags.split(",")).forEach(tag -> lists.add(blogTagService.get(tag)));
        return lists;
    }
    
    @Test
    public void getList() {
        System.out.println("Count:" + blogService.getCount(0, "", 0));
        blogService.gets(0, "", 0, 1, 10).forEach((blog) -> {
            System.out.println(blog);
        });
    }
}
