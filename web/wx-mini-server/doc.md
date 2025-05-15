# 微信小程序获取手机号

## 1 传统

1. 通过 code 调用 jscode2session 接口获取 session_key

2. 用户点击 open-type="getPhoneNumber" 获取 encryptedData、iv

3. 后端通过 session_key、encryptedData、iv 解析获得手机号

获取到的手机号为微信实名认证绑定的手机号码

## 2 后端接口请求

1. 前端通过 wx.getPhoneNumber() 获取 code（和换取 open_id 的 code 不是同一个）

2. 后端通过 code 获得手机号

搭配中国运营商 SIM 认证能力（联通、电信、移动）使用，获取的是当前手机插入 SIM 卡的手机号（双卡双待的手机，默认会获取主机手机号）