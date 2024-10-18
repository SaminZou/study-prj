package basic;

/**
 * Java 里面其实只有值传递，包括对象
 *
 * <p>对象严格意义上来说是`按共享传递`，按共享传递是值传递的特例
 *
 * <p>可以通过下面的例子来理解这个事情
 *
 * <p>本 demo 不演示值传递，目的是让大家清楚为什么说对象传递也是值传递
 *
 * @author samin
 * @date 2021-01-10
 */
public class PassByValueUseCase {

    public static void setUserName(User user) {
        user.name = "has change";
    }

    public static void setUser(User user) {
        user = new User();
        user.name = "has change";
    }

    public static void main(String[] args) {
        User user = new User();
        user.name = "original string";
        System.out.println("call function before: " + user.name);

        // 首先看第一个方法调用，相信很容易理解，然后让大家的感觉是引用传递
        setUserName(user);
        System.out.println("call function after: " + user.name);

        System.out.println("--------------------------------------");

        User user2 = new User();
        user2.name = "original string";
        System.out.println("call function before: " + user2.name);
        // 看第二个实例调用第二个方法，如果还是引用传递的话，user2 的值应该会变，实际上并没有
        setUser(user2);
        System.out.println("call function after: " + user2.name);

        // 总结，值传递的本质为实参进入方法后是否复制了副本给形参，原始类型和 String 类型很明显是复制了副本
        // 对于对象而言，其实也复制了一个副本，只不过是指向了同一个地址，修改副本指向地址内容，当然实参也会变化，但是修改副本的地址，实参不变
        // 对象的传递其实是 `按共享传递`，本质是特殊的值传递
    }

    private static class User {

        String name;
    }
}
