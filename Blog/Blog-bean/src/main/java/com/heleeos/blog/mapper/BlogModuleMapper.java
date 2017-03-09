package com.heleeos.blog.mapper;

import java.util.List;
import com.heleeos.blog.bean.BlogModule;

public interface BlogModuleMapper {
    
    public int update(BlogModule bean);
    
    public BlogModule get(Integer id);
    
    public List<BlogModule> gets();
}