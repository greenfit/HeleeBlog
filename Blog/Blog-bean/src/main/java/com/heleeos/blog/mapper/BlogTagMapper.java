package com.heleeos.blog.mapper;

import java.util.List;
import com.heleeos.blog.bean.BlogTag;

public interface BlogTagMapper {
    
    public int insert(BlogTag bean);
    
    public int update(BlogTag bean);

    public List<BlogTag> gets();
}