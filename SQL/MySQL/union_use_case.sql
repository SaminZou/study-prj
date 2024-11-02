-- 聚合几个相同的结果

select id,name from user where id = 1 union
select id,name from user where id = 2 union
select id,name from user where id = 3
