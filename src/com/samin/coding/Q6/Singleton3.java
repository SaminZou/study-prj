package com.samin.coding.Q6;

public class Singleton3 {
    private Singleton3() {}

    public enum SingletonEnum {
        // 创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private Singleton3 singleton3;

        SingletonEnum() {
            singleton3 = new Singleton3();
        }

        public Singleton3 getSingleton3() {
            return singleton3;
        }
    }

    public static void main(String[] args) {
        Singleton3 singleton3 = SingletonEnum.INSTANCE.getSingleton3();
    }
}
