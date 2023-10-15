package structural.composite;

import structural.decorator.Beverage;

public class Decat extends Beverage {

    public Decat() {
        description = "Decat";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
