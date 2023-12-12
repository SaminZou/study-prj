```yaml
title: 如何全局拦截所有 HTTP 请求，并且修改 URL 入参
author: samin
date: 2023-12-12
```

# 前提知识点

1. HttpServletRequest 对象的 Map<String, String[]> 只读，不可直接修改
2. 多个相同参数名的 URL 入参，后者覆盖前者

# 参考答案

修改 URL ，request.getRequestURL().append(url + "?" + queryString);  添加入参覆盖数值。