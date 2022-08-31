# JDK中的元注解

## java.lang 中的元注解

- @Override  
  检查该方法是否是重写方法。如果发现其父类，或者是引用的接口中并没有该方法时，会报编译错误  
  源码注解：注解只在源码中存在，编译成class文件之后就不存在了

- @Deprecated  
  标记过时方法。如果使用该方法，会报编译警告  
  编译时注解：注解在源码和class文件中都存在（@Override、@Deprecated、@Suppvisewarnings）

- @SuppressWarnings  
  指示编译器去忽略注解中声明的警告  
  运行时注解：在运行阶段还在起作用，甚至会影响程序的运行逻辑

## java.lang.annotation 中的元注解

- @Retention  
  标识这个注解怎么保存，是只在代码中，还是编入class文件中，或者是在运行时可以通过反射访问

- @Documented  
  标记这些注解是否包含在用户文档中

- @Target  
  标记这个注解应该是哪种 Java 成员

- @Inherited  
  标记这个注解是继承于哪个注解类(默认 注解并没有继承于任何子类)

- @SafeVarargs  
  Java 7 开始支持，忽略任何使用参数为泛型变量的方法或构造函数调用产生的警告

- @FunctionalInterface  
  Java 8 开始支持，标识一个匿名函数或函数式接口

- @Repeatable  
  Java 8 开始支持，标识某注解可以在同一个声明上使用多次

# 重点注解

## @Retention 指定注解的生命周期

RetentionPolicy 枚举类有三个常量，分别是 SOURCE（编译）/CLASS（类加载）/RUNTIME（运行时）

SOURCE/CLASS 在加载到 jvm的时候就被抹除了 ，lombok的 @Data 能在类中加上 set/get 方法就是因为这个

Java文件编译的“注解抽象语法树”阶段就是处理注解相关的逻辑

继承 AbstractProcessor 并实现 process 方法，可以处理注解

一般来说，使用比较多的是 RUNTIME 级别，因为在运行时我们会处理很多事情

一般的自定义注解是结合反射来使用的，反射是 Java获取运行时信息的重要手段

## @Target 指定注解的修饰地方

ElementType 枚举类中有

- TYPE  
  类、接口（包括注释类型）或枚举声明

- FIELD  
  字段声明（包括枚举常量）

- METHOD  
  方法声明

- PARAMETER  
  参数声明

- CONSTRUCTOR  
  构造方法声明

- LOCAL_VARIABLE  
  局部变量声明

- ANNOTATION_TYPE  
  注释类型声明

- PACKAGE  
  包声明

# 用法

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
```


