package structural.bridge;

import structural.bridge.abs.Rectangle;
import structural.bridge.abs.Shape;
import structural.bridge.abs.Square;
import structural.bridge.inf.Black;
import structural.bridge.inf.Color;
import structural.bridge.inf.White;

/**
 * 桥接模式
 *
 * <p> 将抽象部分与它的实现部分分离，使它们都可以独立地变化
 * <p> 接口做为抽象类的一个属性
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        // 白色
        Color white = new White();
        // 正方形
        Shape square = new Square();
        // 白色的正方形
        square.setColor(white);
        square.draw();

        // 黑色
        Black black = new Black();
        // 长方形
        Shape rectangle = new Rectangle();
        rectangle.setColor(black);
        rectangle.draw();
    }
}
