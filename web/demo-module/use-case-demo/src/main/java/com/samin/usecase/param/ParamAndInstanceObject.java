package com.samin.usecase.param;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ParamAndInstanceObject {

    @Data
    public static class ParamObj1 {

        private List<String> roleIds;
    }

    @Data
    public static class ParamObj2 {

        private List<String> roleIds = new ArrayList<>(0);
    }
}
