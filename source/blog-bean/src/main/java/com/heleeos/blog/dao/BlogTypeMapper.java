package com.heleeos.blog.dao;

import java.util.List;
import com.heleeos.blog.bean.BlogType;

/**
 * 博客分类表的操作, t_blog_type.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public interface BlogTypeMapper {
    
    int insert(BlogType bean) throws Exception;
    
    int update(BlogType bean) throws Exception;

    BlogType get(Integer id) throws Exception;

    List<BlogType> getList() throws Exception;
}