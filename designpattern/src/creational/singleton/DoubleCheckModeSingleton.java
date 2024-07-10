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
     * `new DoubleCheckModeSingleton()` 在底层会被分为
     * 1.分配对象内存空间、2.设置字段初始化、3.设置 instance 指向分配的内存地址
     * 由于指令可重排序，底层可能会把执行顺序变为
     * 1.分配对象内存空间、3.设置 instance 指向分配的内存地址（此时 instance != null）、2.设置字段初始化
     * 在字段没有初始化的时候，实例处于半初始化状态，非首个初始化线程（首个线程由于锁的保护，一定会在 new 实例化完成后释放锁）调用，由于第一个 if 在同步块外部，这时候拿到没有初始化完成的事例，所以会造成异常报错
     */
    private static volatile DoubleCheckModeSingleton singleton = null;

    /**
     * 防止 new 实例化，只能通过统一方法获取实例
     */
    private DoubleCheckModeSingleton() {
    }

    public static DoubleCheckModeSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckModeSingleton.class) {
                // 在第一个调用执行期间，可能会有其他线程也在调用，这时候会进入阻塞，当第一个调用执行完，其他调用开始执行，这时候就需要这个判空来防止类再次实例化
                if (singleton == null) {
                    singleton = new DoubleCheckModeSingleton();
                }
            }
        }

        return singleton;
    }
}
