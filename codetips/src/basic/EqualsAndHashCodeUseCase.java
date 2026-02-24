package basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * equals() 和 hashCode() 方法的深入理解和实践
 *
 * 本示例演示了：
 * 1. equals() 和 hashCode() 方法的作用和关系
 * 2. 为什么需要同时重写这两个方法
 * 3. 在集合类（HashMap、HashSet）中的实际应用
 * 4. 常见的最佳实践和注意事项
 *
 * @author samin
 * @date 2021-01-10
 * @version 2.0 - 优化代码结构和增加更多用例
 */
public class EqualsAndHashCodeUseCase {

    public static void main(String[] args) {
        System.out.println("=== equals() 和 hashCode() 方法演示 ===\n");

        // 基础示例：没有重写 equals 和 hashCode
        demonstrateBasicEquals();
        System.out.println();

        // 进阶示例：重写 equals 和 hashCode
        demonstrateAdvancedEquals();
        System.out.println();

        // HashMap 中的使用
        demonstrateHashMapUsage();
        System.out.println();

        // HashSet 中的使用
        demonstrateHashSetUsage();
        System.out.println();

        // 常见陷阱和注意事项
        demonstrateCommonPitfalls();
    }

    /**
     * 演示基础 equals 方法的行为
     */
    private static void demonstrateBasicEquals() {
        System.out.println("1. 基础示例：没有重写 equals() 和 hashCode()");
        System.out.println("-".repeat(50));

        User user1 = new User("张三", 25, "13800138000");
        User user2 = new User("张三", 25, "13800138000");

        System.out.println("用户1: " + user1);
        System.out.println("用户2: " + user2);
        System.out.println();

        System.out.println("内存地址比较:");
        System.out.println("  user1 内存地址: " + System.identityHashCode(user1));
        System.out.println("  user2 内存地址: " + System.identityHashCode(user2));
        System.out.println("  内存地址相同: " + (user1 == user2));
        System.out.println();

        System.out.println("equals() 方法比较:");
        System.out.println("  user1.equals(user2): " + user1.equals(user2));
        System.out.println("  user2.equals(user1): " + user2.equals(user1));
        System.out.println("  user1.equals(null): " + user1.equals(null));
        System.out.println("  user1.equals(new Object()): " + user1.equals(new Object()));
    }

    /**
     * 演示重写 equals 和 hashCode 后的行为
     */
    private static void demonstrateAdvancedEquals() {
        System.out.println("2. 进阶示例：重写 equals() 和 hashCode()");
        System.out.println("-".repeat(50));

        UserPro userPro1 = new UserPro("李四", 30, "13900139000");
        UserPro userPro2 = new UserPro("李四", 30, "13900139000");

        System.out.println("用户Pro1: " + userPro1);
        System.out.println("用户Pro2: " + userPro2);
        System.out.println();

        System.out.println("内存地址比较:");
        System.out.println("  userPro1 内存地址: " + System.identityHashCode(userPro1));
        System.out.println("  userPro2 内存地址: " + System.identityHashCode(userPro2));
        System.out.println("  内存地址相同: " + (userPro1 == userPro2));
        System.out.println();

        System.out.println("equals() 方法比较:");
        System.out.println("  userPro1.equals(userPro2): " + userPro1.equals(userPro2));
        System.out.println("  userPro2.equals(userPro1): " + userPro2.equals(userPro1));
        System.out.println("  userPro1.equals(null): " + userPro1.equals(null));
        System.out.println();

        System.out.println("hashCode() 方法比较:");
        System.out.println("  userPro1.hashCode(): " + userPro1.hashCode());
        System.out.println("  userPro2.hashCode(): " + userPro2.hashCode());
        System.out.println("  hashCode 相同: " + (userPro1.hashCode() == userPro2.hashCode()));
    }

