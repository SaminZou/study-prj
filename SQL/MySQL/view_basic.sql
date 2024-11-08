-- 表 device 本来只有 project_id、device_nums，多个 project_id 可能归属一个 group，根据 group_id 分组创建试图

CREATE VIEW group_device AS
SELECT
    group_id,
    SUM(device_nums) device_nums
FROM
    device
GROUP BY
    group_id;