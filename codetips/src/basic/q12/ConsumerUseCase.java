package basic.q12;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Java8 Consumer 接口的使用
 *
 * @author samin
 * @date 2022-02-21
 */
public class ConsumerUseCase {

    public static void main(String[] args) {
        // 消费输入
        Consumer<String> consumerImpl = System.out::println;
        consumerImpl.accept("test");

        // Stream 的 forEach / peek 方法就是传入一个实现 Consumer 的实例化
        Stream<String> someStr = Stream.of("a", "B", "c");
        someStr.forEach(consumerImpl);
    }
}
