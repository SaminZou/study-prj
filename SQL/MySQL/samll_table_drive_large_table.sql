‐‐ 示例表
create table `t1` (
                      `id` int(11) not null auto_increment,
                      `a` int(11) default null,
                      `b` int(11) default null,
                      primary key (`id`),
                      key `idx_a` (`a`)
) engine=innodb default charset=utf8;

create table t2 like t1;

‐‐ 插入一些示例数据
‐‐ 往t1表插入1万行记录
drop procedure if exists insert_t1;
create procedure insert_t1()
begin
declare i int;
set i=1;
while(i<=10000)do
insert into t1(a,b) values(i,i);
set i=i+1;
end while;
end;;

call insert_t1();

‐‐ 往t2表插入100行记录
drop procedure if exists insert_t2;

create procedure insert_t2()
begin
declare i int;
set i=1;
while(i<=100)do
insert into t2(a,b) values(i,i);
set i=i+1;
end while;
end;;

call insert_t2();

-- 查询优化器自动选择使用小表驱动大表
explain select * from t1 inner join t2 on t1.a = t2.a;
explain select * from t2 inner join t1 on t1.a = t2.a;