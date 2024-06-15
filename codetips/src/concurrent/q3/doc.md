# CountDownLatch 和 CyclicBarrier 的区别

CountDownLatch 基于 AQS，CyclicBarrier 底层是通过 ReentrantLock 和 Condition 实现的

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

# CountDownLatch 和 Semaphore 的区别

应用场景：**资源池、限流**

两者都是基于 AQS 实现

Semaphore: 

用途： 主要用于控制同时访问某个特定资源的线程数量，它的许可数量可以在运行时动态调整
计数： 初始化时需要指定一个许可数量，每个线程在访问资源之前需要获取许可，获取后许可数量减 1，释放许可时数量加 1
可重用： Semaphore是可重入的，许可的获取和释放可以在不同的地方多次发生

需要解决的问题：

1. 资源耗尽：使用 try finally 确保即使异常场景也能释放许可
2. 公平性问题：默认是非公平的，某些线程饥饿可能永远不会被执行
3. 性能问题：高并发场景中线程频繁获取释放许可造成过多的上下文切换和竞争，适当调整许可数量，或者换用其他并发工具