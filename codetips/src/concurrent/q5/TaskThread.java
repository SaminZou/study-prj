package concurrent.q5;

public class TaskThread implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 500000; i++) {
                if (Thread.interrupted()) {
                    System.out.println("线程已经终止， for循环不再执行");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }

            System.out.println("这是for循环外面的语句，如果不是抛异常而是 break，也会被执行");
        } catch (InterruptedException e) {
            System.out.println("进入MyThread.java类中的catch了。。。");
            e.printStackTrace();
        }
    }
}
