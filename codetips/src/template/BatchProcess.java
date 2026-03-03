package template;

import java.util.ArrayList;
import java.util.List;

/**
 * 批处理骨架 - 优化版本
 *
 * 提供常见的批处理场景示例：
 * 1. 数据库批量处理
 * 2. 文件批量处理
 * 3. API批量调用
 * 4. 数据导入导出
 *
 * 优化点：
 * - 修复了批次处理逻辑
 * - 添加了异常处理和日志
 * - 提供了多种常见场景示例
 * - 改进了代码可读性
 *
 * @author samin
 * @date 2021-11-10
 * @version 2.0
 */
public class BatchProcess {

    /**
     * 默认批次大小
     */
    private static final int DEFAULT_BATCH_SIZE = 50;

    /**
     * 批次处理模式枚举
     */
    public enum ProcessMode {
        DATABASE,
        FILE,
        API,
        DATA_IMPORT
    }

    /**
     * 批次处理配置
     */
    public static class BatchConfig {

        private final int totalCount;
        private final int batchSize;
        private final ProcessMode mode;

        public BatchConfig(int totalCount, int batchSize, ProcessMode mode) {
            this.totalCount = totalCount;
            this.batchSize = batchSize;
            this.mode = mode;
        }

        public int getTotalCount() {return totalCount;}

        public int getBatchSize() {return batchSize;}

        public ProcessMode getMode() {return mode;}
    }

    public static void main(String[] args) {
        // 演示不同场景的批处理
        BatchProcess processor = new BatchProcess();

        System.out.println("=== 数据库批处理示例 ===");
        processor.batchProcess(new BatchConfig(2751, 50, ProcessMode.DATABASE));

        System.out.println("\n=== 文件批处理示例 ===");
        processor.batchProcess(new BatchConfig(1000, 100, ProcessMode.FILE));

        System.out.println("\n=== API批处理示例 ===");
        processor.batchProcess(new BatchConfig(500, 10, ProcessMode.API));
    }

    /**
     * 通用的批处理方法
     *
     * @param config 批处理配置
     */
    public void batchProcess(BatchConfig config) {
        int totalCount = config.getTotalCount();
        int batchSize = config.getBatchSize();

        System.out.println("开始批处理 - 模式: " + config.getMode() + ", 总量: " + totalCount + ", 批次大小: " + batchSize);

        long startTime = System.currentTimeMillis();
        int processedCount = 0;
        int batchNumber = 0;

        for (int offset = 0; offset < totalCount; offset += batchSize) {
            batchNumber++;
            int currentBatchSize = Math.min(batchSize, totalCount - offset);

            try {
                // 根据模式执行不同的处理逻辑
                boolean success = processBatch(config.getMode(), offset, currentBatchSize, batchNumber);

                if (success) {
                    processedCount += currentBatchSize;
                    System.out.printf("批次 %d 处理成功: 偏移量 %d, 数量 %d, 累计处理 %d/%d\n", batchNumber, offset,
                                      currentBatchSize, processedCount, totalCount);
                } else {
                    System.err.printf("批次 %d 处理失败: 偏移量 %d, 数量 %d\n", batchNumber, offset, currentBatchSize);
                    // 可以根据需要决定是否继续处理
                    // break;
                }

                // 模拟处理延迟（实际使用时删除）
                Thread.sleep(10);

            } catch (Exception e) {
                System.err.printf("批次 %d 处理异常: %s\n", batchNumber, e.getMessage());
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("批处理完成! 总耗时: %dms, 成功处理: %d/%d\n", (endTime - startTime), processedCount, totalCount);
    }

    /**
     * 处理单个批次
     *
     * @param mode 处理模式
     * @param offset 偏移量
     * @param batchSize 批次大小
     * @param batchNumber 批次号
     * @return 处理是否成功
     */
    private boolean processBatch(ProcessMode mode, int offset, int batchSize, int batchNumber) {
        switch (mode) {
            case DATABASE:
                return processDatabaseBatch(offset, batchSize, batchNumber);
            case FILE:
                return processFileBatch(offset, batchSize, batchNumber);
            case API:
                return processApiBatch(offset, batchSize, batchNumber);
            case DATA_IMPORT:
                return processDataImportBatch(offset, batchSize, batchNumber);
            default:
                System.err.println("未知的处理模式: " + mode);
                return false;
        }
    }

    /**
     * 数据库批次处理示例
     */
    private boolean processDatabaseBatch(int offset, int batchSize, int batchNumber) {
        // 示例：数据库查询和处理
        String sql = String.format("SELECT * FROM users LIMIT %d OFFSET %d", batchSize, offset);
        System.out.printf("批次 %d - 执行SQL: %s\n", batchNumber, sql);

        // 具体业务逻辑代码
        return true;
    }

    /**
     * 文件批次处理示例
     */
    private boolean processFileBatch(int offset, int batchSize, int batchNumber) {
        // 示例：文件分批读取和处理
        System.out.printf("批次 %d - 读取文件偏移量 %d, 处理 %d 行\n", batchNumber, offset, batchSize);

        // 具体业务逻辑代码

        return true;
    }

    /**
     * API批次处理示例
     */
    private boolean processApiBatch(int offset, int batchSize, int batchNumber) {
        // 示例：API批量调用
        System.out.printf("批次 %d - 调用API处理 %d 条记录\n", batchNumber, batchSize);

        // 具体业务逻辑代码

        return true;
    }

    /**
     * 数据导入批次处理示例
     */
    private boolean processDataImportBatch(int offset, int batchSize, int batchNumber) {
        // 示例：数据导入处理
        System.out.printf("批次 %d - 导入数据偏移量 %d, 数量 %d\n", batchNumber, offset, batchSize);

        // 具体业务逻辑代码

        return true;
    }

    /**
     * 旧的批处理方法（保持向后兼容）
     *
     * @param count 需要处理的数据总量
     */
    public void batch(int count) {
        System.out.println("=== 使用旧版批处理方法 ===");
        batchProcess(new BatchConfig(count, DEFAULT_BATCH_SIZE, ProcessMode.DATABASE));
    }

    // 辅助方法示例（可根据需要实现）

    /**
     * 获取批次数据（示例方法）
     */
    private List<String> getBatchData(int offset, int batchSize) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < batchSize; i++) {
            data.add("Record " + (offset + i));
        }
        return data;
    }

    /**
     * 处理用户记录（示例方法）
     */
    private void processUserRecord(Object resultSet) {
        // 实际处理逻辑
    }

    /**
     * 处理文件行（示例方法）
     */
    private void processFileLine(String line) {
        // 实际处理逻辑
    }

    /**
     * 获取导入数据（示例方法）
     */
    private List<Object> fetchImportData(int offset, int batchSize) {
        return new ArrayList<>();
    }

    /**
     * 数据记录类（示例）
     */
    private static class DataRecord {

        private String field1;
        private String field2;
        private String field3;

        // getters and setters
        public String getField1() {return field1;}

        public String getField2() {return field2;}

        public String getField3() {return field3;}
    }
}