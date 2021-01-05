package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {

    static Map<String, Shape> shapes = new HashMap<>();

    public static Shape getShape(String key) {
        Shape shape = shapes.get(key);
        // 如果shape==null,表示不存在,则新建,并且保持到共享池中
        if (shape == null) {
            shape = new Circle(key);
            shapes.put(key, shape);
        }
        return shape;
    }

    public static int getSum() {
        return shapes.size();
    }
}
