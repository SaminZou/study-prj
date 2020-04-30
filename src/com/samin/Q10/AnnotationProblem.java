package com.samin.Q10;

/**
 * Java的注释不一定不会执行，可能需要排除这种特殊情况
 * 编译器会解析Unicode字符，可能导致代码会在编译时报错
 */
public class AnnotationProblem {
    public static void main(String[] args) {
        // \u000d 运行编译报错

        // 编写注释的时候，尽量避免Unicode字符，以免编译出错

        // PS："\u03C0"字符可以编译为圆周率派字符
    }
}