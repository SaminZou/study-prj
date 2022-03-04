```yaml
title: MySQL 与 PostgreSQL 比较
author: samin
date: 2021-02-11
```

# 背景

PostgreSQL是针对事务型企业应用的严肃、功能完善的数据库，支持强ACID特性和很多数据完整性检查。MySQL更加灵活，提供了更多选项来针对不同的任务进行裁剪。

# 问题

如果打算为项目选择一款免费、开源的数据库，那么你可能会在MySQL与PostgreSQL之间犹豫不定。MySQL与PostgreSQL都是免费、开源、强大、且功能丰富的数据库。

哪一个才是最好的开源数据库，MySQL还是PostgreSQL呢？该选择哪一个开源数据库呢？

在选择数据库时，你所做的是个长期的决策，因为后面如果再改变决定将是非常困难且代价高昂的。你希望一开始就选择正确。两个流行的开源数据库MySQL与PostgreSQL常常成为最后要选择的产品。对这两个开源数据库的高层次概览将会有助于你选择最适合自己需要的。

# MySQL

MySQL相对来说比较年轻，首度出现在1994年。它声称自己是最流行的开源数据库。MySQL就是LAMP（用于Web开发的软件包，包括 Linux、Apache及Perl/PHP/Python）中的M。构建在LAMP栈之上的大多数应用都会使用MySQL，包括那些知名的应用，如 
WordPress、Drupal、Zend及phpBB等。

一开始，MySQL的设计目标是成为一个快速的Web服务器后端，使用快速的索引序列访问方法（ISAM），不支持ACID。经过早期快速的发展之 后，MySQL开始支持更多的存储引擎，并通过InnoDB引擎实现了ACID。MySQL还支持其他存储引擎，提供了临时表的功能（使用MEMORY存 储引擎），通过MyISAM引擎实现了高速读的数据库，此外还有其他的核心存储引擎与第三方引擎。

MySQL的文档非常丰富，有很多质量不错的免费参考手册、图书与在线文档，还有来自于Oracle和第三方厂商的培训与支持。

MySQL近几年经历了所有权的变更和一些颇具戏剧性的事件。它最初是由MySQL AB开发的，然后在2008年以10亿美金的价格卖给了Sun公司，Sun公司又在2010年被Oracle收购。Oracle支持MySQL的多个版 本：Standard、Enterprise、Classic、Cluster、Embedded与Community。其中有一些是免费下载的，另外一 些则是收费的。其核心代码基于GPL许可，对于那些不想使用GPL许可的开发者与厂商来说还有商业许可可供使用。现在，基于最初的MySQL代码还有更多的数据库可供选择，因为几个核心的MySQL开发者已经发布了MySQL分支。最初的MySQL创建者之一 Michael “Monty” Widenius貌似后悔将MySQL卖给了Sun公司，于是又开发了他自己的MySQL分支MariaDB，它是免费的，基于GPL许可。知名的 MySQL开发者Brian Aker所创建的分支Drizzle对其进行了大量的改写，特别针对多CPU、云、网络应用与高并发进行了优化。

# PostgreSQL

PostgreSQL标榜自己是世界上最先进的开源数据库。PostgreSQL的一些粉丝说它能与Oracle相媲美，而且没有那么昂贵的价格和傲慢的客服。它拥有很长的历史，最初是1985年在加利福尼亚大学伯克利分校开发的，作为Ingres数据库的后继。

PostgreSQL是完全由社区驱动的开源项目，由全世界超过1000名贡献者所维护。它提供了单个完整功能的版本，而不像MySQL那样提供了 多个不同的社区版、商业版与企业版。PostgreSQL基于自由的BSD/MIT许可，组织可以使用、复制、修改和重新分发代码，只需要提供一个版权声明即可。

可靠性是PostgreSQL的最高优先级。它以坚如磐石的品质和良好的工程化而闻名，支持高事务、任务关键型应用。PostgreSQL的文档非 常精良，提供了大量免费的在线手册，还针对旧版本提供了归档的参考手册。PostgreSQL的社区支持是非常棒的，还有来自于独立厂商的商业支持。

