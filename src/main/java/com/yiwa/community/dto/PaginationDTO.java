package com.yiwa.community.dto;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 用于分页的DTO <br/>
 * 当前页码左右各自最多显示三个页码<br/>
 * Example：当前页为第四页，显示的页码最多为 |1| |2| |3| |4| |5| |6| |7|<br/>
 * 当位于第一页时hasPre=false,位于最后一页时hasNext=false<br/>
 * 当第一页不在所显示的页码中hasFront=true,当最后一页不在所显示的页码中hasEnd=true
 * */
@Component
public class PaginationDTO {
    //当前页
    private Integer page;
    //页面大小
    private Integer pageSize;
    //总数据个数
    private Integer totalCount;
    //总页面数
    private Integer pageCount;
    //显示到达地页面
    private List<Integer> pages;
    //是否有下一页
    private boolean hasNext;
    //是否有直达尾页
    private boolean hasEnd;
    //是否有前一项
    private boolean hasPre;
    //是否有直达首页
    private boolean hasFront;

    public PaginationDTO() {
    }

    public PaginationDTO(Integer page, Integer pageSize, Integer totalCount,Integer pageCount ,List<Integer> pages, boolean hasNext, boolean hasEnd, boolean hasPre, boolean hasFront) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageCount=pageCount;
        this.pages = pages;
        this.hasNext = hasNext;
        this.hasEnd = hasEnd;
        this.hasPre = hasPre;
        this.hasFront = hasFront;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount=totalCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    private void setPageCount() {
        this.pageCount=(int)Math.ceil((double)totalCount/pageSize);
    }

    public List<Integer> getPages() {
        return pages;
    }

    //计算应该显示的页码
    private void setPages() {
        pages=new LinkedList<Integer>();
        pages.add(0,page);
        for(int i=1;i<=3;i++){
            if(page-i>=1){
                pages.add(0,page-i);
            }
            if(page+i<=pageCount){
                pages.add(page+i);
            }
        }

    }

    public boolean isHasNext() {
        return hasNext;
    }

    private void setHasNext() {
        //最后一页
        this.hasNext= page != pageCount;
    }

    public boolean isHasEnd() {
        return hasEnd;
    }

    private void setHasEnd() {
        this.hasEnd=(page+3)<pageCount;
    }

    public boolean isHasPre() {
        return hasPre;
    }

    private void setHasPre() {
        this.hasPre=page!=1;
    }

    public boolean isHasFront() {
        return hasFront;
    }

    private void setHasFront() {
        this.hasFront=(page-3)>1;
    }

    //设置分页参数
    public void setPagination(){
        setPageCount();
        setPages();
        setHasNext();
        setHasEnd();
        setHasPre();
        setHasFront();
    }
}
