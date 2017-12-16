package com.heleeos.blog.dao;

import com.heleeos.blog.bean.Manager;

public interface ManagerMapper {

    int insert(Manager bean) throws Exception;
        
    int update(Manager bean) throws Exception;

    int updataState(Integer id, Byte state) throws Exception;
    
    int updateLoginTime(Integer id) throws Exception;
    
    Manager get(String username, String password) throws Exception;
}