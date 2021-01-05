package behavioural.template;

public abstract class AbstractClass {

    // 默认需要开锁
    protected boolean isLock = true;

    protected abstract void unlock();

    protected abstract void ride();

    // 钩子方法，子类可实现
    protected void isNeedUnlock(boolean isLock) {
        this.isLock = isLock;
    }

    // 模板方法，负责调度基本方法，子类不可实现
    public final void use() {
        if (isLock) {
            unlock();
        } else {
            System.out.println("========锁坏了，不用解锁========");
        }
        ride();
    }
}
