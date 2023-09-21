```yaml
title: JDK8 跟闰年有关的问题
author: samin
date: 2021-06-10
```

# 背景

Java8 提供了非常便利的时间类

# 题目

用 `LocalDate` 声明 2020年2月29 的时间实例，加一年和减一年的时间分别是哪天，会报错吗 ？

# 参考答案

```java
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate date = LocalDate.of(2020, 2, 29);
// 2020-02-28
System.out.println(dtf.format(date.plusYears(1)));
// 2021-02-28
System.out.println(dtf.format(date.plusYears(1)));
// 2019-02-28
System.out.println(dtf.format(date.minusYears(1)));
```

# 进阶

如果是初始化 2020年3月32日呢？输出是什么

```java
// 报错
LocalDate date2 = LocalDate.of(2020, 3, 32);
System.out.println(dtf.format(date2));
```