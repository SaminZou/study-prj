package structural.adapter;

import structural.adapter.adapter.Tame;
import structural.adapter.adapter.TameWolfAdapter;

/**
 * 适配器模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class DemoEntry {

    public static void main(String[] args) {
        // 现实中的狗
        Dog dog = new Dog();
        // 现实中的狼
        Wolf wolf = new Wolf();

        // 狗可以直接按照命令行动
        dog.wang();
        dog.run();

        System.out.println("-------------");

        // 通过适配器可以获取一只被驯服的狼
        Tame tameWolf = new TameWolfAdapter(wolf);
        // 可以和狗一样直接按照预期调用
        tameWolf.wang();
        tameWolf.run();
    }
}
