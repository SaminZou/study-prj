package com.samin.auth.vo.resp;

import com.samin.auth.entity.Menu;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class MenuResp {

    private Integer id;
    private Integer parentId;
    private String name;
    private String code;
    private Integer level;
    private Integer sort;
    private String icon;
    private String remark;
    private Integer hidden;
    private String createTime;
    private String updateTime;

    public static MenuResp getInstance(Menu menu) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        MenuResp resp = new MenuResp();

        resp.id = menu.getId();
        resp.parentId = menu.getParentId();
        resp.name = menu.getName();
        resp.code = menu.getCode();
        resp.level = menu.getLevel();
        resp.sort = menu.getSort();
        resp.icon = menu.getIcon();
        resp.remark = menu.getRemark();
        resp.hidden = menu.getHidden();
        resp.createTime = dtf.format(menu.getCreateTime());
        resp.updateTime = Objects.nonNull(menu.getUpdateTime()) ? dtf.format(menu.getUpdateTime()) : "";

        return resp;
    }
}
