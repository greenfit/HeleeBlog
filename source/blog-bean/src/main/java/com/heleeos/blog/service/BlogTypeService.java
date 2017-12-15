package com.heleeos.blog.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heleeos.blog.bean.BlogType;
import com.heleeos.blog.dao.BlogTypeMapper;

/**
 * 博客分类数据服务层.
 * 
 * @author liyu
 */
@Service
public class BlogTypeService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BlogTypeMapper blogTypeMapper;
    
    public boolean save(BlogType bean) {
        if(bean == null) return false;
        try {
            if(bean.getId() == null || bean.getId() == 0) {
                return blogTypeMapper.insert(bean) == 1;
            } else {
                return blogTypeMapper.update(bean) == 1;
            }
        } catch (Exception e) {
            logger.error("保存[博客分类]异常,原因:{}", e.getMessage());
            return false;
        }
    }
    
    public BlogType get(Integer id) {
        if(id == null || id == 0) return null;
        try {
            return blogTypeMapper.get(id);
        } catch (Exception e) {
            logger.error("获取[博客分类]异常,原因:{}", e.getMessage());
            return null;
        }
    }

    public List<BlogType> gets() {
        try {
            return blogTypeMapper.gets();
        } catch (Exception e) {
            logger.error("获取[博客分类列表]异常,原因:{}", e.getMessage());
            return null;
        }
    }
    
    public List<BlogType> getCount() {
        try {
            return blogTypeMapper.getCount();
        } catch (Exception e) {
            logger.error("获取[博客分类列表个数]异常,原因:{}", e.getMessage());
            return null;
        }
    }
}
