package com.heleeos.blog.bean;

/**
 * 博客标签.
 * 
 * @author liyu
 */
public class BlogTag {

    private String name;

    private Integer count;
    
    public BlogTag() {
        setCount(0);
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
    
    @Override
    public String toString() {
        return '"' + getName() + '"';
    }
}