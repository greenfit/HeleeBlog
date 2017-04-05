package com.heleeos.blog.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BlogMapper blogMapper;
    
    /**
     * 保存文章.
     * 
     * @param bean 文章
     */
    public boolean save(Blog bean) {
        if(bean == null) return false;
        try {
            if(bean.getId() == 0) {
                return blogMapper.insert(bean) == 1;
            } else {
                return blogMapper.update(bean) == 1;
            }
        } catch (Exception e) {
            logger.error("保存[博客文章]异常,原因:{}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 使文章为删除状态.
     * 
     * @param id 文章ID
     */
    public boolean delete(Integer id) {
        if(id == null || id == 0) return false;
        try {
            blogMapper.delete(id);
            return true;
        } catch (Exception e) {
            logger.error("删除[博客文章]异常,原因:{}", e.getMessage());
            return false;
        }
    }
    
    
    /**
     * 获取文章.
     * 
     * @param id 文章ID
     */
    public Blog get(Integer id) {
        if(id == null || id == 0) return null;
        try {
            return blogMapper.get(id);
        } catch (Exception e) {
            logger.error("获取[博客文章]异常,原因:{}", e.getMessage());
            return null;
        }
    }
    
    /**
     * 根据URL获取文章.
     * 
     * @param disp 文章显示的URL.
     */
    public Blog getByURL(String disp) {
        if(StringUtils.trimToNull(disp) == null) return null;
        try {
            return blogMapper.getByURL(disp);
        } catch (Exception e) {
            logger.error("获取[博客文章(URL)]异常,原因:{}", e.getMessage());
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
    public List<Blog> gets(Integer type, String tags, Integer page, Integer rows) {
        int index = (page - 1) * rows;
        if(index < 0) index = 0;
        if(type == null || type == 0) type = null;
        if(StringUtils.trim(tags) == null) tags = null;
        try {
            return blogMapper.gets(type, tags, index, rows);
        } catch (Exception e) {
            logger.error("获取[博客文章列表]异常,原因:{}", e.getMessage());
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
    public int getCount(Integer type, String tags) {
        if(type == null || type == 0) type = null;
        if(StringUtils.trim(tags) == null) tags = null;        
        try {
            return blogMapper.getCount(type, tags);
        } catch (Exception e) {
            logger.error("获取[博客文章个数]异常,原因:{}", e.getMessage());
            return 0;
        }
    }
    
    /**
     * 修改显示顺序.
     * 
     * @param id 博客的ID
     * @param changeIndex 显示的顺序
     * 
     */
    public boolean changeIndex(Integer id, Integer changeIndex) {
        try {
            return blogMapper.changeIndex(id, changeIndex) == 1;
        } catch (Exception e) {
            logger.error("修改[博客显示顺序]异常,原因:{}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 新增阅读次数.
     * 
     * @param id 博客的ID
     */
    public boolean addCount(Integer id) {
        try {
            return blogMapper.addCount(id) == 1;
        } catch (Exception e) {
            logger.error("增加[博客阅读次数]异常,原因:{}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 新增阅读次数.
     * 
     * @param id 博客的ID
     */
    public boolean addCountByUrl(String url) {
        try {
            return blogMapper.addCountByUrl(url) == 1;
        } catch (Exception e) {
            logger.error("增加[博客阅读次数]异常,原因:{}", e.getMessage());
            return false;
        }
    }
}
