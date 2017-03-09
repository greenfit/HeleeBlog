package com.heleeos.blog.bean;

import java.util.Date;

/**
 * 博客文章.
 * 
 * @author liyu
 */
public class Blog {
    private Integer id;

    private String title;

    private Date time;

    private Date lasttime;

    private Integer managerid;

    private Integer typeid;

    private String tags;

    private Boolean isdelete;

    private Boolean istop;

    private String content;
    
    private Integer count;
    
    public Blog() {
        setId(0);
        setTypeid(0);
        setCount(count);
        setTime(new Date());
        setLasttime(new Date());
        setIsdelete(false);
        setIstop(false);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Boolean getIstop() {
        return istop;
    }

    public void setIstop(Boolean istop) {
        this.istop = istop;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public Integer getCount() {
        return count;
    }
}