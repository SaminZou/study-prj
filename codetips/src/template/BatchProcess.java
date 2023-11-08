package template;

/**
 * 批处理骨架
 *
 * @author samin
 * @date 2021-11-10
 */
public class BatchProcess {

    // 批次处理频率
    private final static int BATCH_NUM = 50;

    public static void main(String[] args) {
        new BatchProcess().batch(2751);
    }

    /**
     * 批处理方法
     *
     * @param count 需要处理的数据总量
     */
    public void batch(int count) {
        // 初始化数据
        int offset = 0;
        int doneCount = 0;

        System.out.println("需要处理的数据总量：" + count);
        while (count - 50 > 0) {
            // TODO 业务代码

            System.out.printf("sql：offset %s limit %s \n", offset, BATCH_NUM);

            // 计数更新
            offset += BATCH_NUM;
            count -= BATCH_NUM;
            doneCount += BATCH_NUM;
            System.out.println("已处理：" + doneCount);
        }

        // 处理最后一批次
        if (count > 0) {
            // TODO 业务代码

            System.out.printf("sql：offset %s limit %s \n", offset, BATCH_NUM);

            System.out.printf("开始 %s \n", offset);
            // 计数更新
            doneCount += count;

            System.out.println("已处理：" + doneCount);
        }
    }
}