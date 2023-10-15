package structural.decorator;

/**
 * 饮料
 * <p>
 * Description: 饮料
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 */
public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
