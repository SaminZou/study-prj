package concurrent.q4;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor 用例
 *
 * @author samin
 * @date 2022-03-02
 */
public class ThreadPoolExecutorUseCase {

    public static void main(String[] args) {
        Executor executor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 6; i++) {
            System.out.println("添加第 " + i + " 个任务");
            executor.execute(new TestThread("名字" + i));

        }
    }

    private static class TestThread implements Runnable {

        String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread().getName() + " 执行：" + name + " run");
        }
    }
}
