package basic.q10.jdk8function;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java8 Function 接口的使用
 *
 * @author samin
 * @date 2021-01-10
 */
public class FunctionUseCase {

    public static void main(String[] args) {
        // 最基础的使用
        Function<String, Integer> func = Integer::valueOf;
        Integer num = func.apply("100");
        System.out.println("the num is :" + num);
        System.out.println("********************");

        // 可以声明多个组合
        Function<String, Integer> func1 = Integer::valueOf;
        Function<Integer, Integer> func2 = x -> x + 1;
        Integer numPlus = func1.andThen(func2)
                               .apply("100");
        System.out.println("the num after plus 1 is :" + numPlus);
        System.out.println("********************");

        // Stream 的 map 方法就是传入一个实现 Function 的实例化
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Function<Integer, String> integerStringFunction = String::valueOf;
        List<String> resultList = integerStream.map(integerStringFunction)
                                               .collect(Collectors.toList());
        resultList.forEach(System.out::println);
    }
}
