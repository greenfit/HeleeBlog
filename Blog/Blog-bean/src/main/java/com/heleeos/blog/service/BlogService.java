package com.heleeos.blog.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.mapper.BlogMapper;

/**
 * 博客文章数据服务层.
 * 
 * @author liyu
 */
@Service
public class BlogService {
    
    private final Logger logger = LogManager.getLogger(getClass());
    
    @Autowired
    private BlogMapper blogMapper;
    
    /**
     * 保存文章.
     * 
     * @param bean 文章
     */
    public boolean save(Blog bean) {
        try {
            if(bean.getId() == 0) {
                return blogMapper.insert(bean) == 1;
            } else {
                return blogMapper.update(bean) == 1;
            }
        } catch (Exception e) {
            logger.error("保存[博客文章]异常,原因:" + e.getMessage());
            return false;
        }
    }
    
    /**
     * 使文章为删除状态.
     * 
     * @param id 文章ID
     */
    public boolean delete(Integer id) {
        try {
            blogMapper.delete(id);
            return true;
        } catch (Exception e) {
            logger.error("删除[博客文章]异常,原因:" + e.getMessage());
            return false;
        }
    }
    
    
    /**
     * 获取文章内容.
     * 
     * @param id 文章ID
     */
    public Blog get(Integer id) {
        try {
            return blogMapper.get(id);
        } catch (Exception e) {
            logger.error("获取[博客文章]异常,原因:" + e.getMessage());
            return null;
        }
    }
    
    /**
     * 查询文章.
     * 
     * @param type 分类
     * @param tags 标签
     * @param managerId 管理者ID
     * @param index 开始位置
     * @param rows 显示条数
     */
    public List<Blog> gets(Integer type, String tags, Integer managerId, Integer page, Integer rows) {
        int index = (page - 1) * rows;
        if(index < 0) index = 0;
        try {
            return blogMapper.gets(type, tags, managerId, index, rows);
        } catch (Exception e) {
            logger.error("获取[博客文章列表]异常,原因:" + e.getMessage());
            return null;
        }
    }
    
    /**
     * 获取文章个数.
     * 
     * @param type 分类
     * @param tags 标签
     * @param managerId 管理者ID
     */
    public int getCount(Integer type, String tags, Integer managerId) {
        try {
            return blogMapper.getCount(type, tags, managerId);
        } catch (Exception e) {
            logger.error("获取[博客文章个数]异常,原因:" + e.getMessage());
            return 0;
        }
    }    
}
