package com.samin.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@ToString
@Setter
@SuperBuilder
public class MessageBody {

    private Long msgId;

    private String text;

    private String content;

    private Long userId;
}
