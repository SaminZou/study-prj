# CountDownLatch 和 CyclicBarrier 的区别

CountDownLatch 调用 await() 通常是主线程，而 CyclicBarrier 调用 await() 是在任务线程

- CountDownLatch：一个或者多个线程，等待其他多个线程完成某件事情之后才能执行；

- CyclicBarrier：多个线程互相等待，直到到达同一个同步点，再继续一起执行。

| CountDownLatch                             | CyclicBarrier        |
|:-------------------------------------------|:---------------------|
| 减计数                                        | 加计数                  |
| 计数为 0 释放等待线程                               | 计数到达指定值释放所有等待线程      |
| 计数为 0，无法重置                                 | 计数到达指定值时，计数置为 0 重新开始 |
| 调用 countDown() 方法计数减 1，调用 await() 方法对计数没影响 | 对用 await() 方法计数加 1   | 
| 不可重复利用                                     | 可重复利用                |

> 核心是调用 await() 方法的线程会被阻塞

# 用例

场景：实现几个客人一起去到酒店，人齐上菜

`CountDownLatchUseCase` 和 `CyclicBarrierUseCase` 实现了同一个功能
