package structural.decorator.component;

import structural.decorator.Beverage;

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 2.05;
    }
}
