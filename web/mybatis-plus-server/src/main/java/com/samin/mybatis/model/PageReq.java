package com.samin.mybatis.model;

import lombok.Data;

@Data
public class PageReq extends UserQueryVO {

    private int page = 1;

    private int size = 10;
}
