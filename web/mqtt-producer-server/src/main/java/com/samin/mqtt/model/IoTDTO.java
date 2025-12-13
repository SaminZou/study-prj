package com.samin.mqtt.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IoTDTO {

    @NotBlank(message = "code 不能为空")
    private String code;

    @NotBlank(message = "uuid 不能为空")
    private String uuid;

    private String params;
}
