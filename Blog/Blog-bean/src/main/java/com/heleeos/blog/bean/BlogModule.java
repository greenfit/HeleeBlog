package com.heleeos.blog.bean;

/**
 * 博客模块.
 * 
 * @author liyu
 */
public class BlogModule {
    private Integer id;

    private String name;

    private String url;

    private String image;

    private String content;
    
    public BlogModule() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}