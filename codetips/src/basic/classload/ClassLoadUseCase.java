package basic.classload;

import java.lang.reflect.InvocationTargetException;

public class ClassLoadUseCase {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 会执行静态初始化方法
        Class<?> aClass = Class.forName("basic.classload.TestClass");
        TestClass testClass1 = (TestClass) aClass.getDeclaredConstructor()
                .newInstance();
        testClass1.test("testClass1");

        // 默认的类加载器
        // 实际上 basic.q13.TestClass 会被转换成 /basic/q13/TestClass.class 去加载文件流，通过 defineClass 生成类
        Class<?> bClass = ClassLoadUseCase.class.getClassLoader()
                .loadClass("basic.classload.TestClass");
        TestClass testClass2 = (TestClass) bClass.getDeclaredConstructor()
                .newInstance();
        testClass2.test("testClass2");

        // 自定义类加载器
        Class<?> cClass = new CustomClassLoader("").loadClass("basic.classload.TestClass");
        TestClass testClass3 = (TestClass) cClass.getDeclaredConstructor()
                .newInstance();
        testClass3.test("testClass3");
    }
}