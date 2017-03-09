package com.heleeos.blog.service;

import java.util.List;
import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.mapper.BlogMapper;

/**
 * 博客文章数据服务层.
 * 
 * @author liyu
 */
public class BlogService {
    
    private BlogMapper blogMapper;
    
    /**
     * 保存文章.
     * 
     * @param bean 文章
     */
    public boolean save(Blog bean) {
        if(bean.getId() == 0) {
            return blogMapper.insert(bean) == 1;
        } else {
            return blogMapper.update(bean) == 1;
        }
    }
    
    /**
     * 使文章为删除状态.
     * 
     * @param id 文章ID
     */
    public boolean delete(Integer id) {
        blogMapper.delete(id);
        return true;
    }
    
    
    /**
     * 获取文章内容.
     * 
     * @param id 文章ID
     */
    public Blog get(Integer id) {
        return blogMapper.get(id);
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
        return blogMapper.gets(type, tags, managerId, index, rows);
    }
    
    /**
     * 获取文章个数.
     * 
     * @param type 分类
     * @param tags 标签
     * @param managerId 管理者ID
     */
    public int getCount(Integer type, String tags, Integer managerId) {
        return blogMapper.getCount(type, tags, managerId);
    }
}
