package factory;

public class IceCream implements Product {

    @Override
    public void product() {
        // 具体的业务逻辑
        System.out.println("雪糕做好了！！");
    }
}
