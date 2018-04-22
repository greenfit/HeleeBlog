package com.heleeos.blog.bean;

import java.util.List;

/**
 * 分页
 * Created by liyu on 2018/4/22.
 */
public class PageInfo<T> {

    /** 当前页码 */
    private int page;
    /** 当前个数 */
    private int rows;
    /** 总的个数 */
    private int count;
    /** 展示对象 */
    private List<T> beans;

    public PageInfo() {}

    public PageInfo(int page, int rows, int count, List<T> beans) {
        this.page = page;
        this.rows = rows;
        this.count = count;
        this.beans = beans;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getBeans() {
        return beans;
    }

    public void setBeans(List<T> beans) {
        this.beans = beans;
    }
}
