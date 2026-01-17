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
            System.out.printf("随机数 [%d]: %d", i, random.nextInt(RANDOM_BOUND));
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
            System.out.printf("随机数 [%d]: %d", i, randomValue);
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
            System.out.printf("随机数 [%d]: %d", i, randomValue);
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
}
