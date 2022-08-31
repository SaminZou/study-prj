package creational.singleton;

/**
 * 双重校验锁 DCL double-checked locking
 *
 * @author samin
 * @date 2021-01-05
 */
public class DoubleCheckModeSingleton {

    /**
     * 需要加上 volatile，才能确保 DCL 万无一失
     */
    private static volatile DoubleCheckModeSingleton singleton = null;

    private DoubleCheckModeSingleton() {
    }

    public static DoubleCheckModeSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckModeSingleton.class) {
                if (singleton == null) {
                    // `new DoubleCheckModeSingleton()` 在底层会被分为
                    // 分配对象内存空间、设置字段初始化、设置instance指向分配的内存地址，此时 instance != null （重点）
                    // 由于指令可重排序 底层可能会把执行顺序变为
                    // 分配对象内存空间、设置instance指向分配的内存地址，此时 instance != null （重点）、设置字段初始化
                    // 在字段没有初始化的时候，实例处于半初始化状态，被其他线程使用会造成异常报错
                    singleton = new DoubleCheckModeSingleton();
                }
            }
        }

        return singleton;
    }
}
