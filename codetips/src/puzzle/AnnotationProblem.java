package puzzle;

/**
 * Q：Unicode 编码注释问题
 *
 * Java 的注释不一定不会执行，可能需要排除这种特殊情况
 *
 * <p>编译器会解析 Unicode 字符，可能导致代码会在编译时报错
 *
 * <p>以下内容放在main方法里面会编译报错 // \u000d 运行编译报错
 *
 * @author samin
 * @date 2021-01-10
 * @version 1.1
 */
public class AnnotationProblem {

    public static void main(String[] args) {
        // Demonstrate Unicode escape characters behavior in comments
        demonstrateUnicodeInComments();
    }

    /**
     * Demonstrate Unicode escape characters behavior during compilation
     */
    private static void demonstrateUnicodeInComments() {
        // Warning: The following comment contains Unicode escape characters
        // During compilation, \\u000d is parsed as newline, making code execute

        // Safe comment example - using regular characters
        System.out.println("Normal executing code");

        // Unicode character example: pi symbol
        System.out.println("Unicode character example: \u03C0 = " + Math.PI);

        // Best practice reminder
        System.out.println("Tip: Avoid Unicode escape characters in comments");
    }
}
