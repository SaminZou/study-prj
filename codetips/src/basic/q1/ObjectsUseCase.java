package basic.q1;

import java.util.Objects;

/**
 * Object 类用例
 *
 * @author samin
 * @date 2020-12-31
 */
public class ObjectsUseCase {

    public static void main(String[] args) {
        String a = null;
        String b = "123";
        // 这个会报空指针
        // System.out.println(a.equals(b));

        // 正常需要这样来解决
        if (a != null) {
            System.out.println(a.equals(b));
        }

        // 或者使用常量或者确定值来解决报空指针的问题
        if ("123".equals(a)) {
        }

        /**
         * JDK1.7之后提供 Objects 工具包解决这个问题
         *
         * <p>Objects 的 equals() 方法是先判断入参是否为空，然后调用 Object 的 equals() 方法进行比较
         *
         * <p>原因在于 a.equals(b) 这种写法，当 a 为 null 值时，会报错
         */
        System.out.println(Objects.equals(a, b));
        System.out.println("判断不为空: " + Objects.nonNull(a));
        System.out.println("判断为空: " + Objects.isNull(a));
    }
}
