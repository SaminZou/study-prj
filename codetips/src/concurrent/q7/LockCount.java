package concurrent.q7;

/**
 * 锁的重入性
 * <p>
 * Description: 锁的对象是同一个实例，是否可重入直接决定是否会产生死锁
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-11
 */
public class LockCount {

    public static void main(String[] args) {
        BankAccount saminAccount = new BankAccount(520.1314D);

        new Thread(() -> saminAccount.withdraw(100)).start();
        new Thread(() -> saminAccount.withdraw(100)).start();
        new Thread(() -> saminAccount.withdraw(100)).start();
    }
}