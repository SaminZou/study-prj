package structural.decorator.decorator;

import structural.decorator.Beverage;
import structural.decorator.CondimentDecorator;

public class SoyMilk extends CondimentDecorator {

    Beverage beverage;

    public SoyMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , SoyMilk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
