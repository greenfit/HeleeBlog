package com.heleeos.blog.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.heleeos.blog.bean.BlogType;
import com.heleeos.blog.mapper.BlogTypeMapper;

/**
 * 博客分类数据服务层.
 * 
 * @author liyu
 */
@Service
public class BlogTypeService {
    
    private final Logger logger = LogManager.getLogger(getClass());
    
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
            logger.error("保存[博客分类]异常,原因:" + e.getMessage());
            return false;
        }
    }
    
    public BlogType get(Integer id) {
        if(id == null || id == 0) return null;
        try {
            return blogTypeMapper.get(id);
        } catch (Exception e) {
            logger.error("获取[博客分类]异常,原因:" + e.getMessage());
            return null;
        }
    }

    public List<BlogType> gets(Integer moduleId) {
        if(moduleId == null || moduleId == 0) return null;
        try {
            return blogTypeMapper.gets(moduleId);
        } catch (Exception e) {
            logger.error("获取[博客分类(模块)]异常,原因:" + e.getMessage());
            return null;
        }
    }
}
