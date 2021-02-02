# 分类

- 简单工厂模式：

简单工厂模式有唯一的工厂类，工厂类的创建方法根据传入的参数做if-else条件判断，决定最终创建什么样的产品对象。

- 工厂方法模式：

工厂方法模式由多个工厂类实现工厂接口，利用多态来创建不同的产品对象，从而避免了冗长的if-else条件判断。

- 抽象工厂模式：

抽象工厂模式把产品子类进行分组，同组中的不同产品由同一个工厂子类的不同方法负责创建，从而减少了工厂子类的数量。

# 用例

- 修改了实现类，客户端调用方不需要修改代码

```java
// 未使用工厂模式来创建一个 Test 对象
A a = new A();
B b = new B();
Test test = new Test(a,b);
```

仔细思考，如果有十个类要声明一个 Test 对象，那么代码行数会有三十行，在修改代码的时候就会需要修改十个地方，并且需要保持代码一致性。

- 降低耦合度

```java
// 未使用工厂模式来创建一个 Test 对象
A a = new A();
B b = new B();
Test test = new Test(a,b);

// 使用工厂模式来创建一个 Test 对象
Test test = TestFactory.newTest();
```

可以看到优化后，创建一个 Test 对象从和三个类耦合降低成一个类，顾降低了耦合度。

# 简单工厂模式

## 说明

通过工厂类，根据传入参数，生成对象

```java
public interface IMask {
    void show();
}

public class HighEndMask implements IMask {
    @Override
    public void show() {
        System.out.println("我是高端口罩");
    }
}

public class LowEndMask implements IMask {
    @Override
    public void show(){
        System.out.println("我的低端口罩");
    }

}

public class MaskFactory{

    public IMask createMask(String type) {
        IMask mask = null;
        if("高端口罩".equals(type)){
            mask = new HighEndMask();
            // .....
            // HighEndMask的100行初始化代码
        }else if("低端口罩".equals(type)){
            mask =  new LowEndMask();
            // .....
            // LowEndMask的100行初始化代码
        }
        return mask;
    }
}

// 客户端调用
public class Test {

    public static void main(String[] args) {
        MaskFactory factory = new MaskFactory();
        IMask maskA = factory.createMask("高端口罩");
        IMask maskB = factory.createMask("低端口罩");
        maskA.show();
        maskB.show();
    }
}
```

## 缺点

不符合`开放-封闭`原则

> 面向对象的开放-封闭原则，就是在程序中对“扩展”开放，对“修改”封闭。如果每次业务改动都要增加新的if-else，就涉及对旧有代码的修改，不但容易出错，可读性也不好。

# 工厂方法模式

## 说明

利用多态性动态创建对象的模式

```java
public interface IMaskFactory {
    IMask createMask();
}

public class HighEndFactory implements IMaskFactory{

    @Override
    public IMask createMask() {
        IMask mask =  new HighEndMask();
        // .....
        // HighEndMask的100行初始化代码
        return mask;
    }
}

public class LowEndFactory implements IMaskFactory{

    @Override
    public IMask createMask() {
        IMask mask =  new LowEndMask();
        // .....
        //  LowEndMask的100行初始化代码
        return mask;
    }
}

// 客户端调用
public class Test {

    public static void main(String[] args) {
        IMaskFactory factoryA = new LowEndFactory();
        IMaskFactory factoryB = new HighEndFactory();
        IMask maskA = factoryA.createMask();
        IMask maskB = factoryB.createMask();
        maskA.show();
        maskB.show();
    }
}
```

# 抽象工厂模式

## 说明

把产品分组，组内不同产品对应同一工厂类不同方法的设计模式，就是抽象工厂模式

```java
// 口罩
public interface IMask {
    void showMask();
}

public class LowEndMask implements IMask {
    @Override
    public void showMask(){
        System.out.println("我的低端口罩");
    }
}

public class HighEndMask implements IMask {
    @Override
    public void showMask() {
        System.out.println("我是高端口罩");
    }
}

// 防护服
public interface IProtectiveSuit {
    void showSuit();
}

public class LowEndProtectiveSuit implements IProtectiveSuit {
    @Override
    public void showSuit() {
        System.out.println("我是低端防护服");
    }
}

public class HighEndProtectiveSuit implements IProtectiveSuit {
    @Override
    public void showSuit() {
        System.out.println("我是高端防护服");
    }
}

// 工厂类
public interface IFactory {
    //创建口罩
    IMask createMask();
    //创建防护服
    IProtectiveSuit createSuit();
}

public class LowEndFactory implements IFactory {
    @Override
    public IMask createMask() {
        IMask mask =  new LowEndMask();
        // .....
        //  LowEndMask的100行初始化代码
        return mask;
    }

    @Override
    public IProtectiveSuit createSuit() {
        IProtectiveSuit suit =  new LowEndProtectiveSuit();
        // .....
        //  LowEndProtectiveSuit的100行初始化代码
        return suit;
    }
}

public class HighEndFactory implements IFactory {
    @Override
    public IMask createMask() {
        IMask mask =  new HighEndMask();
        // .....
        // HighEndMask的100行初始化代码
        return mask;
    }

    @Override
    public IProtectiveSuit createSuit() {
        IProtectiveSuit suit =  new HighEndProtectiveSuit();
        // .....
        //  HighEndProtectiveSuit的100行初始化代码
        return suit;
    }
}

// 客户端调用
public class Test {

    public static void main(String[] args) {
        IFactory factoryA = new LowEndFactory();
        IFactory factoryB = new HighEndFactory();
        //创建低端口罩
        IMask maskA = factoryA.createMask();
        //创建高端口罩
        IMask maskB = factoryB.createMask();
        //创建低端防护服
        IProtectiveSuit suitA = factoryA.createSuit();
        //创建高端防护服
        IProtectiveSuit suitB = factoryB.createSuit();

        maskA.showMask();
        maskB.showMask();
        suitA.showSuit();
        suitB.showSuit();
    }
}
```

# 总结

简单 / 静态工厂模式是工厂方法模式的缩减；抽象工厂模式是工厂方法模式的增强。

# 进阶

## Spring框架中的工厂模式

引入了Java的 `反射` 特性，所以可以支持动态的替换需要生成对象的内部依赖