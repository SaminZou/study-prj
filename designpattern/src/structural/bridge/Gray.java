package structural.bridge;

public class Gray implements Color {

    @Override
    public void paint(String shape) {
        System.out.println("灰色的" + shape);
    }
}
