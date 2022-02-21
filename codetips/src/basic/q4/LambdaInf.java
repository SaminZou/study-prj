package basic.q4;

/**
 * 演示接口3
 *
 * <p>修饰的接口为 函数式接口
 *
 * <p>定义接口中的抽象方法只能有一个
 *
 * @author samin
 * @date 2021-01-10
 */
@FunctionalInterface
public interface LambdaInf {

    void action(String words);

    default void actionUtils() {
        System.out.println("此方法不影响 Lambda 表达式的使用");
    }
}
