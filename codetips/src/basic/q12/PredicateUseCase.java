package basic.q12;

import java.util.function.Predicate;

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
    }
}
