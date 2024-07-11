package concurrent.q7;

/**
 * 银行账户类
 * <p>
 * Description: 用于展示锁的可重入性
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-11
 */
public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(double amount)  {
        if (this.checkFunds(amount)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.balance -= amount;
            System.out.println(Thread.currentThread()
                    .getName() + " withdraw " + amount);
        }
    }

    public synchronized boolean checkFunds(double amount) {
        return this.balance >= amount;
    }
}