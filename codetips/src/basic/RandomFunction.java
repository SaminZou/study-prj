package basic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 获取随机数示例
 * <p>
 * 演示 Java 中生成随机数的多种方式：
 * <ul>
 *   <li>Random 类：无参构造（真随机）和有参构造（伪随机，固定种子）</li>
 *   <li>Math.random()：生成 [0.0, 1.0) 的双精度随机数</li>
 *   <li>ThreadLocalRandom：多线程环境下的高性能随机数生成器</li>
 * </ul>
 *
 * @author samin
 * @date 2021-01-10
 */
public class RandomFunction {

    private static final int RANDOM_COUNT = 10;
    private static final int MATH_RANDOM_COUNT = 3;
    private static final int RANDOM_BOUND = 100;
    private static final int MATH_RANDOM_MULTIPLIER = 10;
    private static final long SEED_VALUE = 100L;

    public static void main(String[] args) {
        demonstrateRandomClass();
        demonstrateRandomWithSeed();
        demonstrateMathRandom();
        demonstrateThreadLocalRandom();
        demonstrateRandomRange();
        demonstrateRandomBoolean();
        demonstrateRandomFloat();
        demonstrateRandomChar();
        demonstrateRandomString();
    }

    /**
     * 演示 Random 类的无参构造方法
     * 每次运行生成不同的随机数序列
     */
    private static void demonstrateRandomClass() {
        System.out.println("=== Random 类无参构造（真随机） ===");
        Random random = new Random();
        for (int i = 0; i < RANDOM_COUNT; i++) {
            System.out.printf("随机数 [%d]: %d%n", i, random.nextInt(RANDOM_BOUND));
        }
    }

    /**
     * 演示 Random 类的有参构造方法（固定种子）
     * 无论运行多少次，只要种子相同，生成的随机数序列也相同
     * 这种方式不是真正意义上的随机数，称为伪随机数
     */
    private static void demonstrateRandomWithSeed() {
        System.out.println("=== Random 类有参构造（固定种子，伪随机） ===");
        Random random = new Random(SEED_VALUE);
        for (int i = 0; i < RANDOM_COUNT; i++) {
            System.out.printf("随机数 [%d]: %d%n", i, random.nextInt(RANDOM_BOUND));
        }
    }

    /**
     * 演示 Math.random() 方法
     * 生成 [0.0, 1.0) 区间的双精度随机数
     */
    private static void demonstrateMathRandom() {
        System.out.println("=== Math.random() 方法 ===");
        for (int i = 0; i < MATH_RANDOM_COUNT; i++) {
            int randomValue = (int) (Math.random() * MATH_RANDOM_MULTIPLIER);
            System.out.printf("随机数 [%d]: %d%n", i, randomValue);
        }
    }

    /**
     * 演示 ThreadLocalRandom 类
     * 多线程环境下的高性能随机数生成器
     * 避免 Random 类在多线程下的竞争问题
     */
    private static void demonstrateThreadLocalRandom() {
        System.out.println("=== ThreadLocalRandom 方法 ===");
        for (int i = 0; i < RANDOM_COUNT; i++) {
            int randomValue = ThreadLocalRandom.current()
                                               .nextInt(RANDOM_BOUND);
            System.out.printf("随机数 [%d]: %d%n", i, randomValue);
        }
    }

    /*
     * 随机数生成公式总结：
     *
     * (int)(Math.random() * n) 生成大于等于 0 小于 n 的随机数
     * (int)(Math.random() * n) + m 生成大于等于 m 小于 m+n 之间的随机数
     * (int)(Math.random() * (n - m) + m) 生成从 m 到 n 范围内的数，包含 m 不包含 n
     *
     * (char)('a' + Math.random() * ('z' - 'a' + 1)) 随机生成 a~z 之间的字符
     * (char)(cha1 + Math.random() * (cha2 - cha1 + 1)) 随机生成 cha1~cha2 的字符
     */

    /**
     * 演示生成指定范围的随机整数 [min, max]
     */
    private static void demonstrateRandomRange() {
        System.out.println("=== 指定范围随机数 [min, max] ===");
        Random random = new Random();
        int min = 10;
        int max = 20;
        for (int i = 0; i < 5; i++) {
            int randomValue = random.nextInt(max - min + 1) + min;
            System.out.printf("范围 [%d-%d] 随机数: %d%n", min, max, randomValue);
        }
    }

    /**
     * 演示生成随机布尔值
     */
    private static void demonstrateRandomBoolean() {
        System.out.println("=== 随机布尔值 ===");
        Random random = new Random();
        int trueCount = 0;
        int falseCount = 0;
        for (int i = 0; i < 10; i++) {
            boolean randomBool = random.nextBoolean();
            if (randomBool) {
                trueCount++;
            } else {
                falseCount++;
            }
            System.out.printf("随机布尔值 [%d]: %b%n", i, randomBool);
        }
        System.out.printf("统计: true=%d, false=%d%n", trueCount, falseCount);
    }

    /**
     * 演示生成随机浮点数
     */
    private static void demonstrateRandomFloat() {
        System.out.println("=== 随机浮点数 ===");
        Random random = new Random();

        // [0.0, 1.0) 之间的浮点数
        System.out.printf("[0.0, 1.0): %.4f%n", random.nextFloat());

        // [0.0, 1.0) 之间的双精度浮点数
        System.out.printf("[0.0, 1.0) double: %.6f%n", random.nextDouble());

        // 高斯分布（正态分布）随机数，均值为 0，标准差为 1
        System.out.printf("高斯分布: %.4f%n", random.nextGaussian());
    }

    /**
     * 演示生成随机字符
     */
    private static void demonstrateRandomChar() {
        System.out.println("=== 随机字符 ===");
        Random random = new Random();

        // 小写字母 a-z
        char lowercase = (char) (random.nextInt(26) + 'a');
        System.out.printf("小写字母: %c%n", lowercase);

        // 大写字母 A-Z
        char uppercase = (char) (random.nextInt(26) + 'A');
        System.out.printf("大写字母: %c%n", uppercase);

        // 数字 0-9
        char digit = (char) (random.nextInt(10) + '0');
        System.out.printf("数字字符: %c%n", digit);

        // 任意可打印 ASCII 字符 (32-126)
        char ascii = (char) (random.nextInt(95) + 32);
        System.out.printf("ASCII 字符: %c%n", ascii);
    }

    /**
     * 演示生成随机字符串（如随机密码）
     */
    private static void demonstrateRandomString() {
        System.out.println("=== 随机字符串 ===");
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";

        // 生成 8 位随机密码
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        System.out.printf("8 位随机密码: %s%n", password.toString());

        // 生成 16 位随机字符串（十六进制）
        StringBuilder hexString = new StringBuilder();
        String hexChars = "0123456789abcdef";
        for (int i = 0; i < 16; i++) {
            hexString.append(hexChars.charAt(random.nextInt(hexChars.length())));
        }
        System.out.printf("16 位十六进制字符串: %s%n", hexString.toString());
    }
}
