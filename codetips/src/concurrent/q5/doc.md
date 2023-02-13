# 结论

1. Thread.stop() 是暴力方法，JDK 中已废弃，同类的还有 suspend 及 resume
2. 使用 interrupt 方法中断线程

# interrupt 如何进行线程停止

interrupt() 方法的使用效果并不像 for+break 语句那样，马上就停止循环。调用 interrupt 方法是在当前线程中打了一个停止标签，并不是真的停止线程

外层 interrupt() + this.interrupted() + throw new InterruptedException() 完成线程停止

interrupt() 与 return 结合使用也能实现停止线程

# interrupted() 和 isInterrupted() 的区别

interrupted() 是判断**当前**线程是否停止，并且会清除 interrupted 标签

```java
public static void main(String args[]) {
    Thread thread = new MyThread();
    thread.start();
    try {
        Thread.sleep(2000);
        thread.interrupt();
        
        // 使用对象是 thread，但实际上是判断 main 方法的线程运行情况
        System.out.println("stop 1??" + Thread.interrupted());
        System.out.println("stop 2??" + Thread.interrupted());
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

# stop()

- 暴力停止，抛出 java.lang.ThreadDeath 异常，输出 `Process finished with exit code 0`
- stop() 释放锁将会给数据造成不一致性