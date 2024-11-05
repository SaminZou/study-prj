-- 假设数据表 project_device 有一个 datetime 类型字段 update_time(对应 LocalDateTime)，新建了一个年月日 create_date 字段(对应 LocalDateTime)

-- 使用以下 SQL 完成字段数据填充
```SQL
UPDATE project_device
SET create_date = DATE(update_time);
```