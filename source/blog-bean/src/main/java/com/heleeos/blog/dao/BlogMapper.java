package com.heleeos.blog.dao;

import java.util.List;

import com.heleeos.blog.bean.Blog;
import org.apache.ibatis.annotations.Param;

/**
 * 博客表的操作, t_blog.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public interface BlogMapper {

    int insert(Blog blog) throws Exception;
    
    int update(Blog blog) throws Exception;
    
    Blog get(@Param("id") Integer id) throws Exception;
    
    Blog getByURL(@Param("url") String url) throws Exception;
        
    List<Blog> getList(@Param("type") Integer type, @Param("tags") String tags, @Param("state") Byte state,
                       @Param("index") Integer index, @Param("rows") Integer rows) throws Exception;
    
    int getCount(@Param("type") Integer type, @Param("tags") String tags, @Param("state") Byte state) throws Exception;
    
    int changeIndex(@Param("id") Integer id, @Param("index") Byte newIndex) throws Exception;
    
    int changeState(@Param("id") Integer id, @Param("state") Byte newState) throws Exception;
    
    int addCountByUrl(@Param("url") String url) throws Exception;
}