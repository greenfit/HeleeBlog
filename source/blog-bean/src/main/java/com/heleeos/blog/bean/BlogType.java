package com.heleeos.blog.bean;

/**
 * 博客分类.
 * 
 * @author liyu
 */
public class BlogType {
    private Integer id;

    private String name;
    
    private Integer count;

    public BlogType() {
        setId(0);
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
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public Integer getCount() {
        return count;
    }
}