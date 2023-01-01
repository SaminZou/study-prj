package com.samin.dingtalk.pojo.req;

import lombok.Data;

@Data
public class DingtalkReq {

    private String msgtype;

    private Markdown markdown;

    private At at;
}
