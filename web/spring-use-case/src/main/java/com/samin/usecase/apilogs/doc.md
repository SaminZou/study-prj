# 实现功能

用于记录 HTTP 请求日志

- 请求来源的 IP 地址（客户端地址）
- 请求的目标路径（请求 URL）
- HTTP 方法
- 处理耗时
- 异常

# ServletRequestHandledEvent 原理

- Spring 的事件驱动机制

- 观察者模式

当请求被 DispatcherServlet 完整处理后，Spring 会通过 ApplicationEventPublisher 发布 ServletRequestHandledEvent。随后，所有实现了 ApplicationListener<ServletRequestHandledEvent> 的监听器会接收到这个事件并执行相应的处理逻辑。

# 优点

- 非侵入性
- 不影响业务代码（性能监控的逻辑完全独立于业务实现，避免了代码耦合。）
- 灵活扩展（开发者可以根据需求自定义监听器，轻松扩展功能。）