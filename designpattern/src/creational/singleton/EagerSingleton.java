package creational.singleton;

/**
 * 饿汉模式（提前创建对象，使用时直接使用即可）
 *
 * <p> 类加载初始化的时候执行
 *
 * @author samin
 * @date 2021-01-05
 */
public class EagerSingleton {

    /**
     * 利用静态变量来记录 Singleton 的唯一实例，确保线程安全
     */
    private static final EagerSingleton singleton = new EagerSingleton();

    /**
     * 私有化，只有 Singleton 类内才可以调用构造器
     */
    private EagerSingleton() {
    }

    public static EagerSingleton getSingleton() {
        return singleton;
    }
}
