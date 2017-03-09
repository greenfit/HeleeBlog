package com.heleeos.blog.mapper;

import java.util.List;
import com.heleeos.blog.bean.BlogType;

public interface BlogTypeMapper {
    
    public int insert(BlogType bean);
    
    public int update(BlogType bean);
    
    public BlogType get(Integer id);

    public List<BlogType> gets(Integer moduleId);
}