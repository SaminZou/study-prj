-- 跨库联表查询
select p.id,
       p.name
from iot_device.device d
         left join iot_base.project p on
    d.project_id = p.id
where d.key = 'iot_sensor_1001';
