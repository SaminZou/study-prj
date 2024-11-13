-- 窗口函数

-- 根据 create_date 聚合 device_nums，统计每一天的设备总数
SELECT DISTINCT create_date,
                SUM(device_nums) OVER (
           PARTITION BY create_date
       ) AS device_nums
FROM project_device
ORDER BY create_date DESC
;