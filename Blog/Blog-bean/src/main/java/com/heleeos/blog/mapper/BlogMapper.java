package com.heleeos.blog.mapper;

import java.util.List;

import com.heleeos.blog.bean.Blog;

public interface BlogMapper {
    
    public int insert(Blog bean) throws Exception;
    
    public int update(Blog bean) throws Exception;
    
    public Blog get(Integer id) throws Exception;
    
    public Blog getByURL(String url) throws Exception;
        
    public List<Blog> gets(Integer type, String tags, boolean onlyNormal, Integer index, Integer rows) throws Exception;
    
    public int getCount(Integer type, String tags, boolean onlyNormal) throws Exception;
    
    public int changeIndex(Integer id, Integer changeIndex) throws Exception;
    
    public int changeState(Integer id, Byte state) throws Exception;
    
    public int addCount(Integer id) throws Exception;
    
    public int addCountByUrl(String id) throws Exception;
}