package structural.bridge.inf;

import structural.bridge.inf.Color;

public class Gray implements Color {

    @Override
    public void paint(String shape) {
        System.out.println("灰色的" + shape);
    }
}
