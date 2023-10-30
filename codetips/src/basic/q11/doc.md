```yaml
title: 泛型
author: samin
date: 2021-06-08
```

# 是什么

背景：继承是面向对象的三大特性之一，比如在我们向集合中添加元素的过程中 add() 方法里填入的是 Object 类，而 Object
又是所有类的父类，这就产生了一个问题——添加的类型无法做到统一 由此就可能产生在遍历集合取出元素时类型不统一而报错问题。

例如：我向一个 ArrayList 集合中添加 Person 类的对象，但是不小心手贱添加了一个 Boy 类的对象，这就会导致报错

Java 泛型（generics）是 JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型。

泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。

# 使用泛型的好处

1. 提升了程序的健壮性和规范性

2. 编译时，检查添加元素的类型，提高了安全性

3. 减少了类型转换的次数，提高效率

4. 在类声明时通过一个标识可以表示属性类型、方法的返回值类型、参数类型

```java
class Person<E> {
    E s;   //可以是属性类型
    public Person(E s) {  //可以是参数类型
        this.s = s;
    }
    public E f() { //可以是返回类型
        return s;
    }
    public void show() {
        System.out.println(s.getClass());  //显示S的运行类型
    }
}
```

# 原理

Java 的泛型实际上是由编译器实现的，将泛型类型转换为 Object 类型，在运行期间再进行状态转换

# 语法

- 一个类型
  < 占位符 >

- 多个类型
  < 占位符，占位符>

- 类型限定
  < 占位符 extends 占位符 >

> 需要对泛型进行限制的时候可以使用 ? ，使用 `<? extends ParentClass>` 语法，表明泛型的对象必须是集成 `ParentClass` 父类

# 泛型 vs Object

1. 不需要做强制类型装换

2. 不需要做大量的类型判断（ instanceof ）

3. 在编译时抛错，非运行时抛出 ClassCastException （泛型出现的主要目的之一，希望在编译时可以排除这类错误）

# 泛型中的 ?

通配符，指定类型范围，避免开发不知道该数据只支持的范围，设置错误类型导致出现bug。

```java

// <? extends Generics> 是上边界限定通配符，声明需要继承了 Generics 才可以通过编译
List<? extends Generics> genericsList = new ArrayList<>();
Generics generics = genericsList.get(0);
genericsList.add(new Generics<String>()); // 编译无法通过

// <? super Generics> 是下边界限定通配符，声明需要是 Generics 的父类才可以通过编译
List<? super Generics> genericsList = new ArrayList<>();
genericsList.add(new Generics()); // 编译无法通过
Generics generics = genericsList.get(0);
```

# 类型擦除

## 由来

严格来说，Java的泛型并不是真正的泛型。Java 的泛型是 JDK1.5 之后添加的特性，为了兼容之前版本的代码，其实现引入了类型擦除的概念。

## 具体含义

Java 的泛型代码在编译时，由编译器进行类型检查，之后会将其泛型类型擦除掉，只保存原生类型，如 Generics<Long> 被擦除后是
Generics，我们常用的 List<String> 被擦除后只剩下 List。