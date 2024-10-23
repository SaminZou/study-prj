package basic.classload;

import java.util.Objects;

public class TestClass {

    static String field;

    static {
        System.out.println("static block");
    }

    public void test(String name) {
        if (Objects.nonNull(field)) {
            System.out.println("test: " + field);
        } else {
            System.out.println("test: " + name);
        }
    }
}