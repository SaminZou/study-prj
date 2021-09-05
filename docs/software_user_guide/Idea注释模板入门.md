```yaml
title: Idea注释模板入门
author: samin
date: 2021-09-05
```

# 类模板

## 入口

Settings -> Editor -> File and Code Templates -> Files -> Class(Interface、Enum)

```java
// 常用类模板1

/**
 * @author samin
 * @date ${YEAR}年${MONTH}月${DAY}日 ${TIME}
 */

// 常用类模板2

/**
 * <p>Title: ${NAME}</p>
 *
 * <p>Description: ${DESCRIPTION}</p>
 *
 * <p>Copyright: Copyright (c) ${USER} 2018</p>
 *
 * <p>Company: DAS</p>
 *
 * @author ${USER}
 * @version 1.0
 * @date ${YEAR}-${MONTH}-${DAY} ${TIME}
 */
public class ${NAME} {
}
```

# Live Templates

```java
/**
 *
 * <p> $CLASSNAME$ </p>
 *
 * @author $USER$
 * @version 1.0
 * @date $DATE$ $TIME$
 */

variables:
CLASSNAME：className()
USER：user()
DATE：date()
TIME：time()

如果需要特定类的时间格式 如: yyyy-MM-dd 可以使用以下配置
DATE：groovyScript("new java.text.SimpleDateFormat(\"yyyy-MM-dd\").format(new java.util.Date())")
```