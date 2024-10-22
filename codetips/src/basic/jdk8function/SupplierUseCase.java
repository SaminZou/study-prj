package basic.jdk8function;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Java8 Supplier 接口的使用
 *
 * @author samin
 * @date 2022-02-21
 */
public class SupplierUseCase {

    public static void main(String[] args) {
        // 基础用法
        Supplier<Integer> intGenerator = () -> new Random().nextInt();
        System.out.println(intGenerator.get());
        System.out.println("*********************************");

        // 和 optional 搭配使用，不存在大于 5 的数
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> firstInt = integerStream.filter(i -> i > 5)
                                                  .findFirst();
        // orElse：如果first中存在数，就返回这个数，如果不存在，就放回传入的数
        System.out.println(firstInt.orElse(1));
        // orElseGet：如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
        System.out.println(firstInt.orElseGet(() -> new Random().nextInt()));
        System.out.println("*********************************");
    }
}
