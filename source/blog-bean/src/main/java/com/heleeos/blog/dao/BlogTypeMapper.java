package com.heleeos.blog.dao;

import java.util.List;
import com.heleeos.blog.bean.BlogType;

public interface BlogTypeMapper {
    
    int insert(BlogType bean) throws Exception;
    
    int update(BlogType bean) throws Exception;
    
    BlogType get(Integer id) throws Exception;

    List<BlogType> gets() throws Exception;
    
    List<BlogType> getCount() throws Exception;
}