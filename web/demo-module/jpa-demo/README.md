@Transactional 失效的3种场景及解决办法

1. 方法没有使用 public

2. 在类内部调用调用类内部 @Transactional 标注的方法

3. 事务方法内部捕捉了异常，没有抛出新的异常，导致事务操作不会进行回滚

4. @Transactional(rollbackFor = SpecException.class) 捕捉异常和抛出异常类型不一样  
   noRollbackFor 会覆盖 rollbackFor，@Transactional(rollbackFor = SpecException.class，noRollbackFor = SpecException.
   class) 会导致 SpecException 异常不会被捕捉

5. `deleteUserB` 在新线程中开启，连接不是同一个，所以报错不会触发 `deleteUserA` 回滚

```java
@Transactional
public void deleteUser() throws MyException{
    userMapper.deleteUserA();
 try {
  //休眠1秒，保证deleteUserA先执行
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    new Thread(() -> {
        int i = 1/0;
        userMapper.deleteUserB();
    }).start();    
}
```

6. 数据库本身不支持（如 MySQL 需要设置为 InnoDB）

7. 事务传播需要设置正确