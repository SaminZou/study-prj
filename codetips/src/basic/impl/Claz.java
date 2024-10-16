package basic.impl;

public class Claz implements ItfA, ItfB {

    public static void main(String[] args) {
        // Claz 单实现 ItfA 或者 ItfB，不需要实现 print 可以直接调用
        // Claz 同时实现 ItfA 和 ItfB 需要实现方法来避免错误
        new Claz().print();
    }

    @Override
    public void print() {
        ItfA.super.print();
        ItfB.super.print();

        System.out.println("Claz");
    }
}