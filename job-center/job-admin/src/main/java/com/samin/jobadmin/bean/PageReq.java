package com.samin.jobadmin.bean;

import lombok.Data;

@Data
public class PageReq {

    private int page;
    private int size;

    public void setPage(int page) {
        if (page > 0) {
            this.page = page - 1;
        } else {
            this.page = page;
        }
    }
}
