package algorithms;

/**
 * 爬楼梯
 *
 * <p> 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * <p> 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢
 *
 * @author samin
 * @date 2021-01-11
 */
public class ClimbStairs {

    public static void main(String[] args) {
        // 2
        System.out.println(new ClimbStairs().climbStairs(2));
        // 3
        System.out.println(new ClimbStairs().climbStairs(3));
    }

    /**
     * 使用动态规划法，得出结论为斐波那契数列
     *
     * @param n 需要到底的层数
     * @return 结果
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        // a 表示第n-2个台阶的走法，b表示第n-1个台阶的走法,传统迭代
        int a = 1, b = 2;

        int count = 0;

        for (int i = 3; i <= n; i++) {
            // 累加结果
            count = a + b;
            // 向下迭代，下次迭代的第 n-2 个台阶的走法等于上次迭代 n-1 个台阶的走法
            a = b;
            // 下次迭代的第 n-1 个台阶的走法等于上次迭代的第 n 个台阶走法
            b = count;
        }

        return count;
    }
}