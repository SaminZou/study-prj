package com.samin.usecase.param;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

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
