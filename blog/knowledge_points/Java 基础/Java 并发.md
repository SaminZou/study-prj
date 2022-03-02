```yaml
title: Java 并发
author: samin
date: 2022-03-02
```

# 多线程
## 多线程应用场景

<u>只有乱序执行的代码才有必要设计为多线程</u>
- 日志处理
- 分布式计算
- 定时的批量任务
- servlet

## 程序、进程和线程

**程序**是一个指令序列

**进程**是程序的一次执行过程，是系统运行程序的基本单位，因此进程是动态的。系统运行一个程序即是一个进程从创建，运行到消亡的过程。
在 Java 中，当我们启动 main 函数时其实就是启动了一个 JVM 的进程，而 main 函数所在的线程就是这个进程中的一个线程，也称主线程。

**线程**与进程相似，但线程是一个比进程更小的执行单位。一个进程在其执行的过程中可以产生多个线程。与进程不同的是同类的多个**线程共享进程的堆和方法区资源**，但每个线程有自己的**程序计数器、虚拟机栈和本地方法栈**，所以系统在产生一个线程，或是在各个线程之间作切换工作时，负担要比进程小得多，也正因为如此，线程也被称为轻量级进程。

线程执行开销小，但不利于资源的管理和保护。

## 程序计数器、虚拟机栈、本地方法栈为什么私有？

- 程序计数器私有：为了线程切换后能恢复到正确的执行位置。
- 虚拟机栈、本地方法栈私有：为了保证线程中的局部变量不被别的线程访问到。
  PS：虚拟机栈为Java方法（字节码）服务，本地方法栈为Native方法服务，在HotSpot中Java虚拟机栈合二为一

## 并发与并行

- 并发：同一时间段，多个任务都在执行（单位时间内不一定同时执行）
- 并行：单位时间内，多个任务同时执行

## 为什么要用多线程？

- 从计算机底层来说：线程是程序执行的最小单位，切换调度成本小于进程。多核CPU意味着多个线程可以同时运行，减少了线程上下文切换的开销。
    - 单核时代：多线程用来提供CPU和IO设备的综合利用率。
    - 多核时代：提供CPU的利用率。一个线程只会利用到一个CPU核心，多个线程可以让多个CPU核心被利用到。
- 从当代互联网发展趋势来说：多线程并发编程是高并发系统的基础，多线程机制可以提高系统整体的并发能力和性能

## 应用场景

- CPU密集型程序（上下文切换频繁）
    - 实例：如计算1加到1亿
    - 创建线程数量，理论上是：`线程数量 = CPU 核数（逻辑）`，实际上是`线程数量 = CPU 核数（逻辑）+1`可以发挥最佳性能，防止某个时刻有一个线程或错误或暂停
- IO密集型程序（数据拷贝）
    - 单核CPU创建线程数量，`最佳线程数 = (1 / CPU利用率) = 1 + (I/O耗时 / CPU耗时)`
    - 多核CPU创建线程数量，`最佳线程数 = CPU核心数 * (1 / CPU利用率)  = CPU核心数 * 1 + (I/O耗时 / CPU耗时)`

> 给定一颗CPU核心，其顺序执行A和B永远比通过时间分片“同时”执行A和B要快，这是一条计算机科学的基本法则。**一旦线程的数量超过了CPU核心的数量，再增加线程数系统就只会更慢，而不是更快。**

## 多线程可能导致的问题

- 死锁
- 线程不安全（造成脏数据）
- 内存泄露

## 线程的生命周期和状态

|状态名称|说明|
| --- | --- |
|NEW|初始状态，线程被创建，还没有调用start()方法|
|RUNNABLE|运行状态，Java线程将操作系统中的就绪和运行两种状态笼统地称为“运行中”|
|BLOCKED|阻塞状态，表示线程阻塞于锁|
|WAITING|等待状态，表示线程进入等待状态，进入该状态表示当前线程需要等待其他线程做出一些特定动作（通知或中断）|
|TIME_WAITING|超时等待状态，该状态不同于WAITING，它是可以在指定的时间自行返回的|
|TERMINATED|终止状态，表示当前线程已经执行完毕|

## 什么是上下文切换

CPU核心在任意时刻只能被一个线程使用，CPU采取的策略是为每个线程分配时间片并轮转的形式。当一个线程的时间片用完，就会重新处于就绪状态让给其他线程使用，这个过程就属于一次上下文切换。

**当前任务在执行完 CPU 时间片切换到另一个任务之前会先保存自己的状态，以便下次再切换回这个任务时，可以再加载这个任务的状态。任务从保存到再加载的过程就是一次上下文切换。**

上下文切换对系统来说意味着消耗大量的 CPU 时间，事实上，可能是操作系统中时间消耗最大的操作。

Linux 相比与其他操作系统（包括其他类 Unix 系统）有很多的优点，其中有一项就是，其上下文切换和模式切换的时间消耗非常少。

## 死锁问题

### 认识线程死锁

线程死锁描述的是这样一种情况：多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞，因此程序不可能正常终止。

产生死锁必须具备以下四个条件：
1. 互斥条件：该资源任意一个时刻只由一个线程占用。
2. 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
3. 不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
4. 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。

### 如何避免线程死锁

1. 破坏互斥条件 ：这个条件我们没有办法破坏，因为我们用锁本来就是想让他们互斥的（临界资源需要互斥访问）。
2. 破坏请求与保持条件 ：一次性申请所有的资源。
3. 破坏不剥夺条件 ：占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
4. 破坏循环等待条件 ：靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。

