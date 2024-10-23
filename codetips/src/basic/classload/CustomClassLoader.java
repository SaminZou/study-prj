package basic.classload;

import java.lang.reflect.Field;

public class CustomClassLoader extends ClassLoader {

    private String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            Class<?> aClass = super.loadClass(name);

            try {
                Field testField = aClass.getDeclaredField("field");
                testField.setAccessible(true);
                testField.set(null, "testFiled");
            } catch (NoSuchFieldException noSuchFieldException) {
                System.out.println("no field");
            }

            // 简化处理，直接返回系统类加载器加载的类（仅作为示例）
            return aClass;
        } catch (Exception e) {
            throw new ClassNotFoundException(name);
        }
    }
}