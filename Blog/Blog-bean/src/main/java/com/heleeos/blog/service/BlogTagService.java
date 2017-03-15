package com.heleeos.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    
    private final Logger logger = LogManager.getLogger(getClass());
    
    private Map<String, BlogTag> tagCache;
    
    @Autowired
    private BlogTagMapper blogTagMapper;

    public List<BlogTag> gets() {
        try {
            return blogTagMapper.gets();
        } catch (Exception e) {
            logger.error("获取[博客标签]异常,原因:" + e.getMessage());
            return null;
        }
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
        if(bean == null) return false;
        try {
            return blogTagMapper.insert(bean) == 1;
        } catch (Exception e) {
            logger.error("新增[博客标签]异常,原因:" + e.getMessage());
            return false;
        }
    }
    
    private boolean update(BlogTag bean) {
        if(bean == null) return false;
        try {
            return blogTagMapper.update(bean) == 1;
        } catch (Exception e) {
            logger.error("更新[博客标签]异常,原因:" + e.getMessage());
            return false;
        }
    }
    
    private synchronized void initCache() {
        if(tagCache == null || tagCache.isEmpty()) {
            tagCache = new HashMap<>();
            try {
                blogTagMapper.gets().forEach(blog -> tagCache.put(blog.getName(), blog));
            } catch (Exception e) {
                logger.error("获取[博客标签列表]异常,原因:" + e.getMessage());
            }
        }
    }
}
