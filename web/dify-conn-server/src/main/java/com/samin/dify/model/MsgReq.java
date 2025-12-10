package com.samin.dify.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 消息请求对象
 */
@Data
public class MsgReq {

    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空")
    private String content;
}
