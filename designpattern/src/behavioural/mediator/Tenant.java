package behavioural.mediator;

public class Tenant extends Person {

    Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 与中介者联系
    public void constact(String message) {
        mediator.constact(message, this);
    }

    // 与中介者联系
    public void getMessage(String message) {
        System.out.println("租房者:" + name + ",获得信息：" + message);
    }
}
