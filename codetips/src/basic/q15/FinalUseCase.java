package basic.q15;

/**
 * final 修饰变量赋值写法
 *
 * @author samin
 * @date 2021-02-13
 */
public class FinalUseCase {

    /** 类变量可以直接赋值、static 块中赋值 */
    private static final int FINAL_DEFINED_VARIABLE_A = 0;

    private static final int FINAL_DEFINED_VARIABLE_B;

    static {
        FINAL_DEFINED_VARIABLE_B = 10;
    }

    /** 成员变量可以直接声明、在代码块中赋值、在构造器中赋值 */
    private final int FINAL_DEFINED_VARIABLE_C = 100;

    private final int FINAL_DEFINED_VARIABLE_D;
    private final int FINAL_DEFINED_VARIABLE_E;

    {
        FINAL_DEFINED_VARIABLE_D = 1000;
    }

    public FinalUseCase(int a) {
        FINAL_DEFINED_VARIABLE_E = 1000;
    }

    public void showFinalMethod(final int theUniqueNum) {
        // 此句会报错
        // theUniqueNum = 10;

        final int theOtherNum;
        // 只能声明一次
        theOtherNum = 10;

        // 此句会报错
        // theOtherNum = 20;
    }

    public static class FinalParentClass {
        public final void test() {}

        public final void test(String a) {}
    }

    public static class FinalSubClass extends FinalParentClass {
        // 报错
        // public final void test(String a) {}

        public final void test(String a, String b) {}
    }
}
