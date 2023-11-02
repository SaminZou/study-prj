package template;

/**
 * 批处理骨架
 *
 * @author samin
 * @date 2021-11-10
 */
public class BatchProcess {

    // 初始化数据
    private static int OFFSET = 0;
    private static int DONE_COUNT = 0;
    // 批次处理频率
    private static int BATCH_NUM = 50;

    public static void main(String[] args) {
        new BatchProcess().batch(2751);
    }

    /**
     * 批处理方法
     *
     * @param count 需要处理的数据总量
     */
    public void batch(int count) {
        System.out.println("需要处理的数据总量：" + count);
        while (count - 50 > 0) {
            // TODO 业务代码

            System.out.printf("sql：offset %s limit %s \n", OFFSET, BATCH_NUM);

            // 计数更新
            OFFSET += BATCH_NUM;
            count -= BATCH_NUM;
            DONE_COUNT += BATCH_NUM;
            System.out.println("已处理：" + DONE_COUNT);
        }

        // 处理最后一批次
        if (count > 0) {
            // TODO 业务代码

            System.out.printf("sql：offset %s limit %s \n", OFFSET, BATCH_NUM);

            System.out.printf("开始 %s \n", OFFSET);
            // 计数更新
            DONE_COUNT += count;

            System.out.println("已处理：" + DONE_COUNT);
        }
    }
}