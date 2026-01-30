package basic;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Objects 类用例 - Objects 工具类最佳实践演示
 *
 * <p>Objects 类提供了一系列静态工具方法，用于处理对象的空值检查、相等性比较、哈希码生成等操作
 *
 * <p>主要方法包括：
 * <ul>
 *   <li>equals() - 安全的相等性比较，避免空指针异常</li>
 *   <li>deepEquals() - 深度相等性比较，支持数组</li>
 *   <li>hashCode() - 生成对象的哈希码</li>
 *   <li>hash() - 基于多个值生成哈希码</li>
 *   <li>toString() - 安全的对象转字符串</li>
 *   <li>compare() - 对象比较</li>
 *   <li>requireNonNull() - 非空检查</li>
 *   <li>isNull() / nonNull() - 空值判断</li>
 * </ul>
 *
 * @author samin
 * @date 2020-12-31
 */
public class ObjectsUseCase {

    private static final String DEFAULT_VALUE = "123";
    private static final Integer DEFAULT_NUMBER = 42;

    public static void main(String[] args) {
        demonstrateBasicUsage();
        demonstrateAdvancedMethods();
        demonstratePracticalExamples();
        demonstrateErrorHandling();
    }

    /**
     * 演示 Objects 类的基本用法
     */
    private static void demonstrateBasicUsage() {
        System.out.println("=== Objects 基本用法演示 ===");
        
        String a = null;
        String b = "123";

        System.out.println("传统空检查比较结果: " + 
            (a != null && a.equals(b) ? "true" : "false (a is null)"));

        System.out.println("常量在前比较结果: " + b.equals(a));

        System.out.println("Objects.equals() 比较结果: " + Objects.equals(a, b));
        System.out.println("Objects.isNull(a): " + Objects.isNull(a));
        System.out.println("Objects.nonNull(a): " + Objects.nonNull(a));
        System.out.println("Objects.nonNull(b): " + Objects.nonNull(b));
        
        System.out.println();
    }

    /**
     * 演示 Objects 类的高级方法
     */
    private static void demonstrateAdvancedMethods() {
        System.out.println("=== Objects 高级方法演示 ===");

        String nullString = null;
        System.out.println("Objects.toString(nullString): " + Objects.toString(nullString));
        System.out.println("Objects.toString(nullString, \"默认值\"): " + Objects.toString(nullString, "默认值"));
        System.out.println("Objects.toString(DEFAULT_VALUE): " + Objects.toString(DEFAULT_VALUE));

        Integer number = null;
        System.out.println("Objects.hashCode(null): " + Objects.hashCode(null));
        System.out.println("Objects.hashCode(DEFAULT_NUMBER): " + Objects.hashCode(DEFAULT_NUMBER));

        int multiHash = Objects.hash("name", 25, Arrays.asList(1, 2, 3));
        System.out.println("Objects.hash(\"name\", 25, list): " + multiHash);

        try {
            Objects.requireNonNull(null, "参数不能为空");
        } catch (NullPointerException e) {
            System.out.println("requireNonNull 捕获异常: " + e.getMessage());
        }

        String result = Objects.requireNonNull(DEFAULT_VALUE, "默认值不能为空");
        System.out.println("requireNonNull 成功: " + result);

        try {
            System.out.println("requireNonNullElse: 需要 Java 9+ 支持");
        } catch (Exception e) {
            System.out.println("requireNonNullElse 不可用: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 演示实际应用场景
     */
    private static void demonstratePracticalExamples() {
        System.out.println("=== 实际应用场景演示 ===");

        Person person1 = new Person("张三", 25);
        Person person2 = new Person("张三", 25);
        Person person3 = null;

        System.out.println("person1.equals(person2): " + Objects.equals(person1, person2));
        System.out.println("person1.equals(person3): " + Objects.equals(person1, person3));

        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        int[] array3 = {1, 2, 4};

        System.out.println("Objects.equals(array1, array2): " + Objects.equals(array1, array2));
        System.out.println("Objects.deepEquals(array1, array2): " + Objects.deepEquals(array1, array2));
        System.out.println("Objects.deepEquals(array1, array3): " + Objects.deepEquals(array1, array3));

        String firstName = "张";
        String lastName = null;
        String fullName = safeConcat(firstName, lastName);
        System.out.println("安全字符串拼接: " + fullName);

        Integer age = null;
        String ageCategory = getAgeCategory(age);
        System.out.println("年龄分类: " + ageCategory);

        System.out.println();
    }

    /**
     * 演示错误处理和最佳实践
     */
    private static void demonstrateErrorHandling() {
        System.out.println("=== 错误处理和最佳实践 ===");

        try {
            validateUserInput(null);
        } catch (IllegalArgumentException e) {
            System.out.println("参数验证失败: " + e.getMessage());
        }

        try {
            new Product(null, 100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("构造函数验证失败: " + e.getMessage());
        }

        String optionalResult = getOptionalValue(null);
        System.out.println("Optional 处理结果: " + optionalResult);

        System.out.println();
    }

    /**
     * 安全的字符串拼接方法
     *
     * @param first 第一个字符串
     * @param second 第二个字符串
     * @return 拼接后的字符串，null值会被替换为空字符串
     */
    private static String safeConcat(String first, String second) {
        return Objects.toString(first, "") + Objects.toString(second, "");
    }

    /**
     * 根据年龄获取年龄分类
     *
     * @param age 年龄
     * @return 年龄分类字符串
     */
    private static String getAgeCategory(Integer age) {
        if (Objects.isNull(age)) {
            return "年龄未知";
        }
        
        if (age < 18) {
            return "未成年";
        } else if (age < 60) {
            return "成年";
        } else {
            return "老年";
        }
    }

    /**
     * 验证用户输入
     *
     * @param input 用户输入
     */
    private static void validateUserInput(String input) {
        Objects.requireNonNull(input, "用户输入不能为空");
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("用户输入不能为空白字符串");
        }
        System.out.println("用户输入验证通过: " + input);
    }

    /**
     * 使用 Optional 处理可能为空的值
     *
     * @param value 可能为空的值
     * @return 处理后的字符串
     */
    private static String getOptionalValue(String value) {
        return Objects.equals(value, null) ? "默认值" : value;
    }

    /**
     * 人员类 - 用于演示自定义对象的相等性比较
     */
    private static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = Objects.requireNonNull(name, "姓名不能为空");
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return Objects.toString(this, "Person{null}");
        }
    }

    /**
     * 产品类 - 演示构造函数中的非空检查
     */
    private static class Product {
        private final String name;
        private final double price;

        public Product(String name, double price) {
            this.name = Objects.requireNonNull(name, "产品名称不能为空");
            if (price < 0) {
                throw new IllegalArgumentException("产品价格不能为负数");
            }
            this.price = price;
            System.out.println("创建产品: " + this);
        }

        @Override
        public String toString() {
            return String.format("Product{name='%s', price=%.2f}", name, price);
        }
    }
}
