```yaml
title: IoT 基础知识
author: samin
date: 2021-04-27
tags: IoT
```

# 基础概念

## 设备连接到物联网

![设备连接物联网](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/设备连接物联网.png)

## 感测的作用

![感测的作用](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/感测的作用.png)

## 数据处理方式

![数据处理方式](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/数据处理方式.png)

![数据处理](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/数据处理.png)

### 批处理

![hadoop批处理方式](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/hadoop批处理方式.png)

使用MapReduce和HDFS这两种机制，Hadoop就能高速处理巨型数据。

![spark批处理方式](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/spark批处理方式.png)

### 流处理

批处理是把数据攒起来，一次性进行处理的方法。相对而言，流处理是不保存数据，按照到达处理服务器的顺序对数据依次进行处理。

![spark流处理](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/spark流处理.png)

![storm流处理](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/storm流处理.png)

## 反馈

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/%E5%8F%8D%E9%A6%88.png)

![反馈方式](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/反馈方式.png)

## 物联网网关

网关指的是能连接多台设备，并具备直接连接到互联网的功能的机器和软件。

![物联网网关](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网网关.jpg)

![网关的作用](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/网关的作用.jpg)

## M2M

![M2M图解](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/M2M%E5%9B%BE%E8%A7%A3.jpg)

# 物联网架构

## 总体架构

![物联网系统架构2](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网系统架构2.jpg)

![物联网系统架构](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网系统架构.jpg)

## 数据传输模式及交互模式

![物联网数据传输模式](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网数据传输模式.jpg)

![物联网的数据交互](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网的数据交互.jpg)

前端部分代表各种设备或者网关

## 常用通讯协议

- HTTP

- WebSocket

- MQTT

![MQTT](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/MQTT.png)

四大特性：QoS、Retain、Will、Clean session，不同的中间件，不一定实现所有特性

QoS 是Quality of Service（服务质量）的简称

![MQTT主题通配符](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/MQTT主题通配符.png)

主题通配符：“#” 和 “+”

![retain](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/retain.png)

![will](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/will.png)

Will Retain 可以达到 Will + Retain 的效果，在中介存储，让更多订阅者收到 Will。

Clean session 机制，用于订阅者重连中介，是否接受断线期间，中介存储的 QoS1 和 QoS2 信息，session 0 为接收，session 1 为当作干净的连接，不接收断线期间中介累积的信息。

### 互联网常用数据交互公式

- XML

- Json

- MessagePack

## 机器学习

![机器学习](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/机器学习.jpg)

# ICT（信息与通信技术）和 IoT

# 物联网应用开发

![IoT开发者协调](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/IoT开发者协调.png)

## 设备结构

![物联网设备结构](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网设备结构.png)

- 微控制器

![微控制器](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/微控制器.png)

开发流程：把微控制器嵌入到自制电路中 -> PC上编写程序 -> 程序写入到微控制器 -> 运行调试

- 输入设备

键盘、传感器、麦克风、相机

- 输出设备

LED、显示器、驱动器、喇叭

- 与网络连接

    - 有线（稳定）、无线（灵活）

    - 直连型、通过网关连接型

> 根据连接方式分类，可以分为以上两种，通过网关连接型设备和网关连接的方式也细分为有线和无线

## 连接方式

IoT 设备通信方式的选择一般根据 `使用的协议`、`通信模块的大小`、`耗电量` 这三个指标进行判断选择。

### 有线连接

- 以太网

- 串行通信

![串口通信](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/串口通信.png)

- USB

### 无线连接

- WI-FI

Wi-Fi耗电量高，所以不适合那些需要长时间进行通信的设备

- 3G/LTE

设备需要 SIM 卡插槽，且需要额外付费

- 蓝牙

v4.x 以上版本协议才支持 BLE（ Bluetooth Low Energy ）

- IEEE 802.15.4/Zigbee

需要安装接收器，没有蓝牙和无线网卡那么普遍被安装在大多数设备

- 易能森

## 传感器

### 具有代表性的传感器

![具有代表性的传感器](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/具有代表性的传感器.png)

### 感测方法

- 利用物理特征的传感器

- 利用几何变异的传感器

### 放大传感器的信号

放大电路的核心是一个叫作运算放大器（operational amplifier）的IC芯片。其实它就是由晶体管（控制电流的元件）等组成的一个复杂电路，除了放大信号外，也用于模拟运算。

### 把模拟信号转换成数字信号

![AD转换过程](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/AD转换过程.png)

### 传感器的性能指标

![传感器的性能指标](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/传感器的性能指标.png)

## 反馈

D/A 转换（数字/模拟转换），比较有代表性的方法：脉冲宽度调制（PWM）

# GPS

- 广播信号让设备接收信息计算位置，所以不会出现卫星被请求挤爆的现象

- 大概有24颗卫星构成GPS系统，还有一些卫星帮助提高精度（至少需要 4 颗卫星才能定位，GNSS时代，即使在某个时刻只有一颗 GPS卫星，加上GLONASS或Galileo凑够 4 颗也能准确定位）

- 卫星发出的信息包含：发射无线电报时的准确时刻、卫星在宇宙空间中的信息

- 卫星系统的统称是 GNSS（GPS 是美国的，GLONASS 是俄罗斯的，Galileo 是欧盟的，北斗是中国的），常用的是GPS和GLONASS

## IMES（Indoor Messaging System，室内通信系统）

![IMES](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/IMES.png)

## WI-FI 定位技术

## 指纹定位

## Beacon

支持 BLE 的手机可以收到 Beacon发出的数据及定位信息

# 物联网服务的系统开发

## 作用

- 传感器信息持久化

- 云端控制设备

## 开发

![物联网系统开发](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网系统开发.png)

从事物联网系统开发需要的技术面要比从事一般的Web开发更广

## 特征

![物联网系统特征](https://raw.githubusercontent.com/SaminZou/pic-repo/master/IoT/物联网系统特征.png)

# 物联网与数据分析

# 物联网与可穿戴设备

# 物联网与机器人

> 文中贴图大量来自《图解物联网》