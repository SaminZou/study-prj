package com.samin.usecase.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncUseCaseService {

  @Async
  public void test() throws InterruptedException {
    Thread.sleep(5000);
    log.info("异步方法执行");
  }
}
