package com.gavin.pojo;

import java.io.Serializable;
import java.util.List;

public class PageBean<T>  implements Serializable {
private List<T> data;
private Integer totalSize;//总记录
private Integer pageSize;//页大小
private Integer totalPage;//总页数
private Integer currentPage;//当前页

    public PageBean() {
    }

    public PageBean(List<T> data, Integer totalSize, Integer pageSize, Integer totalPage, Integer currentPage) {
        this.data = data;
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