## sleep()和wait()方法区别和共同点

- 两者最主要的区别在于：sleep 方法没有释放锁，而 wait 方法释放了锁 。
- 两者都可以暂停线程的执行。
- Wait 通常被用于线程间交互/通信，sleep 通常被用于暂停执行。
- wait() 方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的 notify() 或者 notifyAll() 方法，或者可以使用 wait(long timeout)超时后线程会自动苏醒。sleep() 方法执行完成后，线程会自动苏醒。

## 为什么我们调用start()方法时会执行run()方法，为什么不能直接调用run()方法？

总结： 调用 start 方法方可启动线程并使线程进入就绪状态，而 run 方法只是 thread 的一个普通方法调用，还是在主线程里执行。

# Java的多线程

## 对synchronized关键字的了解

synchronized关键字是解决多个线程之间访问资源的同步性，可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行。

## 自己是怎么使用synchronized关键字，在项目中用到了吗

- 修饰实例方法：当前对象实例加锁
- 修饰静态方法：当前类加锁
- 修饰代码块：指定加锁对象

synchronized加到static静态方法和synchronized(class)代码上都是给**Class类**上锁，synchronized加到实例方法上是给**对象实例**上锁。

双重校验锁实现对象单例（线程安全）

## 讲一下synchronized关键字的底层原理

synchronized关键字底层原理属于JVM层面

1. synchronized 同步代码块的情况
```
public class SynchronizedDemo{
    public void method() {
        synchronized (this) {
            System.out.println("代码块加锁");
        }
    }
}
```

查看字节码文件，会看到对应代码编译出来前后会有"monitorenter"和"monitorexit"指令记录代码开始和结束位置。每个Java对象头中有monitor对象，用于获取锁。当计数器为0则可以获取成功，获取后计数器计为1，执行monitorexit指令后，将锁计数器设为0，表名锁失败。获取失败则阻塞等待，知道锁被另一个线程释放为止。

2. synchronized 修饰方法的情况

```
public class SynchronizedDemo {
    public synchronized void method () {
        System.out.println("方法加锁");
    }
}
```
字节码的方法中用"ACC_SYNCHNIZED"来标识该方法同步加锁。

## JDK1.6之后的synchronized关键字底层做了哪些优化？

JDK1.6对锁的实现引入了大量的优化，如偏向锁、轻量级锁、适应自旋锁、锁消除、锁粗化等技术来减少锁操作的开销。

锁主要四种状态，依次是：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，他们会随着竞争的激烈而逐渐升级。注意锁可以升级不可以降级，这种策略是为了提高获得锁和释放锁的效率。

## as-if-serial 规则和happens-before 规则
1. as-if-serial语义保证单线程内程序的执行结果不被改变，happens-before关系保证正确同步的多线程程序的执行结果不被改变。
2. as-if-serial语义给编写单线程程序的程序员创造了一个幻境：单线程程序是按程序的顺序来执行的。happens-before关系给编写正确同步的多线程程序的程序员创造了一个幻境：正确同步的多线程程序是按happens-before指定的顺序来执行的。
3. as-if-serial语义和happens-before这么做的目的，都是为了在不改变程序执行结果的前提下，尽可能地提高程序执行的并行度。

## wait和notify使用注意事项

1. 永远在synchronized的函数或者对象里使用wait、notify和notifyAll，不然Java虚拟机会生成lllegalMonitorStateException
2. 永远在while循环里而不是if语句下使用wait。这样循环会在线程睡眠前后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知
3. 永远在多线程间共享的对象上使用wait
4. notify随机通知一个阻塞在对象上的线程；notifyAll通知阻塞在对象上所有的线程

## 谈谈synchronized和ReentrantLock的区别

1. 两者都是可重入锁（不可锁重入会造成死锁）

“可重入锁”概念是：针对同一个对象或者类，自己可以再次获取自己的内部锁。（一个线程获得了锁之后仍然可以反复的加锁，不会出现自己阻塞自己的情况）

> 如 `A` 类有`a( )`方法和`b( )`方法，都是使用了synchronized上锁，那么同时调用的时候，都可以正常执行。不可重入锁同时执行两个方法会产生死锁。

2. synchronized依赖于JVM而ReentrantLock依赖于API

synchronized是依赖于JVM实现的。ReentrantLock是JDK层面实现的（也就是API层面，需要lock()和unlock()方法配合try/finally语句块来完成）。

3. ReentrantLock比synchronized增加了一些高级功能

    1. 等待可中断；
    2. 实现公平锁；
       默认是非公平的，可以通过ReentrantLock(boolean fair)构造方法来制定是否是公平的。
    3. 可实现选择性通知（锁可以绑定多个条件）
        - synchronized关键字与wait()和notify()/notifyAll()方法相结合可以实现等待/通知机制
        - ReentrantLock类借助于Condition接口（JDK1.5以后）与newCondition()方法，具有很好的灵活性，可以实现多路通知功能（在一个Lock对象中可以创建多个Condition实例（对象监视器））
        - 线程对象可以注册在指定的Condition中，从而可以有选择性的进行线程通知，在调度线程上更加灵活。
        - 在使用notify()/notifyAll()方法进行通知时，被通知的线程是由JVM选择的，用ReentrantLock类结合Condition实例可以实现“选择性通知”

4. 性能已不是选择标准

## volatile关键字

### JMM（Java内存模型）

