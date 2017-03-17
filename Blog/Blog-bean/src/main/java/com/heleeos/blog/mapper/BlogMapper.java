package com.heleeos.blog.mapper;

import java.util.List;

import com.heleeos.blog.bean.Blog;

public interface BlogMapper {
    
    public int insert(Blog bean) throws Exception;
    
    public int delete(Integer id) throws Exception;
    
    public int update(Blog bean) throws Exception;
    
    public Blog get(Integer id) throws Exception;
    
    public Blog getByURL(String disp) throws Exception;
    
    public List<Blog> gets(Integer type, String tags, Integer managerId, Integer index, Integer rows) throws Exception;
    
    public int getCount(Integer type, String tags, Integer managerId) throws Exception;
    
}