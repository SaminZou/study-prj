package basic.generics;

/**
 * 使用泛型
 *
 * @author samin
 * @date 2020-12-23
 */
public class BeanFactory {

    public <T> T getBean(T t) {
        return t;
    }
}
