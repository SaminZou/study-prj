package behavioural.template;

/**
 * 密码开锁的单车
 * <p>
 * Description: 密码开锁的单车
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-10-20
 */
public class CodeBicycle extends AbstractClass {

    @Override
    protected void unlock() {
        System.out.println("========" + "密码开锁" + "========");
    }

    @Override
    protected void ride() {
        System.out.println(getClass().getSimpleName() + "骑起来很拉风");
    }

    @Override
    protected void isNeedUnlock(boolean isNeedUnlock) {
        this.isLock = isNeedUnlock;
    }
}
