package puzzle;

/**
 * 测试私有方法被继承
 * <p>
 * Description: 测试私有方法被继承
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-10-11
 */
public class PrivateMethodSon extends PrivateMethodParent {

    /**
     * 私有方法不会被继承，不存在重写
     */
    public void test() {
        System.out.println("son.test");
    }

    public static void main(String[] args) {
        new PrivateMethodSon().testPublic();
        new PrivateMethodSon().test();
    }
}