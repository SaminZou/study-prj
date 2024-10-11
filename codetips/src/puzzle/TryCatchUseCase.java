package puzzle;

/**
 * Q：try catch 性能测试
 *
 * <p> 从 class 编译后文件看，try catch 并不会影响性能
 *
 * <p> 得益于 JIT，性能几乎没有影响
 *
 * @author samin
 * @date 2023-03-06
 */
public class TryCatchUseCase {

    // 100W
    private static final int TIMES = 1000000;
    private static final float STEP_NUM = 1f;
    private static final float START_NUM = Float.MIN_VALUE;

    public static void main(String[] args) {
        int times = 50;
        TryCatchUseCase executeTryCatch = new TryCatchUseCase();
        // 每个方法执行 50 次
        while (--times >= 0) {
            System.out.println("times=".concat(String.valueOf(times)));
            executeTryCatch.executeMillionsEveryTryWithFinally();
            executeTryCatch.executeMillionsEveryTry();
            executeTryCatch.executeMillionsOneTry();
            executeTryCatch.executeMillionsNoneTry();
            executeTryCatch.executeMillionsTestReOrder();
        }
    }

    /**
     * 千万次浮点运算不使用 try catch
     */
    public void executeMillionsNoneTry() {
        float num = START_NUM;
        long start = System.nanoTime();
        for (int i = 0; i < TIMES; ++i) {
            num = num + STEP_NUM + 1f;
            num = num + STEP_NUM + 2f;
            num = num + STEP_NUM + 3f;
            num = num + STEP_NUM + 4f;
            num = num + STEP_NUM + 5f;
            num = num + STEP_NUM + 1f;
            num = num + STEP_NUM + 2f;
            num = num + STEP_NUM + 3f;
            num = num + STEP_NUM + 4f;
            num = num + STEP_NUM + 5f;
        }
        long nao = System.nanoTime() - start;
        long million = nao / 1000000;
        System.out.println("noneTry   sum:" + num + "  million:" + million + "  nao: " + nao);
    }

    /**
     * 千万次浮点运算最外层使用 try catch
     */
    public void executeMillionsOneTry() {
        float num = START_NUM;
        long start = System.nanoTime();
        try {
            for (int i = 0; i < TIMES; ++i) {
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
            }
        } catch (Exception e) {
        }
        long nao = System.nanoTime() - start;
        long million = nao / 1000000;
        System.out.println("oneTry    sum:" + num + "  million:" + million + "  nao: " + nao);
    }

    /**
     * 千万次浮点运算循环内使用 try catch
     */
    public void executeMillionsEveryTry() {
        float num = START_NUM;
        long start = System.nanoTime();
        for (int i = 0; i < TIMES; ++i) {
            try {
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
            } catch (Exception e) {
            }
        }
        long nao = System.nanoTime() - start;
        long million = nao / 1000000;
        System.out.println("evertTry  sum:" + num + "  million:" + million + "  nao: " + nao);
    }

    /**
     * 千万次浮点运算循环内使用 try catch，并使用 finally
     */
    public void executeMillionsEveryTryWithFinally() {
        float num = START_NUM;
        long start = System.nanoTime();
        for (int i = 0; i < TIMES; ++i) {
            try {
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
            } catch (Exception e) {
            } finally {
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
            }
        }
        long nao = System.nanoTime() - start;
        long million = nao / 1000000;
        System.out.println("finalTry  sum:" + num + "  million:" + million + "  nao: " + nao);
    }

    /**
     * 千万次浮点运算，循环内使用多个 try catch
     */
    public void executeMillionsTestReOrder() {
        float num = START_NUM;
        long start = System.nanoTime();
        for (int i = 0; i < TIMES; ++i) {
            try {
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
            } catch (Exception e) {
            }
            try {
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
            } catch (Exception e) {
            }
            try {
                num = num + STEP_NUM + 1f;
                num = num + STEP_NUM + 2f;
            } catch (Exception e) {
            }
            try {
                num = num + STEP_NUM + 3f;
                num = num + STEP_NUM + 4f;
                num = num + STEP_NUM + 5f;
            } catch (Exception e) {
            }
        }
        long nao = System.nanoTime() - start;
        long million = nao / 1000000;
        System.out.println("orderTry  sum:" + num + "  million:" + million + "  nao: " + nao);
    }
}