    /**
     * 演示在 HashMap 中的使用
     */
    private static void demonstrateHashMapUsage() {
        System.out.println("3. HashMap 中的使用演示");
        System.out.println("-".repeat(50));

        Map<UserPro, String> userMap = new HashMap<>();

        UserPro user1 = new UserPro("王五", 28, "13700137000");
        UserPro user2 = new UserPro("王五", 28, "13700137000");
        UserPro user3 = new UserPro("赵六", 35, "13600136000");

        System.out.println("添加用户到 HashMap:");
        System.out.println("  添加 user1 -> '用户1'");
        userMap.put(user1, "用户1");
        System.out.println("  添加 user2 -> '用户2'");
        userMap.put(user2, "用户2");
        System.out.println("  添加 user3 -> '用户3'");
        userMap.put(user3, "用户3");
        System.out.println();

        System.out.println("HashMap 内容:");
        userMap.forEach((key, value) -> System.out.println("  " + key + " -> " + value));
        System.out.println();

        System.out.println("查找操作:");
        System.out.println("  user1 在 map 中: " + userMap.containsKey(user1));
        System.out.println("  user2 在 map 中: " + userMap.containsKey(user2));
        System.out.println("  新建相同用户查找: " + userMap.containsKey(new UserPro("王五", 28, "13700137000")));
    }

    /**
     * 演示在 HashSet 中的使用
     */
    private static void demonstrateHashSetUsage() {
        System.out.println("4. HashSet 中的使用演示");
        System.out.println("-".repeat(50));

        Set<UserPro> userSet = new HashSet<>();

        UserPro user1 = new UserPro("孙七", 32, "13500135000");
        UserPro user2 = new UserPro("孙七", 32, "13500135000");
        UserPro user3 = new UserPro("周八", 40, "13400134000");

        System.out.println("添加用户到 HashSet:");
        System.out.println("  添加 user1: " + userSet.add(user1));
        System.out.println("  添加 user2: " + userSet.add(user2));
        System.out.println("  添加 user3: " + userSet.add(user3));
        System.out.println();

        System.out.println("HashSet 大小: " + userSet.size());
        System.out.println("HashSet 内容:");
        userSet.forEach(user -> System.out.println("  " + user));
        System.out.println();

        System.out.println("查找操作:");
        System.out.println("  user1 在 set 中: " + userSet.contains(user1));
        System.out.println("  user2 在 set 中: " + userSet.contains(user2));
        System.out.println("  新建相同用户查找: " + userSet.contains(new UserPro("孙七", 32, "13500135000")));
    }

    /**
     * 演示常见陷阱和注意事项
     */
    private static void demonstrateCommonPitfalls() {
        System.out.println("5. 常见陷阱和注意事项");
        System.out.println("-".repeat(50));

        // 陷阱1：只重写 equals 不重写 hashCode
        System.out.println("陷阱1: 只重写 equals() 不重写 hashCode()");
        UserPartial user1 = new UserPartial("钱九", 45);
        UserPartial user2 = new UserPartial("钱九", 45);
        System.out.println("  equals() 结果: " + user1.equals(user2));
        System.out.println("  hashCode() 结果: " + user1.hashCode() + " vs " + user2.hashCode());
        System.out.println("  在 HashMap 中行为异常");
        System.out.println();

        // 陷阱2：可变对象作为 HashMap 的键
        System.out.println("陷阱2: 可变对象作为 HashMap 的键");
        UserMutable mutableUser = new UserMutable("吴十", 50, "13300133000");
        Map<UserMutable, String> mutableMap = new HashMap<>();
        mutableMap.put(mutableUser, "初始值");
        System.out.println("  修改前: " + mutableMap.get(mutableUser));
        mutableUser.setAge(51); // 修改对象状态
        System.out.println("  修改后: " + mutableMap.get(mutableUser));
        System.out.println("  原因: hashCode 改变导致无法找到原来的键");
        System.out.println();

        // 最佳实践总结
        System.out.println("最佳实践总结:");
        System.out.println("  ✓ 总是同时重写 equals() 和 hashCode()");
        System.out.println("  ✓ 使用 Objects.equals() 和 Objects.hash() 简化实现");
        System.out.println("  ✓ 确保 equals() 满足自反性、对称性、传递性、一致性");
        System.out.println("  ✓ 确保 hashCode() 在 equals() 为 true 时返回相同值");
        System.out.println("  ✓ 避免使用可变对象作为 HashMap/HashSet 的键");
    }