在JDK1.2之前，Java的内存模型实现总是从主存（共享内存）读取变量，不需要特别的注意。而之后的Java内存模型，线程可以把变量保存本地内存（比如机器的寄存器）中，不是直接读取主存。当一个线程在主存修改了一个变量的值，而另一个线程还继续使用它在寄存器中的变量值的拷贝，造成数据的不一致
把变量声明为volatile，就指示JVM，这个变量不是稳定的，每次使用它都到主存中进行读取。
volatile的主要作用就是保证变量的可见性以及防止指令重排序。

### 并发编程的三个重要特性

1. **原子性**：一个操作或多次操作，要么所有操作全部执行并且不会受到任何因素的干扰而中断，要么所有操作都执行，要么都不执行。synchronized可以保证代码片段的原子性。
   如修饰 long，double
   注意 i++，用volatile修饰了i，但是i++不具备原子性
2. **可见性**：当一个变量对共享内存进行了修改，那么另外的线程都是立即可以看到修改后的最新值。volatile关键字可以保证共享变量的可见性。
   Happenes-before 前面的变量值对后面变量是可见的
3. **有序性（禁止重排序）**：代码在执行的过程中的先后顺序，Java在编译器以及运行期间的优化，代码的执行顺序未必就是编写代码时候的顺序。volatile关键字可以禁止指令进行重排序优化。
   单个线程讨论重排序，毫无意义！单个单线重排序毫无影响

CPU指令-内存屏障（StoreStore、StoreLoad、LoadLoad、LoadStore）

> 内存屏障（英语：Memory barrier），也称内存栅栏，内存栅障，屏障指令等，是一类同步屏障指令，它使得 CPU 或编译器在对内存进行操作的时候, 严格按照一定的顺序来执行, 也就是说在memory barrier 之前的指令和memory barrier之后的指令不会由于系统优化等原因而导致乱序。
> 大多数现代计算机为了提高性能而采取乱序执行，这使得内存屏障成为必须。
> 语义上，内存屏障之前的所有写操作都要写入内存；内存屏障之后的读操作都可以获得同步屏障之前的写操作的结果。因此，对于敏感的程序块，写操作之后、读操作之前可以插入内存屏障。

#### volatile vs synchronized

1. 修饰范围

volatile 修饰变量
synchronized 修饰方法、代码块

2. 内存可见性

volatile 写 对应 锁释放
volatile 读 对应 锁获取

3. 原子性

volatile 一次操作是原子性
synchronized 加锁的地方是原子性的

### 说说synchronized关键字和volatile关键字的区别

synchronized关键字和volatile关键字是互补关系

- volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好。但是volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块
- 多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞
- volatile关键字能保证数据的可见性，但不能保证数据的原子性，synchronized两者都可以保证
- volatile关键字主要用于解决变量在多个线程之间的可见性，synchronized解决的是多个线程之间访问资源的同步性

## ThreadLocal

### 简介

通常情况下，创建的变量是可以被任何一个线程访问并修改的，如果想实现每一个线程都有自己的专属本地变量，可以使用JDK提供的ThreadLocal类，主要解决的就是让每个线程绑定自己的值，可以将ThreadLocal类形象的比喻成存放数据的盒子，盒子中可以存储每个线程的私有数据

如果创建了一个ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的本地副本，这也是ThreadLocal变量名的由来。

使用 **get()** 和 **set()** 方法来获取默认值或将其值更改为当前线程所存的副本的值，从而避免了线程安全问题。

### 原理

- Thread类中，有两个ThreadLocalMap类型的变量threadLocals和inheritableThreadLocals
- threadLocals用于ThreadLocal保存公共的值，inheritableThreadLocals用于当前Thread保存当前线程的值，互相不影响
- ThreadLocalMap可以理解为ThreadLocal类定制化的HashMap
- threadLocals和inheritableThreadLocals默认是null，调用ThreadLocal的set或get方法才创建实例
- 本质上是调用ThreadLocalMap类对应的get()、set()方法
- 最终的变量是放在了ThreadLocal封装的ThreadLocalMap中
- 每个Thread中都有一个ThreadLocalMap，ThreadLocalMap可以存储以ThreadLocal为key，Object对象为value的键值对

### ThreadLocal内存泄露问题

ThreadLocalMap中使用的key为弱引用，value是强引用。所以ThreadLocal在没有被外部强引用的情况下，GC时key会被清理，value不会被清理。所以ThreadLocalMap中会出现key为null的Entry。不做措施的情况下，value永远无法被GC回收，从而产生内存泄漏。所以使用完ThreadLocal方法后最后手动调用remove()方法。

## 线程池

### 线程池常问问题

- 线程池有什么参数
- JDK内置线程池有哪些
- 这些内置线程池在使用的时候有哪些局限

### 为什么要用线程池

> 池化技术相比大家已经屡见不鲜了，**线程池、数据库连接池、Http 连接池**等等都是对这个思想的应用。池化技术的思想主要是为了减少每次获取资源的消耗，提高对资源的利用率。

- 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
- 提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。
- 提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。
- 有助于this逃逸问题。
> this 逃逸是指在构造函数返回之前其他线程就持有该对象的引用. 调用尚未构造完全的对象的方法可能引发令人疑惑的错误。

### Executor 框架结构(主要由三大部分组成)

1. 任务(Runnable /Callable)

执行任务需要实现的 `Runnable` 接口 或 `Callable` 接口。`Runnable` 接口或 `Callable` 接口 实现类都可以被 `ThreadPoolExecutor` 或 `ScheduledThreadPoolExecutor` 执行。

