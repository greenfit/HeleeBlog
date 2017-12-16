package com.heleeos.blog.bean;

import java.util.Date;
import com.heleeos.blog.common.BlogState;
import com.heleeos.blog.common.ContentType;

/**
 * 博客文章.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public class Blog {
    //ID
    private Integer id;
    //标题
    private String blogTitle;
    //显示的URL
    private String dispUrl;
    //摘要信息
    private String blogSummary;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //管理者ID
    private Integer managerId;
    //博客分类
    private String blogType;
    //查看次数
    private Integer readCount;
    //标签
    private String blogTags;
    //内容类型
    private Byte contentType;
    //博客内容
    private String blogContent;
    //博客状态
    private Byte blogState;

    private Byte dispIndex;
    
    public Blog() {
        setId(0);
        setReadCount(0);
        setCreateTime(new Date());
        setUpdateTime(new Date());
        setBlogState(BlogState.NORMAL.getState());
        setDispIndex(new Byte("0"));
        setContentType(ContentType.HTML.getType());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getDispUrl() {
        return dispUrl;
    }

    public void setDispUrl(String dispUrl) {
        this.dispUrl = dispUrl;
    }

    public String getBlogSummary() {
        return blogSummary;
    }

    public void setBlogSummary(String blogSummary) {
        this.blogSummary = blogSummary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(String blogTags) {
        this.blogTags = blogTags;
    }

    public Byte getContentType() {
        return contentType;
    }

    public void setContentType(Byte contentType) {
        this.contentType = contentType;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Byte getBlogState() {
        return blogState;
    }

    public void setBlogState(Byte blogState) {
        this.blogState = blogState;
    }

    public Byte getDispIndex() {
        return dispIndex;
    }

    public void setDispIndex(Byte dispIndex) {
        this.dispIndex = dispIndex;
    }
}