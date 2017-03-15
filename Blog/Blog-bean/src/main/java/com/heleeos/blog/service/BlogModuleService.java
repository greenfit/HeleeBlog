package com.heleeos.blog.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heleeos.blog.bean.BlogModule;
import com.heleeos.blog.mapper.BlogModuleMapper;

/**
 * 博客模块数据服务层.
 * 
 * @author liyu
 */
@Service
public class BlogModuleService {
    
    private final Logger logger = LogManager.getLogger(getClass());
    
    @Autowired
    private BlogModuleMapper blogModuleMapper;
    
    public boolean save(BlogModule bean) {
        if(bean == null) return false;
        try {
            return blogModuleMapper.update(bean) == 1;
        } catch (Exception e) {
            logger.error("保存[博客模块]异常,原因:" + e.getMessage());
            return false;
        }
    }
    
    public BlogModule get(Integer id) {
        try {
            return blogModuleMapper.get(id);
        } catch (Exception e) {
            logger.error("获取[博客模块]异常,原因:" + e.getMessage());
            return null;
        }
    }
   
    public List<BlogModule> gets() {
        try {
            return blogModuleMapper.gets();
        } catch (Exception e) {
            logger.error("获取[博客模块列表]异常,原因:" + e.getMessage());
            return null;
        }
    }
}
