package com.heleeos.blog.mapper;

import java.util.List;
import com.heleeos.blog.bean.BlogTag;

public interface BlogTagMapper {
    
    public int insert(BlogTag bean) throws Exception;
    
    public int update(BlogTag bean) throws Exception;

    public List<BlogTag> gets() throws Exception;
}