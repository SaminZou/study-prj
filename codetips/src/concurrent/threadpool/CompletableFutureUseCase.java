package concurrent.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 用例
 * <p>
 * Description: 可以更优雅的创建执行线程任务，提高并发程度
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-07-24
 */
public class CompletableFutureUseCase {

    public static void main(String[] args) {
        CompletableFuture<Void> async1 = CompletableFuture.runAsync(() -> System.out.println("没有返回值"));

        CompletableFuture<String> async2 = CompletableFuture.supplyAsync(() -> "value");
        System.out.println(async2.join());

        CompletableFuture<String> async3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "run slow";
        });
        CompletableFuture<String> async4 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "run fast";
        });
        // 获取执行更快执行完成的结果
        CompletableFuture<Object> anyFutures = CompletableFuture.anyOf(async3, async4);
        // 阻塞到获取到最快任务执行完结束
        System.out.println(anyFutures.join());

        // 适合可以并发执行并需要返回结果的任务，模拟用户注册
        CompletableFuture<Boolean> async5 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("用户名唯一");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
        CompletableFuture<Boolean> async6 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(400);
                System.out.println("手机号唯一");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
        CompletableFuture<Boolean> async7 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println("满足密码强度");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return false;
        });
        CompletableFuture<Boolean> allFuture = CompletableFuture.allOf(async5, async6, async7)
                                                                .thenApply(res -> async5.join() && async6.join()
                                                                        && async7.join());
        System.out.println("注册结果: " + allFuture.join());

        // TODO whenComplete 没有返回值，可以向后续任务传递异常信息
        // TODO handle 有返回值，且返回的 CompletableFuture 为回调结果
        // TODO 链式处理：thenRun 没入参也没返回值；thenAccept 可以入参没有返回值；thenApply 可以入参并返回结果
    }
}