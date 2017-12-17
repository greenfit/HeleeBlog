package com.heleeos.blog.dao;

import com.heleeos.blog.bean.Manager;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员表的操作, t_manager.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public interface ManagerMapper {

    int insert(Manager bean) throws Exception;

    int update(Manager bean) throws Exception;

    int updateState(@Param("id") Integer id, @Param("state") Byte state) throws Exception;

    int updateLoginTime(Integer id) throws Exception;
    
    Manager get(@Param("userName") String username, @Param("passWord") String password) throws Exception;

    Manager getByToken(@Param("token") String token) throws Exception;

    int updateToken(@Param("id") int id, @Param("token") String token) throws Exception;
}