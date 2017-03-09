package com.heleeos.blog.mapper;

import com.heleeos.blog.bean.Manager;

public interface ManagerMapper {

    public int insert(Manager bean);
    
    public int delete(Integer id);
    
    public int update(Manager bean);

    public Manager get(String username, String password);
}