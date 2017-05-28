package com.heleeos.blog.mapper;

import java.util.List;

import com.heleeos.blog.bean.Blog;

public interface BlogMapper {
    
    int insert(Blog bean) throws Exception;
    
    int update(Blog bean) throws Exception;
    
    Blog get(Integer id) throws Exception;
    
    Blog getByURL(String url) throws Exception;
        
    List<Blog> gets(Integer type, String tags, boolean onlyNormal, Integer index, Integer rows) throws Exception;
    
    int getCount(Integer type, String tags, boolean onlyNormal) throws Exception;
    
    int changeIndex(Integer id, Integer changeIndex) throws Exception;
    
    int changeState(Integer id, Byte state) throws Exception;
    
    int addCount(Integer id) throws Exception;
    
    int addCountByUrl(String id) throws Exception;
}