    // ==================== 内部类定义 ====================

    /**
     * 基础用户类 - 没有重写 equals 和 hashCode
     */
    private static class User {

        private String name;
        private int age;
        private String phone;

        public User() {}

        public User(String name, int age, String phone) {
            this.name = name;
            this.age = age;
            this.phone = phone;
        }

        public String getName() {return name;}

        public void setName(String name) {this.name = name;}

        public int getAge() {return age;}

        public void setAge(int age) {this.age = age;}

        public String getPhone() {return phone;}

        public void setPhone(String phone) {this.phone = phone;}

        @Override
        public String toString() {
            return String.format("User{name='%s', age=%d, phone='%s'}", name, age, phone);
        }
    }

    /**
     * 进阶用户类 - 正确重写 equals 和 hashCode
     */
    private static class UserPro extends User {

        public UserPro(String name, int age, String phone) {
            super(name, age, phone);
        }

        /**
         * 正确重写 equals 方法，满足：
         * 1. 自反性：x.equals(x) 必须返回 true
         * 2. 对称性：x.equals(y) 必须和 y.equals(x) 结果相同
         * 3. 传递性：x.equals(y) 且 y.equals(z) 则 x.equals(z)
         * 4. 一致性：多次调用结果一致
         * 5. 非空性：x.equals(null) 必须返回 false
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return getAge() == user.getAge() && Objects.equals(getName(), user.getName()) && Objects.equals(getPhone(),
                                                                                                            user.getPhone());
        }

        /**
         * 正确重写 hashCode 方法，满足：
         * 1. 如果两个对象 equals() 为 true，则 hashCode 必须相同
         * 2. 如果两个对象 equals() 为 false，hashCode 最好不同（但不是必须）
         * 3. 多次调用 hashCode() 应该返回相同值（对象状态不变时）
         */
        @Override
        public int hashCode() {
            return Objects.hash(getName(), getAge(), getPhone());
        }

        @Override
        public String toString() {
            return String.format("UserPro{name='%s', age=%d, phone='%s', hashCode=%d}", getName(), getAge(), getPhone(),
                                 hashCode());
        }
    }

    /**
     * 部分重写类 - 只重写 equals 不重写 hashCode（错误示例）
     */
    private static class UserPartial {

        private String name;
        private int age;

        public UserPartial(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UserPartial that = (UserPartial) o;
            return age == that.age && Objects.equals(name, that.name);
        }

        // 故意不重写 hashCode() 来演示问题

        @Override
        public String toString() {
            return String.format("UserPartial{name='%s', age=%d}", name, age);
        }
    }

    /**
     * 可变用户类 - 演示可变对象作为键的问题
     */
    private static class UserMutable {

        private String name;
        private int age;
        private String phone;

        public UserMutable(String name, int age, String phone) {
            this.name = name;
            this.age = age;
            this.phone = phone;
        }

        public String getName() {return name;}

        public void setName(String name) {this.name = name;}

        public int getAge() {return age;}

        public void setAge(int age) {this.age = age;}

        public String getPhone() {return phone;}

        public void setPhone(String phone) {this.phone = phone;}

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UserMutable that = (UserMutable) o;
            return age == that.age && Objects.equals(name, that.name) && Objects.equals(phone, that.phone);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, phone);
        }

        @Override
        public String toString() {
            return String.format("UserMutable{name='%s', age=%d, phone='%s'}", name, age, phone);
        }
    }
}