2. 任务的执行(Executor)

`ThreadPoolExecutor` 和 `ScheduledThreadPoolExecutor` 这两个关键类实现了 ExecutorService 接口。

这里提了很多底层的类关系，但是，实际上我们需要更多关注的是 `ThreadPoolExecutor` 这个类，这个类在我们实际使用线程池的过程中，使用频率还是非常高的。

> 注意： 通过查看 ScheduledThreadPoolExecutor 源代码我们发现 ScheduledThreadPoolExecutor 实际上是继承了 ThreadPoolExecutor 并实现了 ScheduledExecutorService ，而 ScheduledExecutorService 又实现了 ExecutorService，正如我们下面给出的类关系图显示的一样。

3) 异步计算的结果(Future)
   `Future` 接口以及 `Future` 接口的实现类 `FutureTask` 类都可以代表异步计算的结果。

当我们把 `Runnable` 接口 或 `Callable` 接口 的实现类提交给 `ThreadPoolExecutor` 或 `ScheduledThreadPoolExecutor` 执行。（调用 submit() 方法时会返回一个 FutureTask 对象）

### 线程创建的方法

- 声明线程时，实现Runnable和继承Thread，基本都是采用实现Runnable，优势如下：
1. 避免点继承的局限，一个类可以继承多个接口。
2. 适合于资源的共享
3. 增加程序的健壮性，代码可以被多个线程共享，代码和数据独立

- 实现Runnable接口和Callable接口的区别

1. Runnable接口**不会返回结果或抛出检查异常**，Callable接口可以。
2. 不需要返回结果或抛出异常推荐使用Runnable接口，代码看起来会简洁一些。

### 执行execute()方法和submit()方法的区别是什么呢？

- execute()方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功与否；
- submit()方法用于提交需要返回值的任务。线程池会返回一个 Future 类型的对象，通过这个 Future 对象可以判断任务是否执行成功，Future的get()方法会阻塞当前线程直到任务完成，使用 get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。


### shutdown()和shutdownNow()的区别

- shutdown（） :关闭线程池，线程池的状态变为 SHUTDOWN。线程池不再接受新任务了，但是队列里的任务得执行完毕。

- shutdownNow（） :关闭线程池，线程的状态变为 STOP。线程池会终止当前正在运行的任务，并停止处理排队的任务并返回正在等待执行的 List。

### isTerminated()和isShutdown()的区别

- isShutDown 当调用 shutdown() 方法后返回为 true。

- isTerminated 当调用 shutdown() 方法后，并且所有提交的任务完成后返回为 true

### 如何创建线程池

尽量避免使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险

> Executors 返回线程池对象的弊端如下：
> - FixedThreadPool 和 SingleThreadExecutor ： 允许请求的队列长度为 Integer.MAX_VALUE ，可能堆积大量的请求，从而导致OOM。
> - CachedThreadPool 和 ScheduledThreadPool ： 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM。

- 通过 ThreadPoolExecutor 构造方法实现
- 通过 Executor 框架的工具类 Executors 来实现 可以创建三种类型的ThreadPoolExecutor

    - FixedThreadPool ： 该方法返回一个固定线程数量的线程池。该线程池中的线程数量始终不变。当有一个新的任务提交时，线程池中若有空闲线程，则立即执行。若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务。
    - SingleThreadExecutor： 方法返回一个只有一个线程的线程池。若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，待线程空闲，按先入先出的顺序执行队列中的任务。
    - CachedThreadPool： 该方法返回一个可根据实际情况调整线程数量的线程池。线程池的线程数量不确定，但若有空闲线程可以复用，则会优先使用可复用的线程。若所有线程均在工作，又有新的任务提交，则会创建新的线程处理任务。所有线程在当前任务执行完毕后，将返回线程池进行复用。

### ThreadPoolExecutor 类分析

ThreadPoolExecutor 类中提供的四个构造方法。我们来看最长的那个，其余三个都是在这个构造方法的基础上产生（其他几个构造方法说白点都是给定某些默认参数的构造方法比如默认制定拒绝策略是什么）

```
/**
 * 用给定的初始参数创建一个新的ThreadPoolExecutor。
 */
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```

#### ThreadPoolExecutor构造函数重要参数分析

- ThreadPoolExecutor 3 个最重要的参数：
    - corePoolSize : 核心线程数线程数定义了最小可以同时运行的线程数量。
    - maximumPoolSize : 当队列中存放的任务达到队列容量的时候，当前可以同时运行的线程数量变为最大线程数。
    - workQueue: 当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，新任务就会被存放在队列中。

- ThreadPoolExecutor其他常见参数:
    - keepAliveTime : 当线程池中的线程数量大于 corePoolSize 的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了 keepAliveTime才会被回收销毁；
    - unit : keepAliveTime 参数的时间单位。
    - threadFactory : executor 创建新线程的时候会用到。
    - handler : 饱和策略。关于饱和策略下面单独介绍一下。

#### ThreadPoolExecutor 饱和策略

##### 定义

如果当前同时运行的线程数量达到最大线程数量并且队列也已经被放满，ThreadPoolTaskExecutor 定义一些策略

##### 具体策略

