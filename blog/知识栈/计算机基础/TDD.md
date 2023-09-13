```yaml
title: TDD
author: samin
date: 2021-01-24
```

# 基本概念

- 测试驱动开发（Test Driven Development）是一种软件开发过程，是 XP 的核心实践

- 将需求转换为测试用例

- 先写测试用例，再进行业务代码的开发

- 不断重构，反复通过测试用例进行软件迭代升级

- 通过测试用例追踪软件生命周期

> 完整的需求文档为传统开发模式的软件背书，被拆解的测试用例集合为 TDD 开发模式的软件背书。
>
> 重构和TDD是敏捷方法的核心构成要素，脱离了TDD的敏捷是危险的，没有用例保障的重构一旦启动，就像一匹脱缰的野马，而单元测试和TDD则是缚住野马的缰绳。

# 开发流程

**红灯、绿灯 、重构** 流程是TDD的基石，分别代表的意思是 **编写单元测试**、**编写可以成功通过单元测试的业务代码**、**重构代码**。

![](https://lh3.googleusercontent.com/pw/ACtC-3fUXUhBDRlNKsrtz3EqjHm-wxqyVXeh9djQjzw9U1OVQENyEpNw0p77SpckPYVRXvJ0u8ZEA7k3_nj7M65XO8eyftCww5VfOr3P8AfHCf4fs8QtiN1QiVDbS9rmX0eoIHX7BLUZfsB8rs8Yqp9vmDjx=w792-h820-no?authuser=0)

在开始编程之前，每次只考虑一个需求，把这个需求拆分成尽量小的测试用例集合。首先编写一个无法通过的单元测试，然后用简单的、容易书写并且快速的方式（坏味道）编写可以让这个单元测试通过的业务代码，最后通过重构改进代码。不断重复这个过程，直到成功实现所有需求。

流程整理如下：

1. 阅读理解新特性或者 Bug 修改单，拆解为测试用例（Use Case，简称 UC）集合

2. 根据UC，编写单元测试

3. 实现满足要求的代码， 运行并通过所有单元测试，如果不通过，重复此步骤直至所有单元测试通过

4. 在可以通过单元测试的前提下重构代码

5. 重复以上步骤

# TDD 的样子

1. 根据拆分的最小粒度测试用例，编写一个一定可能报错的单元测试

![](https://lh3.googleusercontent.com/pw/ACtC-3foqlsZMdaIk6PesiiaP5z58bn49zCFvJZ97ixAjWX7DJJxJKvAlUf1N4MoDnQMxfPaAhZMlXpTNaTudTGBXMN1q1S9sL_t0rH_UeEZK2kW9ucQAoaiKCowvWmUPBa-Gsr5rpgEI4PGvql47ScxvzRC=w1257-h636-no?authuser=0)

2. 快速编写可以符合单元测试的代码集合

![](https://lh3.googleusercontent.com/pw/ACtC-3fQOrHl6H2gqsqzY2x0UNFFUefWJxFtXcyFsM4eTKdMWIlKMwLRgX_0iebNy3flrOZTDViibAdXwDLbQn3owhWqoW8XoDlDx-BMybLQrwEGfTC4yOg4UWHfBe6fLC-U20hr_ghBytCErRqZe_nxZ5Qn=w1767-h716-no?authuser=0)

3. 重构你的代码

![](https://lh3.googleusercontent.com/pw/ACtC-3cI5yIkIJiRYhrUoMDX6yHHx_KkkAwGMScx0CsHx2YFl8tPFLxRni9JQbdnqiY0g1TQshmyfZdoELGDRFGvfc8kovzrHcTiIt_C7UYGWqNPn-vMw3JpgzsioTAV5csLzm9JH620lx-ndjC8WQGGTbc6=w746-h664-no?authuser=0)

# TDD 的落地方式

相信大家一听到 TDD，就会下意识的想到单元测试，并且把 TDD 和编写单元测试画上等号，这就像提到微服务，大家容易和”不就是restful接口“和”把项目拆分“挂钩一样。单元测试只是落地 TDD 的一种方式，也可以替换为 Postman + Newman 编写单元测试的方式，甚至可以编写一个个的 bash 脚本去检测自己编写的代码，单元测试是最容易落地 TDD 的方式，所以一般落地 TDD 都会采用编写单元测试的方式。

# TDD / UTDD / ATDD / BDD

- UTDD（ Unit Test Driven Development ）**单元测试驱动开发**

大部分的 TDD 落地实践为 UTDD，所以容易让人产生 TDD 就是 UTDD 的想法。

- ATDD（ Acceptance Test Driven Development ）**验收测试驱动开发**

为了保障开发的程序具有可衡量的业务价值，通过验收文档编写更完善的测试用例，可以让开发人员反思如果开发了不需要经过验收测试的代码，为何要实施。

- BDD（ Behavior Driven development ）**行为驱动开发**

在落地 ATDD 的过程中，慢慢衍生出来和 TDD 方向一致且更具价值的产物。要求每个故事必须有一个通过 GWT 格式（ Given 哪个场景，When 哪个方法， Then 预期结果 ）编写的行为测试。

> ATDD 和 BDD 都需要遵守BCDE原则，保障交付质量。

    Border：边界测试
    Correct：正确的输入，正确的预期输出
    Design：按照需求和设计文档编写测试逻辑
    Error：错误输入，预期输出

# TDD vs BDD

|  区别项  |            TDD             |             BDD             |
| :------: | :------------------------: | :-------------------------: |
|  参与者  |         开发和测试         |         开发和客户          |
| 用例目的 |        方法的正确性        |      行为与结果的对比       |
| 解决问题 |     解决开发和测试脱节     |     解决开发和需求脱节      |
| 测试类型 |          白盒测试          |          黑盒测试           |
| 输入文档 | 通过需求文档拆分的测试用例 | 使用 GWT 格式编写的行为用例 |
|   成本   |             低             |             高              |
|  安全感  |             中             |             高              |
| 执行速度 |             快             |             慢              |

![](https://lh3.googleusercontent.com/pw/ACtC-3efTnSbnGSxjNU8Hn_cv2QRNwHin7Iuy-wNs0mm48tVMbuJLn_98TTh9IDGM5EYUJaYxsmJb5C6Yiz4HnJVcQK8xGX1yojjtcdOAwMapla3DulF9pDypTXg-zpj4qXmnaQT1dr0exibsq126pMjRKmq=w560-h300-no?authuser=0)


通过测试金字塔可以形象的了解到 TDD 和 BDD 针对的测试层次是不一样的，所以说**BDD帮助开发人员设计(design)软件，TDD帮助开发人员测试(test)软件**。区分这些概念不是为了对号入座自己使用的具体开发过程，这样反而画地为牢，限制了团队的开发灵活度，应该结合项目落地实践，灵活的去编写更有价值的单元测试，而不必纠结自己用的是 TDD 还是 BDD，在凌云平台的实际落地中，也有 BDD 的影子。

# Test Doubles 是什么

TDD 是为了保证自身项目的代码质量，而不是致力于项目的整体运行情况，所以对涉及 **数据库连接操作、IO读写、API调用、第三方包调用** 等操作的方法，采用 `Test Doubles` 来消除这方面的影响。

所以出现了一些被临时创建的特殊实例，来消除对单元测试的影响，让单元测试以高效且不被环境因素影响，这些特殊实例集合就是 `Test Doubles`。

> TDD 倡导每个单元测试用例是尽可能快运行结束，并且不受环境因素影响，保证调试效率。

# Test Doubles 分类

- Dummy

通常为了调用某个入参方法，需要实例化一个对象，如果对入参值没有特别说明，我们可以构建一个空对象，一般称这个空对象为 Dummy Object。

- Fake

如果对实例化的对象我们需要按照预期进行一些赋值操作，那么这个对象成为 Fake Object。

- Mock

涉及数据库连接操作、IO读写、API调用、第三方包调用等需要用到，测试对象需要单独编写一个 Mock 方法才能够生明出一个既能够被调用又不能进行实际运行的特殊实例。

- Stub

是 Mock 类型的延申，对模拟实例化对象的方法进行预期结果定义。

- Spy

Spy 和 Mock 类似，但是区别在意 Spy 是实例化**真实**的对象，同时对**部分**方法进行预期结果定义。

- Selenium

仿真器，比如模拟一个 Tomcat 容易启动一个 WEB 项目来运行单元测试进行测试。

![](https://lh3.googleusercontent.com/pw/ACtC-3cpvxvKaZBjlvr1bT8zr3QM-MBIDKqjmcyeRDTmlAl6LMDO-ZMkyxD48DsBA34mUwKRdRmKFHViUf00CCIP9wtBJSYQjGUgAaE44fZoo-kwqdKBnkNDmfF7NRBZ2pQKZk6xlYUX2z_ofytMmRYAdCiy=w858-h551-no?authuser=0)

# Mock vs Stub

![](https://lh3.googleusercontent.com/pw/ACtC-3e5zFg7r31_OzEqUCqIgGQcU66BQiytkytbQrZ4AhcU15Ns0cW0Ub01YjI9JKWo_zZnakLb4Tg0opDb-_CZ0VAHfdbCGYHC9Tt4f0ReGMOEX2uFhfwEsbOeCVJw2p9nUr8GemDEkbfVNINeR46J6F8F=w921-h831-no?authuser=0)

# Mock vs Spy

![](https://lh3.googleusercontent.com/pw/ACtC-3dDv-M5nrU222kEYTr8EH7T5AtMNe3mRS0p2JXKhNvvK21Y1ZQwNg7dG1VEKckhkl7pG1LLV2LxVpAyJYn1_7UV5sPyguWa7Xve47ylxiW7Q9puI46XJKnOKf8BYZ8enNPRdaqt6dgEwSTZD5IQmqZW=w862-h595-no?authuser=0)

# TDD 落地技术栈

![](https://lh3.googleusercontent.com/pw/ACtC-3diuKBo2BjuPetC4HVSx6qrU7uqx2BbfU5fmH-mr_DskMF4Cq1NQEUOwpBUE0UKDxj3LX5XNhByOvfmJ8QjbUl54SAMEKXJQykbsnsDi6uI5xK2y8kibtwz9bzEH8YKQrwW-RixVrvTVgpOSXg0twfd=w1357-h348-no?authuser=0)

# 服务端使用到的技术栈

- JUnit5
  支撑单元测试运行

- spring-boot-starter-test

- Mockito / PowerMock

  PowerMock 是加强版的 mock 框架，对静态方法、私有方法等的 mock 能力

- Jacoco
  解决覆盖率统计的问题

- Maven Surefire（ SurefireTest Reporting ）

[comment]: <> (- Jenkins + JUnit Plugin + Jacoco Plugin)

# 基于 TDD 的开发规范

1. 单元测试必须 100% 覆盖所有业务 service 类方法

2. 测试覆盖率达到 90% 以上

3. 每个单元测试应该保持在毫秒级

   善用 mock 技巧，让单元测试运行更高效

4. 区别于 BDD，尽可能模拟多的用例

5. 配置，过滤比较薄的抽象层（即实现相对简单），如 controller、const、dao、entity 等

6. 业务逻辑只能在 service 层编写，不能在不进行单元测试的代码中进行编写

7. code revicew 的时候，优先走查单元测试

> 通过这些开发规范深度落实 TDD，让开发人员更有信心地进行重构和整洁代码，提高了代码的正确性，可以及时发现代码设计问题。SOLID、DRY、LOD 这些晦涩难懂的规则，其实已经体现在大家的代码中。
> DRY (Don't Repeat Yourself) / LOD ( Law of Demeter ) 

# 引入 TDD 后开发工时预估

引入单元测试后，工时预估发生了变化，大概包含了以下五类时间。

	详细设计 + 编写测试用例及评审 + 编写单元测试 + 编写业务代码 + 多端联调

比起之前的开发模式，增加了测试用例拆解和单元测试的编写，但是这个时间可以换来的效益远大于投入。

# 如何写好单元测试

## 设计原则（S.O.L.I.D）

- SRP 单一职责原则

软件模块应该只有一个被修改的理由。在大多数情况下，编写Java代码时都会将单一职责原则应用于类。单一职责原则可被视为使封装工作达到最佳状态的良好实践。更改的理由是：需要修改代码。

单一原则，类、方法只干一件事。

- OCP 开闭原则

模块、类和函数应该对扩展开放，对修改关闭。

通过继承和多态扩展来添加新功能。开闭原则是最重要的设计原则之一，是大多数设计模式的基础。

软件建设一个复杂的结构，当我们完成其中的一部分，就应该不要修改它，而是在其基础上继续建设。

- LSP 里式替换原则

在设计模块和类时，必须确保派生类型从行为的角度来看是可替代的。

使用父类的地方都可以用子类替代。

父类最好为抽象类。

子类可实现父类的非抽象方法，尽量不要覆盖重写已实现的方法。

子类可写自身的方法，有自身的特性，在父类的基础上扩建。

子类覆盖重写父类方法时，方法的前置条件（即方法的形参）要比父类方法的输入参数更宽松，后置条件（返回值）要更严格。

- ISP 接口隔离原则

减少了代码耦合，使软件更健壮，更易于维护和扩展。

客户端不应该依赖它所不需要的接口。

- DIP 依赖倒置原则

高级模块不应该依赖低级模块，两者都应该依赖抽象。

抽象不应该依赖于细节，细节应该依赖于抽象。

## DRY / KISS / YAGNI / LOD

DRY：不要干重复的事儿。

KISS：不要干复杂的事儿，思从深而行从简。

YAGNI：不要干不需要的事儿，尺度把握尤为重要，超越尺度则会有过度设计之嫌。

LOD：最小依赖。

## 设计模式

高内聚：相近功能放在同一类中，相近功能往往会被同时修改，放到同一个类中在修改时，代码更易维护（指导类本身的设计）

低耦合：类与类之间的依赖关系简单清晰，一个类的代码改动不会或者很少导致依赖类的代码修改（指导类间依赖关系设计）

# 单元测试的好处

- 提高代码正确性

    - 流程判读符合预期，按照步骤运行，逻辑正确。

    - 执行结果符合预期，代码执行后，结果正确。

    - 异常输出符合预期，执行异常或者错误，超越程序边界，保护自身。

    - 代码质量符合预期，效率，响应时间，资源消耗等。

- 发现设计问题

    - 代码可测性差
    
    - 方法封装不合理
    
    - 流程不合理

- 提升代码可读性

易写单测的方法一定是简单好理解的，可读性是高的，反之难写的单测代码是复杂的，可读性差的。

- 顺便微重构

如设计不合理可微重构，保证代码的可读性以及健壮性。

- 提升开发人员自信心

经过单元测试，能让程序员对自己的代码质量更有信心，对实现方式记忆更深。

- 启动速度，提升效率

不用重复启动Pandora容器，浪费大量时间在容器启动上，方便逻辑验证。

- 场景保存（多场景）

在HSF控制台中只能保存一套参数，而单测可保存多套参数，覆盖各个场景，多条分支，就是一个个测试用例。

- CodeReview时作为重点CR的地方

- 好的单测可作为指导文档，方便使用者使用及阅读

# 实践 TDD 后的收益

- 避免了代码难以测试、难以修改、难以阅读的窘况，每个方法的职责更清晰内聚
  
- 先写测试可以帮助我们去思考需求，并提前澄清需求细节，而不是代码写到一半才发现不明确的需求
  
- 由于需求量庞大，很多需求文档的细节不明确，在编写单元测试的时候，会帮战产品反向去完善需求文档
  
- 促进开发人员思考，在编写单元测试的时候会对需求落地思考得更全面
  
- 对于定时任务、消息队列、SDK方法，提供了更便捷的调试方式
  
- 提升了冒烟效率，**持续性**地减少回归bug
  
- 协同开发接手另一个人员开发的模块，当需要在此模块逻辑添加新特性同时保持输出不变的时候，更能体会到单元测试的重要性
  
- 由于在开发的时候，做了单元测试，在做集成测试的时候就会更从容，加快接口的调试速度
  
- 单元测试即文档，开发人员之间交接需求的时候，往往看单元测试比读需求文档更容易理解；网上优秀的开源项目，都会有完整的单元测试，所以大家阅读这类开源项目的时候，往往看单元测试比看文档多
  
- 由于 CI/CD 需求中含有自动化测试的环节，自动化测试包含了白盒和黑盒测试，在0到1的过程中引入了 TDD，帮我们阶段性的完成了白盒自动化测试。

# 总结

![](https://lh3.googleusercontent.com/pw/ACtC-3eWg0yA25FYB5OLWmDFURN2YdB6XRdNcl9pHjnLnG3XRhqzIdflyaHoh7aVUvKqc-BCBhWJIl3k1RXlDdiw_R71trw3pu59fO0lk8Z6cX90QGTSMydEJfAlYNVMYZa2PLOvo3QDgC1WJgjbxkn_O9GM=w1120-h857-no?authuser=0)

- 避免了代码难以测试、难以修改、难以阅读的窘况，每个方法的职责更清晰内聚

- 先写测试可以帮助我们去思考需求，在根据需求拆分成测试用例的阶段，可以对需求的落地进行比较深入的思考。提前澄清需求细节，避免代码写到一半反复和产品确认需求

- 由于需求量庞大，很多需求文档的细节不明确，在编写单元测试的时候，会帮战产品反向去完善需求文档

- 促进开发人员思考，在编写单元测试的时候会对需求落地思考得更全面

- 对于定时任务、消息队列、SDK方法，提供了更便捷的调试方式

- 提升了冒烟效率，**持续性**地减少回归bug

- 协同开发接手另一个人员开发的模块，当需要在此模块逻辑添加新特性同时保持输出不变的时候，更能体会到单元测试的重要性

- 由于在开发的时候，做了单元测试，在做集成测试的时候就会更从容，加快接口的调试速度

- 单元测试即文档，开发人员之间交接需求的时候，往往看单元测试比读需求文档更容易理解；网上优秀的开源项目，都会有完整的单元测试，所以大家阅读这类开源项目的时候，往往看单元测试比看文档多

- 由于 CI/CD 需求中含有自动化测试的环节，自动化测试包含了白盒和黑盒测试，在0到1的过程中引入了 TDD，帮我们阶段性的完成了白盒自动化测试。

TDD 并不是为了让开发人员完全承接测试工作，如多端业务集成测试、编码问题、时区问题、不同 JVM 版本兼容问题等，还是需要通过测试工程师在实际的运行环境中进行集成测试。

有可靠的单元测试集合为项目背书，可以让开发人员的操作更安全，并且在重构的时候更有信心不会对系统的造成致命的破坏。开发养成编写习惯后，对于原先要编写单元测试再开发业务代码的工作量不会再觉得浪费时间或者是麻烦，反而会高度依赖单元测试，编写的代码往可测试（方法复用、方法长度、抛错、日志记录等）方向发展，代码质量提高。更从容的面对迎接需求变化，更乐于改善代码的设计。

相关优秀文章：
https://mp.weixin.qq.com/s/TjJ31yWTMwr4szz1JqtKcQ
https://mp.weixin.qq.com/s/EZejQam6n_qU5ZLDOv82Rg
https://mp.weixin.qq.com/s/nIntjcrhgLQMiNo0XqPyyg