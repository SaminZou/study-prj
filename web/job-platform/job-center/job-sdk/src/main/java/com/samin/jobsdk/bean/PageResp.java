package com.samin.jobsdk.bean;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页响应体
 * <p>
 * Description: 分页响应体
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-04-15
 */
@Data
public class PageResp<T> extends BaseResp<Page<T>> {

    Integer page;
    Integer size;
    Long total;
    List<T> content = new ArrayList<>(0);

    /**
     * 转换返回对象
     *
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> PageResp<R> map(Function<? super T, ? extends R> mapper) {
        PageResp<R> resp = new PageResp<>();
        resp.setPage(this.getPage());
        resp.setSize(this.getSize());
        resp.setTotal(this.getTotal());
        resp.setCode(0);
        resp.setMsg("success");
        resp.setContent(this.getContent()
                .stream()
                .map(mapper)
                .collect(Collectors.toList()));
        return resp;
    }

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
}