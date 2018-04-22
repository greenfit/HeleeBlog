package com.heleeos.blog.dao;

import java.util.List;

import com.heleeos.blog.dto.Blog;
import org.apache.ibatis.annotations.Param;

/**
 * 博客表的操作.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public interface BlogMapper {

    /**
     * 新增一条文章
     * @param blog 文章
     */
    int insert(Blog blog) throws Exception;

    /**
     * 更新博客内容
     * @param blog 文章
     */
    int update(Blog blog) throws Exception;

    /**
     * 获取文章
     * @param id 文章ID
     */
    Blog get(@Param("id") Integer id) throws Exception;

    /**
     * 根据URL获取文章
     * @param url url
     */
    Blog getByURL(@Param("url") String url) throws Exception;

    /**
     * 获取文章列表
     * @param type 文章类型
     * @param tags 文章标签
     * @param state 文章状态
     * @param index 开始位置
     * @param rows 获取个数
     */
    List<Blog> getList(@Param("type") Integer type, @Param("tags") String tags, @Param("state") Byte state,
                       @Param("index") Integer index, @Param("rows") Integer rows) throws Exception;

    /**
     * 获取文章的个数
     * @param type 文章类型
     * @param tags 文章标签
     * @param state 文章状态
     */
    int getCount(@Param("type") Integer type, @Param("tags") String tags, @Param("state") Byte state) throws Exception;

    /**
     * 修改文章显示顺序
     * @param id 文章ID
     * @param sortIndex 新的顺序
     */
    int changeIndex(@Param("id") Integer id, @Param("index") Byte sortIndex) throws Exception;

    /**
     * 修改文章状态
     * @param id 文章ID
     * @param newState 文章状态
     */
    int changeState(@Param("id") Integer id, @Param("state") Byte newState) throws Exception;

    /**
     * 通过URl给博客增加读取个数
     * @param url url
     */
    int addCountByUrl(@Param("url") String url) throws Exception;
}