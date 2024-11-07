package concurrent.threadbasic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable 不好的用例
 * <p>
 * Description: 不好的使用方式
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-24
 */
public class CallableBadCase {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 这种使用是无意义的
        Callable<String> task = () -> "";
        FutureTask<String> ft = new FutureTask<>(task);
        new Thread(ft).start();
        // 阻塞
        System.out.println(ft.get());
    }
}