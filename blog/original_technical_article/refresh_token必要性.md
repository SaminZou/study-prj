```yaml
title: refresh_token必要性
author: samin
date: 2021-09-09 
```

# 来源

在oauth2方案中，访问 "/oauth/token" 接口，会返回类似如下格式：

```json
{
  "access_token": "950a7cc9-5a8a-42c9-a693-40e817b1a4b0",
  "token_type": "bearer",
  "refresh_token": "773a0fcd-6023-45f8-8848-e141296cb3cb",
  "expires_in": 27036,
  "scope": "select"
}
```

# 作用

这里的 `refresh_token` 作用就是在有效期内用来和资源授权服务器来换取新的 `access_token` 的。

网站的记住我，采用的就是客户端缓存 `refresh_token` 的方式来直接换取 `access_token` 而不用用户再次输入账号密码登录的。

# 必要性

- 减少用户输入账号密码或者是重新给第三方客户端授权；

- 如果是单纯服务器调用接口，完全不需要 `refresh_token` 机制；