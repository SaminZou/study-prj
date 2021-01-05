package creational.factory;

public class IceCreamFactory implements Factory {

    @Override
    public Product factory() {
        return new IceCream();
    }
}
