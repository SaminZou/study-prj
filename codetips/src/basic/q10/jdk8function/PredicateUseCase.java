package basic.q10.jdk8function;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java8 Predicate 接口的使用
 *
 * @author samin
 * @date 2022-02-21
 */
public class PredicateUseCase {

    public static void main(String[] args) {
        Predicate<String> judge = (String s) -> s.equals("");
        System.out.println(judge.test("123"));
        System.out.println(judge.test(""));
        System.out.println("**********************************");

        // Stream 的 filter 方法就是传入一个实现 Predicate 的实例化
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Predicate<Integer> integerPredicate = (x) -> x > 3;
        List<Integer> resultList = integerStream.filter(integerPredicate)
                                                .collect(Collectors.toList());
        resultList.forEach(System.out::println);
    }
}
