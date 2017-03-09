package com.heleeos.blog.bean;

/**
 * 博客分类.
 * 
 * @author liyu
 */
public class BlogType {
    private Integer id;

    private String name;

    private Integer moduleid;

    private String content;
    
    public BlogType() {
        setId(0);
        setModuleid(0);
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

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}