package com.samin.jobsdk.bean;

import lombok.Data;

@Data
public class PageReq<T> {

    private int page;
    private int size;
    private T params;

    public void setPage(int page) {
        if (page > 0) {
            this.page = page - 1;
        } else {
            this.page = page;
        }
    }
}
