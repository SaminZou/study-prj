package com.samin.usecase.nullvalue;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ParamDTO {

    public static ParamDTO getInstance1() {
        ParamDTO param = new ParamDTO();

        param.setDelayTime1(1);
        param.setDelayTime2(2);
        param.setDelayTime3(3);
        param.setDelayTime4(4);
        param.setDelayTime5(5);
        param.setDelayTime6(6);
        param.setDelayTime7(7);

        return param;
    }

    public static ParamDTO getInstance2() {
        ParamDTO param = new ParamDTO();
        param.setValid(0);
        return param;
    }

    private Integer delayTime1;
    private Integer delayTime2;
    private Integer delayTime3;
    private Integer delayTime4;
    private Integer delayTime5;
    private Integer delayTime6;
    private Integer delayTime7;
    private Integer valid;
}
