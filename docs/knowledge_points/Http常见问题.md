```yaml
title: Http 常见问题 
author: samin
date: 2021-10-30
```

1. **现代浏览器在与服务器建立了一个 TCP 连接后是否会在一个 HTTP 请求完成后断开？什么情况下会断开？**
   默认情况下建立 TCP 连接不会断开，只有在请求报头中声明 Connection: close 才会在请求完成后关闭连接，一般http头都会加 Connection: keep-alive，如果维持连接，那么 SSL 的开销也可以避免。

2. **一个 TCP 连接可以对应几个 HTTP 请求？**  
   如果维持连接，一个 TCP 连接是可以发送多个 HTTP 请求的。

3. **一个 TCP 连接中 HTTP 请求发送可以一起发送么（比如一起发三个请求，再三个响应一起接收）？**
   在 HTTP/1.1 存在 Pipelining 技术可以完成这个多个请求同时发送，但是由于浏览器默认关闭，所以可以认为这是不可行的。  
   在 HTTP2 中由于 Multiplexing 特点的存在，多个 HTTP 请求可以在同一个 TCP 连接中并行进行。  
   那么在 HTTP/1.1 时代，浏览器是如何提高页面加载效率的呢？主要有下面两点：
- 维持和服务器已经建立的 TCP 连接，在同一连接上顺序处理多个请求。
- 和服务器建立多个 TCP 连接。

4. **为什么有的时候刷新页面不需要重新建立 SSL 连接？**
   TCP 连接有的时候会被浏览器和服务端维持一段时间。TCP 不需要重新建立，SSL 自然也会用之前的。

5. **浏览器对同一 Host 建立 TCP 连接到数量有没有限制？**
   理论上是可以。但是Chrome 最多允许对同一个 Host 建立六个 TCP 连接。不同的浏览器有一些区别。

6. **如何解决HTTP无状态问题**

- token验证
- HTTP Basic
- Digest Access
- App Secret Key + HMAC
- JWT
- Oauth2