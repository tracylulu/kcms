package com.h3c.itrd.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 对数据集合进分页获取
 */
public class PagingList<T> {
 
    /**
     * 总页数
     */
    private int totalPage = 0;
 
    /**
     * 当前是第几页
     */
    private int curPageNo = 0;
 
    /**
     * 每页的大小
     */
    private int pageSize = 0;
 
    /**
     * 每页默认大小
     */
    private static final int DEFAULT_PAGE_SIZE = 500;
 
 
    private List<T> pageData = null;
 
    public PagingList(List<T> pageResult, int curPageNo, int pageSize) {
    	this.curPageNo = curPageNo;
        this.pageSize = pageSize;
        this.pageData = pageResult;
        init(pageResult, pageSize);
    }
 
 
    public PagingList(List<T> pageResult) {
        this(pageResult,0, DEFAULT_PAGE_SIZE);
    }
 
 
    private void init(List<T> pageResult, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Paging size must be greater than zero.");
        }
        if (null == pageResult) {
            throw new NullPointerException("Paging resource list must be not null.");
        }
        if (pageResult.size() % pageSize > 0) {
            this.totalPage = (pageResult.size() / pageSize) + 1;
        } else {
            this.totalPage = pageResult.size() / pageSize;
        }
    }
 
    /**
     * 返回当前剩余页数
     *
     * @return
     */
    private int getSurplusPage() {
        if (pageData.size() % pageSize > 0) {
            return (pageData.size() / pageSize) + 1;
        } else {
            return pageData.size() / pageSize;
        }
 
    }
 
    /**
     * 返回是否还有下一页数据
     *
     * @return
     */
    public boolean hasNext() {
        return pageData.size() > 0;
    }
 
    /**
     * 获取分页后，总的页数
     *
     * @return
     */
    public int getTotalPage() {
        return totalPage;
    }
 
    public List<T> next() {
    	List<T> pagingData = pageData.stream().sorted().skip((curPageNo - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
//        List<T> pagingData = pageData.stream().limit(pageSize).collect(Collectors.toList());
//        pageData = pageData.stream().skip((curPageNo - 1) * pageSize).collect(Collectors.toList());
        return pagingData;
    }
 
    /**
     * 返回当前页数
     *
     * @return
     */
    public int getCurPageNo() {
        return totalPage - getSurplusPage();
    }
}
