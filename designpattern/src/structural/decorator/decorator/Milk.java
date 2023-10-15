package structural.decorator.decorator;

import structural.decorator.Beverage;
import structural.decorator.CondimentDecorator;

public class Milk extends CondimentDecorator {

    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , Milk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.3;
    }
}
