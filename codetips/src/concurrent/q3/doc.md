# CountDownLatch 和 CyclicBarrier 的区别

CountDownLatch调用await()通常是主线程/调用线程，而 CyclicBarrier 调用await()是在任务线程调用的

- CountDownLatch：一个或者多个线程，等待其他多个线程完成某件事情之后才能执行；

- CyclicBarrier：多个线程互相等待，直到到达同一个同步点，再继续一起执行。

# 用例

`CountDownLatchUseCase` 和 `CyclicBarrierUseCase` 实现了同一个功能