- ThreadPoolExecutor.AbortPolicy：抛出 RejectedExecutionException 来拒绝新任务的处理。
- ThreadPoolExecutor.CallerRunsPolicy：这种策略会降低对于新任务提交速度，影响程序的整体性能。另外，这个策略喜欢增加队列容量。如果应用程序可以承受此延迟并且你不能任务丢弃任何一个任务请求的话，可以选择这个策略。
- ThreadPoolExecutor.DiscardPolicy： 不处理新任务，直接丢弃掉。
- ThreadPoolExecutor.DiscardOldestPolicy： 此策略将丢弃最早的未处理的任务请求。

当我们不指定 RejectedExecutionHandler 饱和策略的话来配置线程池的时候默认使用的是 ThreadPoolExecutor.AbortPolicy。
对于可伸缩的应用程序，建议使用 ThreadPoolExecutor.CallerRunsPolicy。当最大池被填满时，此策略为我们提供可伸缩队列。

### 一个简单的线程池Demo: Runnable+ThreadPoolExecutor

```
import java.util.Date;

/**
 * 这是一个简单的Runnable类，需要大约5秒钟来执行其任务
 */
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
```

```
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        // 使用阿里巴巴推荐的创建线程池的方式
        // 通过ThreadPoolExecutor构造函数自定义参数创建
        // corePoolSize: 核心线程数为 5
        // maximumPoolSize ：最大线程数 10
        // keepAliveTime : 等待时间为 1L
        // unit: 等待时间的单位为 TimeUnit.SECONDS
        // workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100
        // handler:饱和策略为 CallerRunsPolicy
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(worker);
        }

        // 终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
```

### 线程池原理分析

上述demo代码，线程池每次会同时执行 5 个任务，这 5 个任务执行完之后，剩余的 5 个任务才会被执行。
原因是：我们在代码中模拟了 10 个任务，我们配置的核心线程数为 5 、等待队列容量为 100 ，所以每次只可能存在 5 个任务同时执行，剩下的 5 个任务会被放到等待队列中去。当前的 5 个任务之行完成后，才会之行剩下的 5 个任务。

### 常见线程池详解

#### FixedThreadPool

`FixedThreadPool` 使用无界队列 `LinkedBlockingQueue`（队列的容量为 `Intger.MAX_VALUE`）作为线程池的工作队列会对线程池带来如下影响 ：

- 当线程池中的线程数达到 `corePoolSize` 后，新任务将在无界队列中等待，因此线程池中的线程数不会超过 `corePoolSize`；
- 由于使用无界队列时 `maximumPoolSize` 将是一个无效参数，因为不可能存在任务队列满的情况。所以，通过创建 `FixedThreadPool` 的源码可以看出创建的 `FixedThreadPool` 的 `corePoolSize` 和 `maximumPoolSize` 被设置为同一个值。
- 由于 1 和 2，使用无界队列时 `keepAliveTime` 将是一个无效参数；
- 运行中的 `FixedThreadPool`（未执行 shutdown()或 shutdownNow()）不会拒绝任务，在任务比较多的时候会导致 OOM（内存溢出）。

#### SingleThreadExecutor

`SingleThreadExecutor` 是只有一个线程的线程池，`corePoolSize` 和 `maximumPoolSize` 都被设置为 1.其他参数和 `FixedThreadPool` 相同

- 如果当前运行的线程数少于 `corePoolSize`，则创建一个新的线程执行任务；
- 当前线程池中有一个运行的线程后，将任务加入 `LinkedBlockingQueue`；
- 线程执行完当前的任务后，会在循环中反复从 `LinkedBlockingQueue` 中获取任务来执行；
- `SingleThreadExecutor` 使用无界队列 `LinkedBlockingQueue` 作为线程池的工作队列（队列的容量为 `Intger.MAX_VALUE`）。`SingleThreadExecutor` 使用无界队列作为线程池的工作队列会对线程池带来的影响与 `FixedThreadPool` 相同。说简单点就是可能会导致 OOM

#### CachedThreadPool

`CachedThreadPool` 的 `corePoolSize` 被设置为空（0），`maximumPoolSize` 被设置为 `Integer.MAX.VALUE`，即它是无界的，这也就意味着如果主线程提交任务的速度高于 `maximumPool` 中线程处理任务的速度时，`CachedThreadPool` 会不断创建新的线程。极端情况下，这样会导致耗尽 cpu 和内存资源

- 创建的线程数量为 `Integer.MAX_VALUE` ，可能会创建大量线程，从而导致 OOM

### ScheduledThreadPoolExecutor

主要用来在给定的延迟后运行任务，或者定期执行任务，这个在实际项目中基本不会被用到，因为有其他方案选择比如quartz

#### ScheduledThreadPoolExecutor 和 Timer 的比较：

- `Timer` 对系统时钟的变化敏感，`ScheduledThreadPoolExecutor`不是；
- `Timer` 只有一个执行线程，因此长时间运行的任务可以延迟其他任务。 `ScheduledThreadPoolExecutor` 可以配置任意数量的线程。 此外，如果你想（通过提供 `ThreadFactory`），你可以完全控制创建的线程;
- 在 `TimerTask` 中抛出的运行时异常会杀死一个线程，从而导致 `Timer` 死机 ... 即计划任务将不再运行。`ScheduledThreadExecutor` 不仅捕获运行时异常，还允许您在需要时处理它们（通过重写 `afterExecute` 方法`ThreadPoolExecutor`）。抛出异常的任务将被取消，但其他任务将继续运行

综上，在 JDK1.5 之后，你没有理由再使用 Timer 进行任务调度了。

