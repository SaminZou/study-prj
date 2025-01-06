package com.samin.usecase.beanconfig.controller;

import com.samin.usecase.beanconfig.configwithcomponet.A;
import com.samin.usecase.beanconfig.configwithcomponet.B;
import com.samin.usecase.beanconfig.configwithcomponet.C;
import com.samin.usecase.beanconfig.configwithcomponet.D;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final A a;
  private final B b;
  private final C c;
  private final D d;

  @GetMapping("/test1")
  public String test1() {
    return b.getA() == a ? "是同一个对象" : "不是同一个对象";
  }

  @GetMapping("/test2")
  public String test2() {
    return d.getC() == c ? "是同一个对象" : "不是同一个对象";
  }
}
