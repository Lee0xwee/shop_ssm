package cn.Lee.ssm.utils;

import java.util.List;


/**
 * 分页类
 */

public class PageBean<T> {

    private int pageNumber;  //当前页
    private int pageSize;    //页面大小
    private int totalPage;   //总页数
    private int totalCount;   //总记录数
    private List<T> list;      //每页记录

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