> 备注：`Quartz` 是一个由 java 编写的任务调度库，由 `OpenSymphony` 组织开源出来。在实际项目开发中使用 `Quartz` 的还是居多，比较推荐使用 `Quartz`。因为 `Quartz` 理论上能够同时对上万个任务进行调度，拥有丰富的功能特性，包括任务调度、任务持久化、可集群化、插件等等。

### 线程池大小的确定

线程池数量的确定一直是困扰着程序员的一个难题，大部分程序员在设定线程池大小的时候就是随心而定。

很多人甚至可能都会觉得把线程池配置过大一点比较好！我觉得这明显是有问题的。就拿我们生活中非常常见的一例子来说：**并不是人多就能把事情做好，增加了沟通交流成本。你本来一件事情只需要 3 个人做，你硬是拉来了 6 个人，会提升做事效率嘛？** 我想并不会。 线程数量过多的影响也是和我们分配多少人做事情一样，对于多线程这个场景来说主要是增加了上下文切换成本。不清楚什么是上下文切换的话，可以看我下面的介绍。

> 上下文切换：
>
> 多线程编程中一般线程的个数都大于 CPU 核心的个数，而一个 CPU 核心在任意时刻只能被一个线程使用，为了让这些线程都能得到有效执行，CPU 采取的策略是为每个线程分配时间片并轮转的形式。当一个线程的时间片用完的时候就会重新处于就绪状态让给其他线程使用，这个过程就属于一次上下文切换。概括来说就是：当前任务在执行完 CPU 时间片切换到另一个任务之前会先保存自己的状态，以便下次再切换回这个任务时，可以再加载这个任务的状态。**任务从保存到再加载的过程就是一次上下文切换。**
>
> 上下文切换通常是计算密集型的。也就是说，它需要相当可观的处理器时间，在每秒几十上百次的切换中，每次切换都需要纳秒量级的时间。所以，上下文切换对系统来说意味着消耗大量的 CPU 时间，事实上，可能是操作系统中时间消耗最大的操作。
>
> Linux 相比与其他操作系统（包括其他类 Unix 系统）有很多的优点，其中有一项就是，其上下文切换和模式切换的时间消耗非常少。

**类比于实现世界中的人类通过合作做某件事情，我们可以肯定的一点是线程池大小设置过大或者过小都会有问题，合适的才是最好。**

**如果我们设置的线程池数量太小的话，如果同一时间有大量任务/请求需要处理，可能会导致大量的请求/任务在任务队列中排队等待执行，甚至会出现任务队列满了之后任务/请求无法处理的情况，或者大量任务堆积在任务队列导致 OOM。这样很明显是有问题的！ CPU 根本没有得到充分利用。**

**但是，如果我们设置线程数量太大，大量线程可能会同时在争取 CPU 资源，这样会导致大量的上下文切换，从而增加线程的执行时间，影响了整体执行效率。**

有一个简单并且适用面比较广的公式：

- CPU 密集型任务(N+1)： 这种任务消耗的主要是 CPU 资源，可以将线程数设置为 N（CPU 核心数）+1，比 CPU 核心数多出来的一个线程是为了防止线程偶发的缺页中断，或者其它原因导致的任务暂停而带来的影响。一旦任务暂停，CPU 就会处于空闲状态，而在这种情况下多出来的一个线程就可以充分利用 CPU 的空闲时间。
- I/O 密集型任务(2N)： 这种任务应用起来，系统会用大部分的时间来处理 I/O 交互，而线程在处理 I/O 的时间段内不会占用 CPU 来处理，这时就可以将 CPU 交出给其它线程使用。因此在 I/O 密集型任务的应用中，我们可以多配置一些线程，具体的计算方法是 2N。

**如何判断是 CPU 密集任务还是 IO 密集任务？**

CPU 密集型简单理解就是利用 CPU 计算能力的任务比如你在内存中对大量数据进行排序。单凡涉及到网络读取，文件读取这类都是 IO 密集型，这类任务的特点是 CPU 计算耗费时间相比于等待 IO 操作完成的时间来说很少，大部分时间都花在了等待 IO 操作完成上。

## Atomic原子类

### 介绍一下Atomic 原子类

这里 Atomic 是指一个操作是不可中断的。即使是在多个线程一起执行的时候，一个操作一旦开始，就不会被其他线程干扰。

所谓原子类说简单点就是具有原子/原子操作特征的类。并发包 java.util.concurrent 的原子类都存放在java.util.concurrent.atomic下

### JUC 包中的原子类是哪4类?

- 基本类型（使用原子的方式更新基本类型）

    - AtomicInteger：整形原子类
    - AtomicLong：长整型原子类
    - AtomicBoolean：布尔型原子类

- 数组类型（使用原子的方式更新数组里的某个元素）

    - AtomicIntegerArray：整形数组原子类
    - AtomicLongArray：长整形数组原子类
    - AtomicReferenceArray：引用类型数组原子类

- 引用类型

    - AtomicReference：引用类型原子类
    - AtomicStampedReference：原子更新引用类型里的字段原子类
    - AtomicMarkableReference ：原子更新带有标记位的引用类型

- 对象的属性修改类型

    - AtomicIntegerFieldUpdater：原子更新整形字段的更新器
    - AtomicLongFieldUpdater：原子更新长整形字段的更新器
    - AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。

### AtomicInteger 类常用方法

```
public final int get()  // 获取当前的值
public final int getAndSet(int newValue) // 获取当前的值，并设置新的值
public final int getAndIncrement() // 获取当前的值，并自增
public final int getAndDecrement()  // 获取当前的值，并自减
public final int getAndAdd(int delta) // 获取当前的值，并加上预期的值
boolean compareAndSet(int expect, int update)  // 如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
public final void lazySet(int newValue) // 最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
```

