package com.heleeos.cms.mapper;

import com.heleeos.cms.bean.Manager;

public interface ManagerMapper {

    public int insert(Manager bean) throws Exception;
        
    public int update(Manager bean) throws Exception;

    public int updataState(Integer id, Byte state) throws Exception;
    
    public int updateLoginTime(Integer id) throws Exception;
    
    public Manager get(String username, String password) throws Exception;
}