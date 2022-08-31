package puzzle.q6;

/**
 * 代码性能统计
 *
 * @author samin
 * @date 2021-01-29
 */
public class TimeSpendStatisticsUseCase {

    /**
     * 1. 时间差统计
     */
    public static long diff1(long startTimestamp, long endTimestamp) {
        return endTimestamp - startTimestamp;
    }

    // 2. StopWatch
    // 可以参考 org.springframework.util.StopWatch

    // 3. Function
    // 更上一层的封装

    // 4. AutoCloseable
    // JDK1.7 自动关闭资源特性 需要重写 close() 方法

    // 2、3、4方式本质上和“时间差统计”是一致的，只是抽取了一层，稍微优雅了一点
}