数据一致性与完整性也是PostgreSQL的高优先级特性。PostgreSQL是完全支持ACID特性的，它对于数据库访问提供了强大的安全性 保证，充分利用了企业安全工具，如Kerberos与OpenSSL等。你可以定义自己的检查，根据自己的业务规则确保数据质量。

在众多的管理特性 中，point-in-time recovery（PITR）是非常棒的特性，这是个灵活的高可用特性，提供了诸如针对失败恢复创建热备份以及快照与恢复的能力。但这并不是 PostgreSQL的全部，项目还提供了几个方法来管理PostgreSQL以实现高可用、负载均衡与复制等，这样你就可以使用适合自己特定需求的功能了。

# 平台

MySQL与PostgreSQL都出现在一些高流量的Web站点上

- MySQL：Slashdot、Twitter、Facebook与Wikipedia
- PostgreSQL：Yahoo使用了一个修改的PostgreSQL数据库来处理每天数以亿计的事件，还有Reddit和Disqus

MySQL与PostgreSQL都能运行在多个操作系统上，如Linux、Unix、Mac OS X与Windows。他们都是开源、免费的，因此测试他们时的唯一代价就是你的时间与硬件。他们都很灵活且具有可伸缩性，可用在小型系统和大型分布式系统上。

MySQL在一个领域上要比PostgreSQL更进一步，那就是它的触角延伸到了嵌入式领域，这是通过libmysqld实现的。PostgreSQL不支持嵌入式应用，依然坚守在传统的客户端/服务器架构上。

MySQL通常被认为是针对网站与应用的快速数据库后端，能够进行快速的读取和大量的查询操作，不过在复杂特性与数据完整性检查方面不太尽如人意。

PostgreSQL是针对事务型企业应用的严肃、功能完善的数据库，支持强ACID特性和很多数据完整性检查。他们二者都在某些任务上具有很快的速度，MySQL不同存储引擎的行为有较大差别。

MyISAM引擎是最快的，因为它只执行很少的数据完整性检查，适合于后端读操作较多的站点，不过对于包含 敏感数据的读/写数据库来说就是个灾难了，因为MyISAM表最终可能会损坏。MySQL提供了修复MySQL表的工具，不过对于敏感数据来说，支持 ACID特性的InnoDB则是个更好的选择。

与之相反，PostgreSQL则是个只有单一存储引擎的完全集成的数据库。你可以通过调整postgresql.conf文件的参数来改进性能，也可以调整查询与事务。PostgreSQL文档对于性能调优提供了非常详尽的介绍。

MySQL与PostgreSQL都是高可配置的，并且可以针对不同的任务进行相应的优化。他们都支持通过扩展来添加额外的功能。

一个常见的误解就是MySQL要比PostgreSQL更容易学习。关系数据库系统都是非常复杂的，这两个数据库的学习曲线其实是差不多的。

# 标准兼容性

PostgreSQL旨在实现SQL兼容性（当前标准是ANSI-SQL:2008）。MySQL则兼容大部分SQL，不过还有自己的扩展，可以支 持NoSQL特性，这在参考手册中都有介绍。每种方式都有优缺点。兼容标准会让数据库管理员、数据库开发者与应用开发者更舒服一些，因为这意味着他们只需学习一套标准、一套特性和命令即可。这会节省时间，提升效率，也不会被锁定在特定的厂商上。

支持使用非标准的自定义功能的人们认为这样可以快速采用新的特性，而不必等待标准进程完成。ANSI/ISO标准在不断演化，因此标准兼容性也是个 变化的目标：知名的关系型数据库Microsoft SQL Server、Oracle与IBM DB2也只是部分兼容于标准。

# 结论

虽然有不同的历史、引擎与工具，不过并没有明确的参考能够表明这两个数据库哪一个能够适用于所有情况。很多组织喜欢使用PostgreSQL，因为 它的可靠性好，在保护数据方面很擅长，而且是个社区项目，不会陷入厂商的牢笼之中。

MySQL更加灵活，提供了更多选项来针对不同的任务进行裁剪。很多时 候，对于一个组织来说，对某个软件使用的熟练程度要比特性上的原因更重要。

# PostgreSQL相对于MySQL的优势

