package com.heleeos.blog.mapper;

import java.util.List;
import com.heleeos.blog.bean.BlogModule;

public interface BlogModuleMapper {
    
    public int update(BlogModule bean) throws Exception;
    
    public BlogModule get(Integer id) throws Exception;
    
    public List<BlogModule> gets() throws Exception;
}