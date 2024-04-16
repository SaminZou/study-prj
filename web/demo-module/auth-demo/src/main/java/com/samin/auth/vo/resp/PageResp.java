package com.samin.auth.vo.resp;

import com.samin.auth.vo.base.BaseResp;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResp<T> extends BaseResp<Page<T>> {

    Integer page;
    Integer size;
    Long total;
    List<T> content = new ArrayList<>(0);

    public static <T> PageResp<T> success(Page<T> data) {
        PageResp<T> resp = new PageResp<>();
        resp.setPage(data.getNumber());
        resp.setSize(data.getSize());
        resp.setTotal(data.getTotalElements());
        resp.setContent(data.getContent());
        resp.setCode(0);
        resp.setMsg("success");
        resp.setData(null);
        return resp;
    }

    // TODO 去掉这个方法
    public static <T, E> PageResp<T> baseOf(PageResp<E> data) {
        PageResp<T> resp = new PageResp<>();
        resp.setPage(data.getPage());
        resp.setSize(data.getSize());
        resp.setTotal(data.getTotal());
        resp.setCode(0);
        resp.setMsg("success");
        resp.setData(null);
        return resp;
    }
}