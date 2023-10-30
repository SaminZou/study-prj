# setnx 实现的分布式锁存在的问题

- `不可重入`：同一个线程无法多次获取同一把锁（eg：方法A调用方法B，在方法A中先获取锁，然后去调用方法B，方法B也需要获取同一把锁，这种情况下如果锁不可重入，方法B显然获取不到锁，会出现死锁的情况）

- `不可重试`：获取锁只尝试一次就返回 false，没有重试机制

- `超时释放`：超时释放虽然能够避免死锁，但如果业务执行执行时间较长导致锁释放，会存在安全隐患

- `主从一致性`
  ：主从数据同步存在延迟，比如：线程在主节点获取了锁，尚未同步给从节点时主节点宕机，此时会选一个从节点作为新的主节点，这个从节点由于没有完成同步不存在锁的标识，此时其他线程可以趁虚而入拿到锁，这就造成多个线程同时拿到锁，就会出现安全问题）

使用 Redisson 的分布式锁可以避免这些

# 1 术语

- 工作历

- 工作日和假期（workday / weekend）

- 节假日和补班（holiday / make-up workday）

> 休假（vacation）非通用

# 2 数据表设计

## 2.1 工作历表（system_holiday）

| 字段名          | 数据类型    | 长度  | 是否为空 | 默认值         | 字段说明                                                 |
|--------------|---------|-----|------|-------------|------------------------------------------------------|
| id           | int     |     | 否    | 自增长         | 主键                                                   |
| tcode        | varchar | 20  | 否    |             | 租户编码                                                 |
| pcode        | varchar | 20  | 否    |             | 园区编码                                                 |
| holidays     | text[]  |     | 是    | 格式 yyyyMMdd | 假期                                                   |
| workdays     | text[]  |     | 是    | 格式 yyyyMMdd | 工作日                                                  |
| holiday_type | int     |     | 否    |             | 假期类型:0元旦，1春节，2清明节，3劳动节，4端午节，5中秋节，6国庆节，7其他非工作日，8其他工作日 |
| remark       | varchar | 255 | 是    |             | 备注                                                   |

# 3 接口设计

## 3.1 查询节日

接口：/system/holiday/query

方法：POST

请求数据类型：application/json

## 3.2 创建节日

接口：/system/holiday/create

方法：POST

请求数据类型：application/json

入参：

| 参数名称        | 参数说明                                                 | 数据类型     | 是否必填  |
|-------------|------------------------------------------------------|----------|-------|
| tcode       | 租户编码                                                 | string   | true  |
| pcode       | 园区编码                                                 | string   | true  |
| holidays    | 假期                                                   | string[] | false |
| workdays    | 工作日                                                  | string[] | false |
| holidayType | 假期类型:0元旦，1春节，2清明节，3劳动节，4端午节，5中秋节，6国庆节，7其他非工作日，8其他工作日 | integer  | true  |
| remark      | 备注                                                   | string   | false |

## 3.3 更新节日

接口：/system/holiday/update

方法：POST

请求数据类型：application/json

入参：

| 参数名称        | 参数说明                                                 | 数据类型     | 是否必填  |
|-------------|------------------------------------------------------|----------|-------|
| tcode       | 租户编码                                                 | string   | true  |
| pcode       | 园区编码                                                 | string   | true  |
| holidays    | 假期                                                   | string[] | false |
| workdays    | 工作日                                                  | string[] | false |
| holidayType | 假期类型:0元旦，1春节，2清明节，3劳动节，4端午节，5中秋节，6国庆节，7其他非工作日，8其他工作日 | integer  | true  |
| remark      | 备注                                                   | string   | false |

## 3.4 删除节日

接口：/system/holiday/delete/{id}

方法：POST

请求数据类型：application/x-www-form-urlencoded

入参：

| 参数名称 | 参数说明 | 数据类型   | 是否必填 |
|------|------|--------|------|
| id   | 记录id | string | true |

> data 为标准查询返回

## 3.5 获取月度类型节假日配置

接口：/system/holiday/list/spec_time?specTime=

方法：POST

请求数据类型：application/x-www-form-urlencoded

入参：

| 参数名称     | 参数说明                  | 数据类型   | 是否必填 |
|----------|-----------------------|--------|------|
| specTime | 年份格式：yyyy，月份格式：yyyyMM | string | true |

响应 data 数据：

| 参数名称        | 参数说明                                                 | 数据类型     |
|-------------|------------------------------------------------------|----------|
| tcode       | 租户编码                                                 | string   |
| pcode       | 园区编码                                                 | string   |
| holidays    | 假期                                                   | string[] |
| workdays    | 工作日                                                  | string[] |
| holidayType | 假期类型:0元旦，1春节，2清明节，3劳动节，4端午节，5中秋节，6国庆节，7其他非工作日，8其他工作日 | integer  |
| remark      | 备注                                                   | string   |

## 3.6 获取年度假节日/工作日天数

接口：/system/holiday/get_days_statistics?specTime=

方法：POST

请求数据类型：application/x-www-form-urlencoded

入参：

| 参数名称     | 参数说明      | 数据类型   | 是否必填 |
|----------|-----------|--------|------|
| specTime | 年份格式：yyyy | string | true |

响应 data 数据：

| 参数名称           | 参数说明     | 数据类型    |
|----------------|----------|---------|
| holidayDaysNum | 年度非工作日天数 | integer |
| workDaysNum    | 年度工作日天数  | integer |