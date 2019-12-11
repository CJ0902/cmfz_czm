package com.baizhi.czm.vo;

public class Page {
    //页号
    private int pageIndex;
    //每页的条数
    private int pageSize;
    //总页数
    private int pageCount;
    //总条数
    private int totalCount;

    //构造函数    初始化每页展示的条数为3条
    public Page() {
        this.pageSize = 3;
    }

    public Page(int pageIndex) {
        this(pageIndex, 3);
    }

    public Page(int pageIndex, int pageSize) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    /*
     * 总条数与总页数有关系
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        //换算总条数与总页数的关系
        this.pageCount = (totalCount % pageSize == 0) ? totalCount / pageSize : totalCount / pageSize + 1;
    }
    //获取当前页的起始下标
    public int getFirstResult() {
        return (pageIndex - 1) * pageSize ;
    }
    //获取当前页的最后下标
    public int getLastResult() {
        return pageIndex * pageSize-1;
    }
    //判断有没有下一页
    public boolean getHasNextPage() {
        return pageIndex < pageCount;
    }
    //判断有没有上一页
    public boolean getHasPrivousPage() {
        return pageIndex > 1;
    }
}
