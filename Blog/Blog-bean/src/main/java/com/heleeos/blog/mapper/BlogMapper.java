package com.heleeos.blog.mapper;

import java.util.List;

import com.heleeos.blog.bean.Blog;

public interface BlogMapper {
    
	public int insert(Blog bean);
	
	public int delete(Integer id);
    
	public int update(Blog bean);
    
	public Blog get(Integer id);
	
	public List<Blog> gets(Integer type, String tags, Integer managerId, Integer index, Integer rows);
    
	public int getCount(Integer type, String tags, Integer managerId);
    
}