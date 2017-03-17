package com.heleeos.blog.bean;

import java.util.Date;
import com.heleeos.blog.constant.BlogState;
import com.heleeos.blog.constant.ContentType;

/**
 * 博客文章.
 * 
 * @author liyu
 */
public class Blog {
    private Integer id;

    private String title;
    
    private String disp;
    
    private String summary;

    private Date time;

    private Date lasttime;

    private Integer managerid;

    private Integer typeid;

    private Integer count;
    
    private String tags;
    
    private Byte contentType;

    private Byte state;

    private Byte dispIndex;
    
    private String content;
    
    public Blog() {
        setId(0);
        setCount(0);
        setTime(new Date());
        setLasttime(new Date());
        setState(BlogState.NORMAL.getState());
        setDispIndex(new Byte("0"));
        setContentType(ContentType.HTML.getType());
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
    
    public String getDisp() {
        return disp;
    }
    
    public void setDisp(String disp) {
        this.disp = disp;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
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

    public void setDispIndex(Byte dispIndex) {
        this.dispIndex = dispIndex;
    }
    
    public Byte getDispIndex() {
        return dispIndex;
    }
    
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public void setContentType(Byte contentType) {
        this.contentType = contentType;
    }
    
    public Byte getContentType() {
        return contentType;
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