使用 AtomicInteger 的方法不用对方法加锁也可以保证线程安全

### 介绍一下 AtomicInteger 类的原理

AtomicInteger 类主要利用 CAS (compare and swap) + volatile 和 native 方法来保证原子操作，从而避免 synchronized 的高开销，执行效率大为提升。

CAS的原理是拿期望的值和原本的一个值作比较，如果相同则更新成新的值。UnSafe 类的 objectFieldOffset() 方法是一个本地方法，这个方法是用来拿到“原来的值”的内存地址，返回值是 valueOffset。另外 value 是一个volatile变量，在内存中可见，因此 JVM 可以保证任何时刻任何线程总能拿到该变量的最新值。

## AQS

### 介绍

- AQS全称为（AbstractQueuedSynchronizer），在java.util.concurrent.locks包下面
- 构建锁和同步器的框架，使用AQS能简单且高效地构造出应用广泛的大量的同步器，比如ReentrantLock，Semaphore
- ReentrantReadWriteLock，SynchronousQueue，FutureTask都是基于AQS
- 我们自己也能利用AQS构造符合我们自己需求的同步器

### 原理分析

#### 原理概览

AQS核心思想是，如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制AQS是用CLH队列锁实现的，即将暂时获取不到锁的线程加入到队列中。


>CLH(Craig,Landin,and Hagersten)队列是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）。AQS是将每条请求共享资源的线程封装成一个CLH锁队列的一个结点（Node）来实现锁的分配。

- AQS使用一个int成员变量来表示同步状态，通过内置的FIFO队列来完成获取资源线程的排队工作。AQS使用CAS对该同步状态进行原子操作实现对其值的修改。
```
private volatile int state;//共享变量，使用volatile修饰保证线程可见性
```
- 状态信息通过protected类型的getState，setState，compareAndSetState进行操作
```
//返回同步状态的当前值 
protected final int getState() { 
        return state; 
} 

// 设置同步状态的值 
protected final void setState(int newState) {
        state = newState; 
} 

//原子地（CAS操作）将同步状态值设置为给定值update如果当前同步状态的值等于expect（期望值） 
protected final boolean compareAndSetState(int expect, int update) { 
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update); 
}
```

#### AQS对资源的共享方式

1. Exclusive（独占）：只有一个线程能执行，如ReentrantLock。又可分为公平锁和非公平锁：
   公平锁：按照线程在队列中的排队顺序，先到者先拿到锁非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的
2. Share（共享）：多个线程可同时执行，如Semaphore/CountDownLatch。Semaphore、CountDownLatch、 CyclicBarrier、ReadWriteLock 我们都会在后面讲到。

ReentrantReadWriteLock 可以看成是组合式，因为ReentrantReadWriteLock也就是读写锁允许多个线程同时对某一资源进行读。

不同的自定义同步器争用共享资源的方式也不同。自定义同步器在实现时只需要实现共享资源 state 的获取与释放方式即可，至于具体线程等待队列的维护（如获取资源失败入队/唤醒出队等），AQS已经在顶层实现好了。

#### AQS底层使用了模板方法模式

同步器的设计是基于模板方法模式的，如果需要自定义同步器一般的方式是这样（模板方法模式很经典的一个应用）：

1. 使用者继承AbstractQueuedSynchronizer并重写指定的方法。（这些重写方法很简单，无非是对于共享资源state的获取和释放）
2. 将AQS组合在自定义同步组件的实现中，并调用其模板方法，而这些模板方法会调用使用者重写的方法。

这和我们以往**通过实现接口的方式有很大区别**，这是模板方法模式很经典的一个运用。

AQS使用了模板方法模式，自定义同步器时需要重写下面几个AQS提供的模板方法：

- isHeldExclusively() // 该线程是否正在独占资源。只有用到condition才需要去实现它。
- tryAcquire(int) // 独占方式。尝试获取资源，成功则返回true，失败则返回false。
- tryRelease(int) // 独占方式。尝试释放资源，成功则返回true，失败则返回false。
- tryAcquireShared(int) // 共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
- tryReleaseShared(int) // 共享方式。尝试释放资源，成功则返回true，失败则返回false。

默认情况下，每个方法都抛出 UnsupportedOperationException。 这些方法的实现必须是内部线程安全的，并且通常应该简短而不是阻塞。AQS类中的其他方法都是final ，所以无法被其他类使用，只有这几个方法可以被其他类使用。

以ReentrantLock为例，state初始化为0，表示未锁定状态。A线程lock()时，会调用tryAcquire()独占该锁并将state+1。此后，其他线程再tryAcquire()时就会失败，直到A线程unlock()到state=0（即释放锁）为止，其它线程才有机会获取该锁。当然，释放锁之前，A线程自己是可以重复获取此锁的（state会累加），这就是可重入的概念。但要注意，获取多少次就要释放多么次，这样才能保证state是能回到零态的。

以CountDownLatch以例，任务分为N个子线程去执行，state也初始化为N（注意N要与线程个数一致）。这N个子线程是并行执行的，每个子线程执行完后countDown()一次，state会CAS(Compare and Swap)减1。等到所有子线程都执行完后(即state=0)，会unpark()主调用线程，然后主调用线程就会从await()函数返回，继续后余动作。

