package structural.decorator;

import structural.decorator.component.DarkRoast;
import structural.decorator.component.Espresso;
import structural.decorator.component.HouseBlend;
import structural.decorator.decorator.Milk;
import structural.decorator.decorator.Mocha;
import structural.decorator.decorator.SoyMilk;
import structural.decorator.decorator.Whip;

/**
 * 装饰器模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        // 基础饮品
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + " $" + espresso.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new Milk(houseBlend);
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());

        // 通过基础饮品 Dark Roast 来调配
        Beverage darkRoast = new DarkRoast();
        // 摩卡、加豆奶、摇晃、加奶
        darkRoast = new Mocha(darkRoast);
        darkRoast = new SoyMilk(darkRoast);
        darkRoast = new Whip(darkRoast);
        darkRoast = new Milk(darkRoast);
        System.out.println(darkRoast.getDescription() + " $" + darkRoast.cost());
    }
}
