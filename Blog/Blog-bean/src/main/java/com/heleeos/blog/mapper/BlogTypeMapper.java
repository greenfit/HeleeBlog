package com.heleeos.blog.mapper;

import java.util.List;
import com.heleeos.blog.bean.BlogType;

public interface BlogTypeMapper {
    
    public int insert(BlogType bean) throws Exception;
    
    public int update(BlogType bean) throws Exception;
    
    public BlogType get(Integer id) throws Exception;

    public List<BlogType> gets(Integer moduleId) throws Exception;
}