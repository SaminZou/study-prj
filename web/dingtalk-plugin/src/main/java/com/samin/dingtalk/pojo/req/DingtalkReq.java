package com.samin.dingtalk.pojo.req;

import lombok.Data;

@Data
public class DingtalkReq {

    private String msgtype;

    private Markdown markdown;

    private At at;

    @Data
    public static class At {

        private String isAtAll;
    }

    @Data
    public static class Markdown {

        private String title;
        private String text;
    }

}
