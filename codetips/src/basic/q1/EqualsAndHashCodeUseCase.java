package basic.q1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * equals() 和 hashCode() 的区别
 *
 * @author samin
 * @date 2021-01-10
 */
public class EqualsAndHashCodeUseCase {

    public static void main(String[] args) {
        // 存放在两个地址的实例，假设在业务上，这两个对象需要是相等的
        User user1 = new User();
        user1.setName("a");
        user1.setAge(100);
        User user2 = new User();
        user2.setName("a");
        user2.setAge(100);
        // 没有重写 equals 和 hashcode，一定是 false
        System.out.println(user1.equals(user2));
        System.out.println("--------------------------");

        User userPro1 = new UserPro();
        userPro1.setName("a");
        userPro1.setAge(100);
        User userPro2 = new UserPro();
        userPro2.setName("a");
        userPro2.setAge(100);
        System.out.println("打印内存地址(不相同):");
        System.out.println(System.identityHashCode(userPro1));
        System.out.println(System.identityHashCode(userPro2));
        // 重写后调用 equals 方法，相等
        System.out.println(userPro1.equals(userPro2));
        System.out.println("--------------------------");

        // 为什么需要同时重写 equals 和 hashCode 方法
        // 这里展示了 hashCode 的作用，map 集合类的 put 方法会主动调用 hashCode 方法
        // 先判断 hashCode 是否相同，不一样的一定不相同，一样的情况下再调用 equals 方法验证最终结果
        // 因为类似集合类大量调用 equals 方法会造成性能低下，所以先转换成调用 hashCode 进行判断
        Map<User, Integer> map = new HashMap<>();
        map.put(userPro1, 1);
        map.put(userPro2, 2);
    }

    private static class User {

        private String name;
        private int age;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    private static class UserPro extends User {

        @Override
        public boolean equals(Object o) {
            System.out.println("调用了equals方法");
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return super.getAge() == user.getAge() && Objects.equals(super.getName(), user.getName()) && Objects.equals(
                    super.getPhone(), user.getPhone());
        }

        @Override
        public int hashCode() {
            System.out.println("调用了hashCode方法");
            return Objects.hash(super.getName(), super.getAge(), super.getPhone());
        }
    }
}
