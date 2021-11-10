package template;

/**
 * 批处理骨架
 *
 * @author samin
 * @date 2021-11-10
 */
public class BatchProcess {

    public static void main(String[] args) {
        // 初始化数据
        int offset = 0;
        int doneNum = 0;

        // 需要处理的数据总量
        int count = 2751;
        // 批次处理频率
        int batchNum = 50;

        System.out.println("需要处理的数据总量：" + count);
        while (count - 50 > 0) {
            // 逻辑处理
            System.out.printf("sql：offset %s limit %s \n", offset, batchNum);

            // 计数更新
            offset += batchNum;
            count -= batchNum;
            doneNum += batchNum;
            System.out.println("已处理：" + doneNum);
        }

        if (count > 0) {
            // 逻辑处理
            System.out.printf("sql：offset %s limit %s \n", offset, batchNum);

            System.out.printf("开始 %s \n", offset);
            // 计数更新
            doneNum += count;
            System.out.println("已处理：" + doneNum);
        }
    }
}
