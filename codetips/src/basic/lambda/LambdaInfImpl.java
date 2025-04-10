package basic.lambda;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 演示主方法
 *
 * <p>lambda表达式是jdk1.8之后才有的新特性
 *
 * @author samin
 * @date 2021-01-10
 */
public class LambdaInfImpl {

    public LambdaInfImpl() {
        System.out.println("实例化 LambdaInfImpl...");
    }

    public static void main(String[] args) {
        // Lambda 规定接口中只能有一个需要被实现的方法，不是规定接口中只能有一个方法，
        // 但是 default 修饰的方法不影响
        LambdaInf lambdaInf = System.out::println;
        // LambdaInf lambdaInf = System.out::println; // 更简单的表达式

        lambdaInf.actionUtils();
        lambdaInf.action("lambda use case ! ");
        System.out.println("-------------------------------");

        // 多参数，切有返回值
        LambdaInf2 lambdaInf2 = Integer::sum;
        // 简单的表达式
        // LambdaInf2 lambdaInf2 = (x, y) -> Integer.sum(x, y);
        // 更简单的表达式
        // LambdaInf2 lambdaInf2 = Integer::sum;
        System.out.println(lambdaInf2.sum(10, 20));
        System.out.println("-----------------------------");

        // 引用方法
        LambdaInfImpl lii = new LambdaInfImpl();
        LambdaInf3 lambdaInf3 = lii::method;
        lambdaInf3.method("使用lambda表达式实现方法");
        System.out.println("-----------------------------");

        // Consumer 接口是 JDK 为我们提供给的一个函数式接口
        ArrayList<String> list = new ArrayList<>();
        list.add("3e");
        list.add("4b");
        list.add("5a");
        list.add("1f");
        list.add("2e");
        list.add("a");
        // 根据条件删除
        list.removeIf("a"::equals);
        // 排序
        list.sort(Comparator.comparingInt(e -> e.charAt(0)));
        // 更简便的写法
        // list.sort(Comparator.comparingInt(e -> e.charAt(0)));
        // 遍历操作
        list.forEach(System.out::println);
    }

    public void method(String param) {
        System.out.println(param);
    }
}
