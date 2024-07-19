-- 创建一个账户表
create table `account` (
                           `id` int(11) not null auto_increment,
                           `name` varchar(255) default null,
                           `balance` int(11) default null,
                           primary key (`id`)
) engine=innodb default charset=utf8;
insert into `account` (`name`, `balance`) values ('user1', '0');
insert into `account` (`name`, `balance`) values ('user2', '2000');
insert into `account` (`name`, `balance`) values ('user3', '400');

-- 在实际演示中，可以通过开启两个 session 连接，分别设置相同的隔离级别来查看效果，演示核心是分别切换窗口，通过开启事务不要进行 commit 操作来控制效果展示

-- 读未提交
-- session A
set tx_isolation = 'read-uncommitted';

begin;

select * from account;

update account set balance = balance + 100 where id = 1;

-- 有可能执行 rollback;

-- session B

set tx_isolation = 'read-committed';

begin;

-- 已经查询到 id 为 1 的记录账户为 100，这就是脏读现象，这时候如果继续对查询结果进行处理，而 session A 进行了 rollback 回滚，就会产生脏数据
select * from account;

-- 读已提交

-- 已经解决脏读

-- session A
set tx_isolation = 'read-committed';

begin;

select * from account;

update account set balance = balance + 100 where id = 1;

commit;

-- session B

set tx_isolation = 'read-committed';

begin;

-- session A commit 前查询结果为 0
select * from account where id = 1;

-- session A commit 后查询结果为 100，这就是不可重复读现象，在同一个事务中对同一个记录查询的结果不一样，会导致业务出错
select * from account where id = 1;

-- 可重复读

-- 已经解决不可重复读

-- session A
set tx_isolation = 'repeatable-read';

begin;

insert into `account` (`name`, `balance`) values ('user4', '0');

-- session B

set tx_isolation = 'repeatable-read';

begin;

-- 查询结果为 3 条数据
select * from account where id = 1;

-- 可以正常执行，更新了对当前事务来说不存在的记录，这种现象就是幻读
update account set balance = 100 where id = 4;

-- 串行

-- 解决幻读

set tx_isolation = 'serializable';