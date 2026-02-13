package basic;

/**
 * Java 值传递概念详解
 *
 * <p>Java 中只有值传递，包括对象传递。对象传递本质上是"按共享传递"，这是值传递的一种特例。</p>
 *
 * <p>本示例通过多个用例演示值传递的本质，帮助理解为什么对象传递也是值传递。</p>
 *
 * <h3>核心概念：</h3>
 * <ul>
 *   <li>值传递：实参进入方法后复制副本给形参</li>
 *   <li>按共享传递：对象传递时复制的是对象引用的副本，指向同一内存地址</li>
 *   <li>修改对象内容会影响原对象，但修改引用本身不会影响原引用</li>
 * </ul>
 *
 * @author samin
 * @date 2021-01-10
 * @version 2.0 - 优化版
 */
public class PassByValueUseCase {

    /**
     * 修改对象的内容 - 会影响原对象
     */
    public static void modifyObjectContent(User user) {
        user.name = "modified content";
        System.out.println("    Inside modifyObjectContent: user.name = " + user.name);
    }

    /**
     * 修改对象引用本身 - 不会影响原对象
     */
    public static void modifyObjectReference(User user) {
        user = new User();
        user.name = "new reference";
        System.out.println("    Inside modifyObjectReference: user.name = " + user.name);
    }

    /**
     * 基本类型值传递演示
     */
    public static void modifyPrimitiveValue(int value) {
        value = 100;
        System.out.println("    Inside modifyPrimitiveValue: value = " + value);
    }

    /**
     * 字符串值传递演示（字符串是不可变对象）
     */
    public static void modifyString(String str) {
        str = "modified string";
        System.out.println("    Inside modifyString: str = " + str);
    }

    /**
     * 数组值传递演示
     */
    public static void modifyArray(int[] arr) {
        arr[0] = 999;
        System.out.println("    Inside modifyArray: arr[0] = " + arr[0]);
    }

    /**
     * 返回新对象的方法
     */
    public static User createNewUser() {
        User newUser = new User();
        newUser.name = "new user object";
        return newUser;
    }

    /**
     * 交换两个整数的值（演示基本类型值传递的限制）
     */
    public static void swapIntegers(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("    Inside swapIntegers: a = " + a + ", b = " + b);
    }

    /**
     * 交换两个对象的内容（通过数组包装实现）
     */
    public static void swapObjectContents(User a, User b) {
        String temp = a.name;
        a.name = b.name;
        b.name = temp;
        System.out.println("    Inside swapObjectContents: a.name = " + a.name + ", b.name = " + b.name);
    }

    /**
     * 集合值传递演示
     */
    public static void modifyList(java.util.List<String> list) {
        list.add("added from method");
        System.out.println("    Inside modifyList: list size = " + list.size());
    }

    /**
     * 不可变对象演示（Integer）
     */
    public static void modifyInteger(Integer num) {
        num = num + 100;
        System.out.println("    Inside modifyInteger: num = " + num);
    }

