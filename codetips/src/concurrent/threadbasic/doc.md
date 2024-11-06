# ReentrantLock 的 lock() 方法必须放在 try 块之前，是使用范式

```java
ReentrantLock lock = new ReentrantLock();
lock.lock();
try {
    // do something
} finally {
    lock.unlock();
}
```

lock() 方法不能放在 try 块里面的原因是 lock 方法执行也可能会报错，如果 lock 方法报错，也会执行 finally 里面的 unlock 方法，这样就会导致
unlock 了一个没有 lock 的对象，会报错

lock() 需要放在 try 块之前的原因是可能会导致死锁，因为 lock() 与 try 块之间的代码块可能报错导致程序中断造成死锁，代码如下：

```java
ReentrantLock lock = new ReentrantLock();
lock.lock();
// predo something
try {
    // do something
} finally {
    lock.unlock();
}
```