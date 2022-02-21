package basic.q12;

import java.util.function.Consumer;

/**
 * Java8 Consumer 接口的使用
 *
 * @author samin
 * @date 2022-02-21
 */
public class ConsumerUseCase {

    public static void main(String[] args) {
        Consumer<String> mq = System.out::println;
        mq.accept("test");
    }
}
