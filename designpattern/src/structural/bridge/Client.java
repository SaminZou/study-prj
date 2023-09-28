package structural.bridge;

/**
 * 桥接模式
 *
 * <p> 抽象是实现分离
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
