package com.heleeos.blog.bean;

/**
 * 博客标签.
 * 
 * @author liyu
 */
public class BlogTag {
    private Integer id;

    private String name;

    private Integer count;
    
    public BlogTag() {
        setId(0);
        setCount(0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}