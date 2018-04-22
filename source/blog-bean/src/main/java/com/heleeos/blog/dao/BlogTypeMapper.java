package com.heleeos.blog.dao;

import java.util.List;
import com.heleeos.blog.dto.BlogType;
import org.apache.ibatis.annotations.Param;

/**
 * 博客分类表的操作, t_blog_type.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public interface BlogTypeMapper {

    /**
     * 新增一个文章分类
     * @param bean 文章分类
     */
    int insert(BlogType bean) throws Exception;

    /**
     * 更新文章分类
     * @param bean 文章分类
     */
    int update(BlogType bean) throws Exception;

    /**
     * 获取文章分类
     * @param id 分类ID
     */
    BlogType get(Integer id) throws Exception;

    /**
     * 获取文章分类列表
     * @param filterZero 是否显示个数为0的
     */
    List<BlogType> getList(@Param("filterZero") boolean filterZero) throws Exception;
}