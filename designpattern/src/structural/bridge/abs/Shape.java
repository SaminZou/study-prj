package structural.bridge.abs;

import structural.bridge.inf.Color;

public abstract class Shape {

    /**
     * 接口类做为一个属性
     */
    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
