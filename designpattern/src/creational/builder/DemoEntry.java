package creational.builder;

import creational.builder.style1.Builder;
import creational.builder.style1.BuilderA;
import creational.builder.style1.BuilderB;
import creational.builder.style1.Director;
import creational.builder.style1.Production;
import creational.builder.style2.ProductionObj;

/**
 * 建造者模式
 *
 * <p> Builder 接口或抽象类，非必选 ConcreteBuilder 具体的建造者
 *
 * <p> 可以有多个 Product 普通的类，具体要被实例化的对象 Director 导演，统一指挥建造者建造目标，非必选
 *
 * <p>解决问题：参数多造成需要的构造器多，初始化分步设置属性容易出错
 */
public class DemoEntry {

    public static void main(String[] args) {
        // style1 中演示的是有 Director 的建造者模式（传统的 Builder 模式），以下是具体的调用方式
        Builder builderA = new BuilderA();
        Director directorA = new Director(builderA);
        // Builder 包含了对象所有属性的声明
        Production a = directorA.construct();
        a.getPart1();

        Builder builderB = new BuilderB();
        Director directorB = new Director(builderB);
        Production b = directorB.construct();
        b.getPart2();

        System.out.println("----------------------------------------------------------");

        // style2 中演示的是无 Director 的建造者模式，简化的使用模式
        ProductionObj obj = new ProductionObj.Builder("a", "d").build();
        System.out.println(obj);

        // 可以指定特定元素值
        obj = new ProductionObj.Builder("a", "d").b("bbb")
                                                 .c("ccc")
                                                 .e("ee")
                                                 .build();
        System.out.println(obj);

        // style2 的使用方式比较实用，很容易理解此方法的用意，在 okhttp 工具包中有体现
    }
}
