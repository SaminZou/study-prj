package factory;

/*
 * 工厂模式
 * */
public class Client {
    public static void main(String[] args) {
        // 生产雪糕
        Factory iceCreamFactory = new IceCreamFactory();
        Product iceCream = iceCreamFactory.factory();
        iceCream.product();

        // 生产披萨
        Factory pizzaFactory = new PizzaFactory();
        Product pizza = pizzaFactory.factory();
        pizza.product();
    }
}
