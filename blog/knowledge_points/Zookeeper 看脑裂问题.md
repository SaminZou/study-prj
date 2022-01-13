```yaml
title: Zookeeper 看脑裂问题 
author: samin
date: 2022-01-13
```

# zookeeper是什么

服务注册中心

**数据发布/订阅、负载均衡、命名服务、分布式协 调/通知、集群管理、Master 选举、配置维护，名字服务、分布式同步、分布式锁和分布式队列** 等功能

# zookeeper集群

# 脑裂问题

## 什么是脑裂

脑裂(split-brain)就是“大脑分裂”，也就是本来一个“大脑”被拆分了两个或多个“大脑”，我们都知道，如果一个人有多个大脑，并且相互独立的话，那么会导致人体“手舞足蹈”，“不听使唤”。
脑裂通常会出现在集群环境中，而这些集群环境有一个统一的特点，就是它们有一个大脑，即Zookeeper集群中的Leader节点。

## 实际问题

对于一个集群，想要提高这个集群的可用性，通常会采用**多机房部署**，比如现在有一个由6台zkServer所组成的一个集群，部署在了两个机房。

正常情况下，此集群只会有一个Leader，那么如果机房之间的网络断了之后，两个机房内的zkServer还是可以相互通信的，如果不考虑**过半机制**，那么就会出现每个机房内部都将选出一个Leader。

## 过半机制

```java
public class QuorumMaj implements QuorumVerifier {
    private static final Logger LOG = LoggerFactory.getLogger(QuorumMaj.class);
    
    int half;
    
    // n表示集群中zkServer的个数（准确的说是参与者的个数，参与者不包括观察者节点）
    public QuorumMaj(int n){
        this.half = n/2;
    }

    // 验证是否符合过半机制
    public boolean containsQuorum(Set<Long> set){
        // half是在构造方法里赋值的
        // set.size()表示某台zkServer获得的票数
        return (set.size() > half);
    }
}
```

> 假设现在集群中有5台zkServer，那么half=5/2=2，那么也就是说，领导者选举的过程中至少要有三台zkServer投了同一个zkServer，才会符合过半机制，才能选出来一个Leader。

## 过半机制中为什么是大于，而不是大于等于呢？

假设现在集群中有6台zkServer，当机房中间的网络断掉之后，机房1内的三台服务器会进行领导者选举，但是此时过半机制的条件是set.size() > 3，也就是说至少要4台zkServer才能选出来一个Leader，所以对于机房1来说它不能选出一个Leader，同样机房2也不能选出一个Leader，这种情况下整个集群当机房间的网络断掉后，整个集群将没有Leader。
而如果过半机制的条件是set.size() >= 3，那么机房1和机房2都会选出一个Leader，这样就出现了脑裂。所以我们就知道了，**为什么过半机制中是大于，而不是大于等于。就是为了防止脑裂**。

## 总结

有了过半机制，对于一个Zookeeper集群，要么没有Leader，要没只有1个Leader，这样就避免了脑裂问题。