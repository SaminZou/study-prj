package puzzle;

/**
 * 私有方法不会被继承
 * <p>
 * Description: 私有方法不会被继承
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-10-11
 */
public class PrivateMethodParent {

    public void testPublic(){
        System.out.println("parent.test.public");
    }

    private void test() {
        System.out.println("parent.test");
    }
}