# 背景

只需要上传 consumer 模块

# 使用以下指令上传 consumer

mvn clean compile package deploy -pl consumer -am -Dmaven.test.skip=true -DaltDeploymentRepository=samin-dev::
default::http://host:port/repository/maven-releases/

> -pl 指定只编译上传 consumer 包到 maven 仓库
> -am 可以把相关依赖包同时编译上传（maven-demo 和 common 项目）

# 其他项目引入 consumer 模块

实际上，引入 consumer 的同时，maven-demo 及 common 项目也会同时被导入到本地仓库

当然可以，以下是一篇适合发布在技术博客或团队知识库的精炼版总结👇

---

# Maven 多模块项目：模块间源码依赖的正确姿势

在多模块（multi-module）Maven 项目中，我们经常会遇到这样的需求：
**在同一父工程下，一个子模块需要直接引用另一个子模块的类（源码级依赖，而非 jar）。**

---

## 项目结构示例

```text
demo
 └── demo-operate
      ├── application
      ├── operate
      ├── console
      └── console-custom
```

父模块 `demo-operate` 的 `pom.xml` 管理所有子模块：

```xml
<modules>
    <module>application</module>
    <module>operate</module>
    <module>console</module>
    <module>console-custom</module>
</modules>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>cn.samin</groupId>
            <artifactId>console</artifactId>
            <version>${project.version}</version>
        </dependency>
        ...
    </dependencies>
</dependencyManagement>
```

---

## 问题

在 `console-custom` 中想直接 `import` 使用 `console` 的类，但 IDEA 报错。
原因是 **Maven 模块间默认是隔离的**，需要显式声明依赖。

---

## 解决方案

在 `console-custom/pom.xml` 中添加依赖：

```xml
<dependencies>
    <dependency>
        <groupId>cn.samin</groupId>
        <artifactId>console</artifactId>
    </dependency>
</dependencies>
```

无需指定版本号，因为父 pom 已在 `<dependencyManagement>` 中统一管理。
执行命令：

```bash
mvn clean install -pl console-custom -am
```

Maven 会自动先构建 `console`，再构建 `console-custom`，实现源码级依赖。

---

## Maven 如何判断依赖是“内部模块”还是“仓库 Jar”？

在执行构建时，Maven 会扫描父工程的 `<modules>` 列表，生成一个 **reactor 构建图**。
当遇到依赖：

```xml
<dependency>
    <groupId>cn.samin</groupId>
    <artifactId>console</artifactId>
</dependency>
```

Maven 的判断逻辑如下：

| 场景                                   | 行为                             |
| ------------------------------------ | ------------------------------ |
| 依赖的模块在当前 reactor（父工程的 `<modules>` 中） | 直接使用源码编译结果（不从仓库取）              |
| 模块不在 reactor 中，但已执行过 `install`       | 从本地仓库 `~/.m2/repository` 取 jar |
| 本地无 jar                              | 再尝试从远程仓库下载                     |

因此，只要两个模块在同一个父工程中且参与同一次构建，就不会访问仓库。

---

## 最佳实践

1. **显式声明模块依赖**（不要依赖 IDEA 手动配置）
2. **统一版本号管理**：使用父 pom 的 `<dependencyManagement>`
3. **保证构建顺序**：父 pom 中 `<modules>` 的顺序与依赖关系一致
4. **独立构建时带上 `-am` 参数**，自动构建依赖模块

---

## 总结

> 在 Maven 多模块工程中，模块间共享源码的正确做法是：
> **通过 `<dependency>` 显式声明依赖，Maven 会在同一 reactor 内自动关联源码编译，而非仓库取 jar。**

这既能保持模块化结构清晰，又能让构建过程高效、可复现。
