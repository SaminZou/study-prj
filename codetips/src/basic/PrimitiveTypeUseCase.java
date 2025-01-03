package basic;

/**
 * 原生类型的坑 基本类型的包装类不都实现了常量池
 *
 * @author samin
 * @date 2021-01-10
 */
public class PrimitiveTypeUseCase {

    public static final String STRING1;
    public static final String STRING2;
    public static final String STRING3 = "a";
    public static final String STRING4 = "a";

    static {
        STRING1 = "a";
        STRING2 = "a";
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {
        // Byte,Short,Integer,Long,Character,Boolean
        // 使用自动拆装箱声明变量，对数值[-128，127]的相应类型的缓存数据，但是超出此范围仍然会去创建新的对象
        // 解决方案非常简单，只要严格遵守对象是通过 equals 方法进行对比即可
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1 == i2); // true
        Integer i3 = 400;
        Integer i4 = 400;
        System.out.println(i3 == i4); // false
        Short s1 = 128;
        Short s2 = 128;
        System.out.println(s1 == s2); // false
        System.out.println("--------------------------------");

        // Integer自动拆箱的情况，相加或者和基本类型进行比较
        int primitiveInt = 300;
        Integer int1 = new Integer(300);
        Integer int2 = new Integer(0);
        System.out.println(primitiveInt == int1 + int2);
        System.out.println(300 == int1 + int2);
        System.out.println("------------------------------");

        // 关于 "+" 表达式
        // 对象间的 "+" 不会触发
        // 常量字符串间的 "+" 会加入到常量池
        String str1 = "str"; // 触发自动装箱
        String str2 = "ing";
        String str3 = "str" + "ing";
        String str4 = "string";
        System.out.println(str1 + str2 == str4); // false
        System.out.println(str3 == str4); // true
        System.out.println("---------------------------------");

        // 由于 STRING1 + STRING2 在编译期不能决定
        String ele = "aa";
        System.out.println(ele == STRING1 + STRING2); // false
        System.out.println(ele == STRING3 + STRING4); // true
        System.out.println("---------------------------------");

        // String.intern() 是一个 Native 方法，
        // 如果运行时常量池中已经包含一个等于此 String 对象内容的字符串，则返回常量池中该字符串的引用；
        // 如果没有，则在常量池中创建与此 String 内容相同的字符串，并返回常量池中创建的字符串的引用。
        String newStr1 = "计算机";
        String newStr2 = newStr1.intern();
        String newStr3 = "计算机";
        System.out.println(newStr1 == newStr2); // false，因为一个是堆内存中的 String 对象一个是常量池中的 String 对象，
        System.out.println(newStr3 == newStr2); // true，因为两个都是常量池中的 String 对象

        // String s1 = new String("xyz"); 创建了几个对象？
        // 类加载阶段会放一份到字符串常量池
        // 实际执行时，会复制一份到heap堆中指向s1

        // 以下是 false，可看 Long 的 equals 源码
        Long longNum = -1L;
        System.out.println(longNum.equals(-1));
    }
}
