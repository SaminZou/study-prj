package bridge;

public class Black implements Color {

    @Override
    public void paint(String shape) {
        System.out.println("黑色的" + shape);
    }
}