    public static void main(String[] args) {
        System.out.println("=== Java 值传递概念演示 ===\n");

        // 用例1: 对象内容修改
        System.out.println("1. 对象内容修改演示:");
        User user1 = new User();
        user1.name = "original content";
        System.out.println("    Before modifyObjectContent: user1.name = " + user1.name);
        modifyObjectContent(user1);
        System.out.println("    After modifyObjectContent: user1.name = " + user1.name);
        System.out.println();

        // 用例2: 对象引用修改
        System.out.println("2. 对象引用修改演示:");
        User user2 = new User();
        user2.name = "original reference";
        System.out.println("    Before modifyObjectReference: user2.name = " + user2.name);
        modifyObjectReference(user2);
        System.out.println("    After modifyObjectReference: user2.name = " + user2.name);
        System.out.println();

        // 用例3: 基本类型值传递
        System.out.println("3. 基本类型值传递演示:");
        int primitiveValue = 10;
        System.out.println("    Before modifyPrimitiveValue: primitiveValue = " + primitiveValue);
        modifyPrimitiveValue(primitiveValue);
        System.out.println("    After modifyPrimitiveValue: primitiveValue = " + primitiveValue);
        System.out.println();

        // 用例4: 字符串值传递
        System.out.println("4. 字符串值传递演示:");
        String originalString = "original string";
        System.out.println("    Before modifyString: originalString = " + originalString);
        modifyString(originalString);
        System.out.println("    After modifyString: originalString = " + originalString);
        System.out.println();

        // 用例5: 数组值传递
        System.out.println("5. 数组值传递演示:");
        int[] array = {1, 2, 3};
        System.out.println("    Before modifyArray: array[0] = " + array[0]);
        modifyArray(array);
        System.out.println("    After modifyArray: array[0] = " + array[0]);
        System.out.println();

        // 用例6: 返回新对象
        System.out.println("6. 返回新对象演示:");
        User user3 = createNewUser();
        System.out.println("    Created new user: user3.name = " + user3.name);
        System.out.println();

        // 用例7: 交换整数演示（基本类型限制）
        System.out.println("7. 交换整数演示（基本类型限制）:");
        int x = 5, y = 10;
        System.out.println("    Before swapIntegers: x = " + x + ", y = " + y);
        swapIntegers(x, y);
        System.out.println("    After swapIntegers: x = " + x + ", y = " + y);
        System.out.println("    说明：基本类型值传递无法实现真正的交换");
        System.out.println();

        // 用例8: 交换对象内容
        System.out.println("8. 交换对象内容演示:");
        User userA = new User();
        userA.name = "User A";
        User userB = new User();
        userB.name = "User B";
        System.out.println("    Before swapObjectContents: userA.name = " + userA.name + ", userB.name = " + userB.name);
        swapObjectContents(userA, userB);
        System.out.println("    After swapObjectContents: userA.name = " + userA.name + ", userB.name = " + userB.name);
        System.out.println("    说明：对象内容交换成功，因为修改的是同一内存地址的内容");
        System.out.println();

        // 用例9: 集合值传递
        System.out.println("9. 集合值传递演示:");
        java.util.List<String> list = new java.util.ArrayList<>();
        list.add("original item");
        System.out.println("    Before modifyList: list size = " + list.size());
        modifyList(list);
        System.out.println("    After modifyList: list size = " + list.size());
        System.out.println("    说明：集合作为对象，修改内容会影响原集合");
        System.out.println();

        // 用例10: 不可变包装类演示
        System.out.println("10. 不可变包装类演示:");
        Integer integerValue = 50;
        System.out.println("    Before modifyInteger: integerValue = " + integerValue);
        modifyInteger(integerValue);
        System.out.println("    After modifyInteger: integerValue = " + integerValue);
        System.out.println("    说明：Integer是不可变对象，修改引用不影响原引用");
        System.out.println();

        // 总结
        System.out.println("=== 总结 ===");
        System.out.println("值传递的本质：实参进入方法后复制副本给形参");
        System.out.println("• 基本类型：复制值本身，修改不影响原值");
        System.out.println("• 对象类型：复制引用（指向同一地址），修改内容影响原对象，修改引用不影响原引用");
        System.out.println("• 字符串：不可变对象，修改引用不影响原引用");
        System.out.println("• 数组：对象类型，修改内容影响原数组");
        System.out.println("• 集合：对象类型，修改内容影响原集合");
        System.out.println("• 包装类：不可变对象，修改引用不影响原引用");
        System.out.println("• 交换操作：基本类型无法直接交换，对象内容可以交换");
    }

    /**
     * 内部用户类
     */
    private static class User {

        String name;

        // 可选：添加toString方法便于调试
        @Override
        public String toString() {
            return "User{name='" + name + "'}";
        }
    }
}
