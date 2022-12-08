```yaml
title: TDengine
author: samin
date: 2022-12-08
```

# 文档

[官方文档](https://docs.tdengine.com/)

# Concepts 概念

- Metric 度量

由传感器上报，随时间发生变化的数据

- Label / Tag 标签

static properties of sensors, equipment or other types of data collection devices, which do not change with time, such as device model, color, fixed location of the device, etc

- Data Collection Point （DCP） 数据采集点

Data Collection Point (DCP) refers to hardware or software that collects metrics based on preset time periods or triggered by events. A data collection point can collect one or multiple metrics, but these metrics are collected at the same time and have the same timestamp. For some complex equipment, there are often multiple data collection points, and the sampling rate of each collection point may be different, and fully independent.

For example, for a car, there could be a data collection point to collect GPS position metrics, a data collection point to collect engine status metrics, and a data collection point to collect the environment metrics inside the car. So in this example the car would have three data collection points. In the smart meters example, d1001, d1002, d1003, and d1004 are the data collection points.

- Table 表

To make full use of time-series data characteristics, TDengine adopts a strategy of **"One Table for One Data Collection Point"**. TDengine requires the user to create a table for each data collection point (DCP) to store collected time-series data. For example, if there are over 10 million smart meters, it means 10 million tables should be created.

In addition, the first column in the table must be a timestamp. TDengine uses the timestamp as the index, and won’t build the index on any metrics stored. Column wise storage is used.

- Super Table (STable) 超级表

STable is a template for a type of data collection point. A STable contains a set of data collection points (tables) that have the same schema or data structure, but with different static attributes (tags).

- SubTable 子表

The table created by using a STable as the template is called subtable in TDengine. The difference between regular table and subtable is:

1. **Subtable is a table**, all SQL commands applied on a regular table can be applied on subtable.
2. Subtable is a table with extensions, it has static tags (labels), and these tags can be added, deleted, and updated after it is created. But a regular table does not have tags.
3. A subtable belongs to only one STable, but a STable may have many subtables. Regular tables do not belong to a STable.
4. A regular table can not be converted into a subtable, and vice versa.

The relationship between a STable and the subtables created based on this STable is as follows:

1. A STable contains multiple subtables with the same metric schema but with different tag values.
2. The schema of metrics or labels cannot be adjusted through subtables, and it can only be changed via STable. Changes to the schema of a STable takes effect immediately for all associated subtables.
3. STable defines only one template and does not store any data or label information by itself. **Therefore, data cannot be written to a STable, only to subtables**.

![度量、标签、超级表、子表](https://docs.tdengine.com/assets/images/supertable-f2896ade8b7eeb2694f5c23efb6ed9cc.webp)

- Database 数据库

The characteristics of time-series data from different data collection points may be different. Characteristics include collection frequency, retention policy and others which determine how you create and configure the database. For e.g. days to keep, number of replicas, data block size, whether data updates are allowed and other configurable parameters would be determined by the characteristics of your data and your business requirements. In order for TDengine to work with maximum efficiency in various scenarios, TDengine recommends that STables with different data characteristics be created in different databases.

- FQDN & End Point

FQDN (Fully Qualified Domain Name) is the full domain name of a specific computer or host on the Internet.

FQDN consists of two parts: hostname and domain name. For example, the FQDN of a mail server might be mail.tdengine.com. The hostname is mail, and the host is located in the domain name tdengine.com.

Each node of a TDengine cluster is uniquely identified by an End Point, which consists of an FQDN and a Port, such as h1.tdengine.com:6030. In this way, when the IP changes, we can still use the FQDN to dynamically find the node without changing any configuration of the cluster. In addition, FQDN is used to facilitate unified access to the same cluster from the Intranet and the Internet.

# 运维

## TDengine 实例

- dnode（Data node）

- vnode（Virtual node）

- VGroup（Virtual node group）

- mnode（Management node）

![](https://www.taosdata.com/docs/user/pages/01.en/v2.0/images/architecture/structure.png)

## FQDN 相关

/etc/hosts

/etc/taos/taos.cfg

## 导入导出

taosdump

## TDengine CLI

$ show dnodes;

$ show mnodes;

$ use <database_name>;    
$ show vgroups;

# 总结

时序数据库提示一些规范必须遵守，如一个设备实例一个表，区分度量和标签等，这些也是快速写入和查询的前提。