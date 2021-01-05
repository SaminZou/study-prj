package basic.q1;

import java.util.StringJoiner;

public class StringUseCase {
    private static final String ABC = "123"; // 常量

    public static void main(String[] args) {
        // ---------------------------- JDK8 学会使用StringJoiner -------------------------------
        // 更方便的加入分隔符
        StringJoiner sj = new StringJoiner(" ");
        sj.add("hello");
        sj.add("world");
        sj.add("!");
        System.out.println("create by StringJoiner:" + sj.toString());
        // String也有静态方法可以直接支持字符串拼接，效果同上
        String res = String.join(" ", "hello", "world", "!");
        System.out.println("create by string.join(): " + res);
        // 可以加头加尾
        sj = new StringJoiner("/", "/", "");
        sj.add("usr");
        sj.add("local");
        sj.add("jdk");
        sj.add("bin");
        System.out.println("create by StringJoiner:" + sj.toString());
        System.out.println("------------------------");
        // String.join() 方法使用了 StringJoiner ，StringJoiner 的值使用了 StringBuilder ， 所以拼接效率也很高

        // --------------------------- 字符串初始化后在常量池 -----------------------------
        String a = "123"; // 字符对象

        // 以下打印的内存地址都相同
        System.out.println(System.identityHashCode("123"));
        System.out.println(System.identityHashCode(ABC));
        System.out.println(System.identityHashCode(a));
        System.out.println("------------------------");

        // System.out.println(a.equals("123"));
        // String类型变量可能会空指针，所以判断的时候可以用以下方式避免报错
        System.out.println("123".equals(a));

        // ---------------------------- 字符切割 -------------------------------
        // 以"."为分隔符
        String str1 = "192.168.110.1";
        for (String ele : str1.split("\\.")) {
            System.out.println(ele);
        }
        System.out.println("-----------------------------");

        // 以"|"为分隔符
        String str2 = "aa|bb|cc|dd";
        for (String ele : str2.split("\\|")) {
            System.out.println(ele);
        }
        System.out.println("-----------------------------");

        // 需要多个分隔符，用"|"作为连接符
        String str3 = "1@2@3&4&5&6";
        for (String ele : str3.split("@|&")) {
            System.out.println(ele);
        }
    }
}
