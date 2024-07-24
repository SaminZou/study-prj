package puzzle.q8;

public class Son extends Parent {

    /**
     * 私有方法不会被继承，不存在重写
     */
    public void test() {
        System.out.println("son.test");
    }

    public static void main(String[] args) {
        new Son().testPublic();
        new Son().test();
    }
}