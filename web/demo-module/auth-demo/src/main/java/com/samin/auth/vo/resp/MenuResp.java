package com.samin.auth.vo.resp;

import com.samin.auth.entity.Menu;
import com.samin.auth.util.DateUtil;
import lombok.Data;

@Data
public class MenuResp {

    public static MenuResp getInstance(Menu menu) {
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
        resp.createTime = DateUtil.getDisplayTime(menu.getCreateTime());
        resp.updateTime = DateUtil.getDisplayTime(menu.getUpdateTime());

        return resp;
    }

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
}
