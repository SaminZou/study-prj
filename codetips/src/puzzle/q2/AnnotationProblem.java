package puzzle.q2;

/**
 * Java 的注释不一定不会执行，可能需要排除这种特殊情况
 *
 * <p>编译器会解析 Unicode 字符，可能导致代码会在编译时报错
 *
 * <p>以下内容放在main方法里面会编译报错 // \u000d 运行编译报错
 *
 * @author samin
 * @date 2021-01-10
 */
public class AnnotationProblem {

    public static void main(String[] args) {
        // 编写注释的时候，尽量避免 Unicode字符，以免编译出错

        // PS："\u03C0"字符可以编译为圆周率派字符
    }
}