一般来说，自定义同步器要么是独占方法，要么是共享方式，他们也只需实现tryAcquire-tryRelease、tryAcquireShared-tryReleaseShared中的一种即可。但AQS也支持自定义同步器同时实现独占和共享两种方式，如ReentrantReadWriteLock。

### 组件总结

- Semaphore（信号量）- 允许多个线程同时访问
- CountDownLatch（倒计时器）
- CyclicBarrier（循环栅栏）

### 线程间通信

- 共享内存方式
    - synchronized关键字
    - 通过volatile修饰的变量
- 管道
  通过使用 `java.io.PipedInputStream` 和 `java.io.PipedOutputStream` 进行通讯
- wait/notify 机制

# Java并发相关的类库 JUC（java.util.concurrent）

## 并发容器总结

- ConcurrentHashMap: 线程安全的 HashMap
- CopyOnWriteArrayList: 线程安全的 List，在读多写少的场合性能非常好，远远好于 Vector.
- ConcurrentLinkedQueue: 高效的并发队列，使用链表实现。可以看做一个线程安全的 LinkedList，这是一个非阻塞队列。
- BlockingQueue: 这是一个接口，JDK 内部通过链表、数组等方式实现了这个接口。表示阻塞队列，非常适合用于作为数据共享的通道。
- ConcurrentSkipListMap: 跳表的实现。这是一个 Map，使用跳表的数据结构进行快速查找。

# 线程池

# 乐观锁和悲观锁

## 悲观锁

总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁（**共享资源每次只给一个线程使用，其它线程阻塞，用完后再把资源转让给其它线程**）。传统的关系型数据库里边就用到了很多这种锁机制，比如**行锁，表锁等，读锁，写锁**等，都是在做操作之前先上锁。Java中synchronized和ReentrantLock等独占锁就是悲观锁思想的实现。

## 乐观锁

总是假设最好的情况，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号机制和CAS算法实现。**乐观锁适用于多读的应用类型，这样可以提高吞吐量**。像数据库提供的类似于write_condition机制，其实都是提供的乐观锁。在Java中java.util.concurrent.atomic包下面的原子变量类就是使用了乐观锁的一种实现方式CAS实现的。

## 两种锁的应用场景（多读乐，多写悲）

从上面对两种锁的介绍，我们知道两种锁各有优缺点，不可认为一种好于另一种，像**乐观锁适用于写比较少的情况下（多读场景）**，即冲突真的很少发生的时候，这样可以省去了锁的开销，加大了系统的整个吞吐量。但如果是多写的情况，一般会经常产生冲突，这就会导致上层应用会不断的进行retry，这样反倒是降低了性能，所以**一般多写的场景下用悲观锁就比较合适**。

## 乐观锁常见的两种实现方式

> 乐观锁一般会使用版本号机制或CAS算法实现。

- 版本号机制
- CAS算法
  即compare and swap（比较与交换），是一种有名的**无锁算法**。无锁编程，即不使用锁的情况下实现多线程之间的变量同步，也就是在没有线程被阻塞的情况下实现变量的同步，所以也叫非阻塞同步（Non-blocking Synchronization）。**CAS算法**涉及到三个操作数
    - 需要读写的内存值
    - V进行比较的值
    - A拟写入的新值 B

  当且仅当 V 的值等于 A时，CAS通过原子方式用新值B来更新V的值，否则不会执行任何操作（比较和替换是一个原子操作）。一般情况下是一个自旋操作，即不断的重试。

## 乐观锁的缺点

1. **ABA 问题**
    - 发生过程：
    1. 线程A取出内存位置V的a
    2. 线程B也去除内存位置V的a
    3. 线程A准备把数据改成b
    4. 线程B把数据改成c之后，又改回了a
    5. 线程A比较了内存位置V的a相同，修改了数据变为b
    - 影响：
      如果是单向链表实现的堆栈，会造成数据丢失的情况。
    - 解决：
      使用AtomicStampedReference包装，加上特定的版本戳来避免ABA问题。
2. **循环时间长开销大**
3. **只能保证一个共享变量的原子操作**

## CAS与synchronized的使用情景

- CAS适用于写比较少的情况下（多读场景，冲突一般较少）
- synchronized适用于写比较多的情况下（多写场景，冲突一般较多）

原因：
1. 对于资源竞争较少（线程冲突较轻）的情况，使用synchronized同步锁进行线程阻塞和唤醒切换以及用户态内核态间的切换操作额外浪费消耗cpu资源；而CAS基于硬件实现，不需要进入内核，不需要切换线程，操作自旋几率较少，因此可以获得更高的性能。
2. 对于资源竞争严重（线程冲突严重）的情况，CAS自旋的概率会比较大，从而浪费更多的CPU资源，效率低于synchronized。

# Atomic原子类

# AQS

# 并发编程的三大核心

## 协作

- 信号量（Semaphore）
- 管程（Monitor）
    - Lock与Condition
    - synchronized
- CountDownLatch
- Phaser
- Exchanger

## 互斥

- 互斥锁
    - synchronized
    - Lock
    - 读写锁
- 无锁
    - 不变模式/final
    - 线程本地存储/ThreadLocal
    - CAS/Unsafe
    - Copy-on-Write
    - 原子类（AtomicXxx）

## 分工

- Executor 与线程池
- Future
- Fork/Join
- Guarded Suspension 模式
- Balking 模式
- Thread-Per-Message 模式
- 生产者-消费者模式
- Worker Thread 模式
- 两阶段终止模式
- 并发编程的其他模式