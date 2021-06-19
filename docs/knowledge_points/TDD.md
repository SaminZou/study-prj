```yaml
title: TDD
author: samin
date: 2021-01-24
```

# 说明

Test-Driven Development 测试驱动

# 入门

The simple concept（概念） of TDD is to write and correct the failed tests before writing new code (before development).

Tests are nothing but requirement conditions that we need to test to fulfill them.

# 优势

- 以单元测试的通过为标准，并且通过不断重构让代码简单、无bug

- 给我们重构的信心（give us the confidence to refactor）；没有单元测试的代码，大家敢随便修改

- 好的单元测试就是文档 （documenting expected behavior）；几个实用的例子比文档让人感兴趣的多

# 特点

In TDD, you achieve 100% coverage test. Every single line of code is tested, unlike traditional testing.

# TDD / UTDD / ATDD / BDD

- UTDD（ Unit Test Driven Development ）**单元测试驱动开发**

大部分的 TDD 落地实践为 UTDD，所以容易让人产生 TDD 就是 UTDD 的想法。

- ATDD（ Acceptance Test Driven Development ）**验收测试驱动开发**

为了保障开发的程序具有可衡量的业务价值，通过验收文档编写更完善的测试用例，可以让开发人员反思如果开发了不需要经过验收测试的代码，为何要实施。

- BDD（ Behavior Driven development ）**行为驱动开发**

在落地 ATDD 的过程中，慢慢衍生出来和 TDD 方向一致且更具价值的产物。要求每个故事必须有一个通过 GWT 格式编写的行为测试。

|区别项|TDD|BDD|
|:---:|:---:|:---:|
|参与者|开发和测试|开发和客户|
|用例目的|方法的正确性|行为与结果的对比|
|解决问题|解决开发和测试脱节|解决开发和需求脱节|
|测试类型|白盒测试|黑盒测试|
|输入文档|通过需求文档拆分的测试用例|使用 GWT 格式编写的行为用例|
|安全感|中|高|
|执行速度|快|慢|

**BDD帮助开发人员设计(design)软件，TDD帮助开发人员测试(test)软件**

> 区分这些概念不是为了对号入座自己使用的具体开发过程，这样反而画地为牢，限制了团队的开发灵活度，应该结合项目落地实践，灵活的去编写更有价值的单元测试，而不必纠结自己用的是 TDD 还是 BDD。

# 容易混淆的 Test Doubles

TDD 是为了保证自身项目的代码质量，而不是致力于项目的整体运行情况，所以对涉及 DB、IO、WEB、API、Library 等操作的方法，采用 Mock 来消除这方面的影响。

同时 TDD 倡导尽可能快的测试速度，尽量不影响整体的构建速度，所以网络通信和其他项目的依赖调用造成的延迟会极大的拖垮项目的构建速度。

临时创建，为了正常进行单元测试的方式，谨记每种类型的目的不一样

- Dummy

实例划一个空对象

- Fake

按照预期实例化对象

- Mock

模拟实例化对象

- Stub

是 Mock 的延申，对模拟实例化对象的方法进行预期结果定义

- Spy

Spy 和 Mock 的区别是 Spy 操作的是实例化**真实**对象，Stub **部分**方法的返回值

- Selenium

仿真器，比如模拟一个 Tomcat 容易启动一个 WEB 项目来运行单元测试进行测试。