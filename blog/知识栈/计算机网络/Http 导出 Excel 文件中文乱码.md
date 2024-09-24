```yaml
title: Http 导出 Excel 文件中文不生效
author: samin
date: 2024-09-24
```

一般的 Excel 导出，利用 HTTP 协议头完成，当文件名带有中文，使用如下代码：

```js
res.setHeader('Content-Type', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
res.setHeader('Content-Disposition', 'attachment; filename*=utf-8\'\'' + encodeURIComponent(formatDateTime() + '中文列表') 
    + '.xlsx');
```