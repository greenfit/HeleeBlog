package com.heleeos.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heleeos.blog.bean.BlogTag;
import com.heleeos.blog.mapper.BlogTagMapper;

/**
 * 博客标签数据服务层.
 * 
 * @author liyu
 */
@Service
public class BlogTagService {
    
    private Map<String, BlogTag> tagCache;
    
    @Autowired
    private BlogTagMapper blogTagMapper;

    public List<BlogTag> gets() {
        return blogTagMapper.gets();
    }
    
    /**
     * 
     */
    public synchronized BlogTag get(String name) {
        if(tagCache == null || tagCache.isEmpty()) initCache();
        BlogTag tag = tagCache.get(name);
        if(tag == null) {
            tag = new BlogTag();
            tag.setCount(1);
            tag.setName(name);
            if(insert(tag)) tagCache.put(name, tag);
        } else {
            tag.setCount(tag.getCount() + 1);
            if(update(tag)) tagCache.put(name, tag);
        }
        return tag;
    }
    
    private boolean insert(BlogTag bean) {
        blogTagMapper.insert(bean);
        return true;
    }
    
    private boolean update(BlogTag bean) {
        blogTagMapper.update(bean);
        return true;
    }
    
    private synchronized void initCache() {
        if(tagCache == null || tagCache.isEmpty()) {
            tagCache = new HashMap<>();
            blogTagMapper.gets().forEach(blog -> tagCache.put(blog.getName(), blog));
        }
    }
}