1. 在SQL的标准实现上要比MySQL完善，而且功能实现比较严谨
   
2. 存储过程的功能支持要比MySQL好，具备本地缓存执行计划的能力
   
3. 对表连接支持较完整，优化器的功能较完整，支持的索引类型很多，复杂查询能力较强
   
4. PG主表采用堆表存放，MySQL采用索引组织表，能够支持比MySQL更大的数据量
   
5. PG的主备复制属于物理复制，相对于MySQL基于binlog的逻辑复制，数据的一致性更加可靠，复制性能更高，对主机性能的影响也更小
   
6. MySQL的存储引擎插件化机制，存在锁机制复杂影响并发的问题，而PG不存在

# MySQL相对于PG的优势

1. innodb的基于回滚段实现的MVCC机制，相对PG新老数据一起存放的基于XID的MVCC机制，是占优的。新老数据一起存放，需要定时触 发VACUUM，会带来多余的IO和数据库对象加锁开销，引起数据库整体的并发能力下降。而且VACUUM清理不及时，还可能会引发数据膨胀
   
2. MySQL采用索引组织表，这种存储方式非常适合基于主键匹配的查询、删改操作，但是对表结构设计存在约束
   
3. MySQL的优化器较简单，系统表、运算符、数据类型的实现都很精简，非常适合简单的查询操作
   
4. MySQL分区表的实现要优于PG的基于继承表的分区实现，主要体现在分区个数达到上千上万后的处理性能差异较大
   
5. MySQL的存储引擎插件化机制，使得它的应用场景更加广泛，比如除了innodb适合事务处理场景外，myisam适合静态数据的查询场景

# 总结

开源数据库都不是很完善，商业数据库oracle在架构和功能方面都还是完善很多的。从应用场景来说，PG更加适合严格的企业应用场景（比如金融、电信、ERP、CRM），而MySQL更加适合业务逻辑相对简单、数据可靠性要求较低的互联网场景（比如google、facebook、alibaba）。

# MySQL 与 PostgreSQL 比较

PostgreSQL和MySQL的差别，我搜索了关键字：MySQL vs PostgreSQL，

MySQL与PostgreSQL的区别MySQL是应用开发者创建出来的DBMS；而PostgreSQL是由数据库开发者创建出来的DBMS
  
换句话说，MySQL倾向于使用者的角度，回答的问题是 “你想解决的是什么问题”；而PostgreSQL倾向于理论角度，回答的问题是 “数据库应该如何来解决问题” 

MySQL一般会将数据合法性验证交给客户；PostgreSQL在合法性难方面做得比较严格。比如MySQL里插入 “2012-02-30” 这个时间时，会成功，但结果会是 “0000-00-00”；PostgreSQL不允许插入此值。

通常，PostgreSQL 被认为特性丰富，而MySQL被认为速度更快。但这个观点基本是在 MySQL 4.x / PostgreSQL 7.x 的事情，现在情况已经变了，PostgreSQL 在9.x版本速度上有了很大的改进，而MySQL特性也在增加。

在架构上，MySQL分为两层：上层的SQL层和几个存储引擎（比如InnoDB，MyISAM）。PostgreSQL 只有一个存储引擎提供这两个功能。

这两个数据库系统都可以针对应用的情境被优化、定制，精确的说哪个性能更好很难。MySQL项目一开始焦点就在速度上，而PostgreSQL一开始焦点在特性和规范标准上。

可能是由于历史原因MySQL在开发者中更流行一些。至少我们上学时没听说过PostgreSQL，当时不是MS SQL Server就是MySQL，而MySQL是开源的。实事上PostgreSQL直到8.0才官方支持了Windows系统。

如果没有什么历史原因（比如系统已经基于MySQL多年了），或技术积累原因（同事中MySQL高手多），那么我觉得选择PostgreSQL不会有错。

有趣的是，我在Google上搜索 “switch postgresql to mysql” 时，结果中第一页全是 “Switch to PostgreSQL from MySQL”，第二页终于有个是from PostgreSQL to MySQL，不过只有它一个，而且原因不是说PostgreSQL不好，而是因为作者MySQL经验多些。
