```yaml
title: SpringBoot变量实体类注入
author: samin
date: 2021-10-15
```

# 定义参数

```yaml
# 版本信息
sys:
  version-info: {versionCode: 100, versionName: "v1.1.0"}
```

# 注入参数实体类配置

```java
@Data
@Component
@ConfigurationProperties(prefix = "sys.version-info")
public class SysVersionInfo {

    private Integer versionCode;
    private String versionName;
}
```

# 引用

```java
@Autowired
private SysVersionInfo info;
```