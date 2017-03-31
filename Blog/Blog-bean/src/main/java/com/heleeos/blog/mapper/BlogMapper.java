package com.heleeos.blog.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heleeos.blog.bean.Blog;

public interface BlogMapper {
    
    public int insert(Blog bean) throws Exception;
    
    public int delete(Integer id) throws Exception;
    
    public int update(Blog bean) throws Exception;
    
    public Blog get(Integer id) throws Exception;
    
    public Blog getByURL(String disp) throws Exception;
        
    public List<Blog> gets(Integer type, String tags, Integer index, Integer rows) throws Exception;
    
    public int getCount(Integer type, String tags) throws Exception;
    
    public int changeIndex(Integer id, Integer changeIndex) throws Exception;
    
    public int addCount(Integer id) throws Exception;
}