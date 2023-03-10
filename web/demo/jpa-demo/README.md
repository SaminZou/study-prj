@Transactional 失效的3种场景及解决办法

https://mp.weixin.qq.com/s/1PSYTCvkDocUNU4AGbuLng

1. 方法没有使用 public
2. 在类内部调用调用类内部 @Transactional 标注的方法
3. 事务方法内部捕捉了异常，没有抛出新的异常，导致事务操作不会进行回滚