package behavioural.mediator.component;

import behavioural.mediator.mediator.Mediator;

public class Tenant implements Person {

    private String name;
    private Mediator mediator;

    public Tenant(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    /**
     * 与中介者联系
     *
     * @param message
     */
    @Override
    public void contact(String message) {
        mediator.notify(message, this);
    }

    /**
     * 与中介者联系
     *
     * @param message
     */
    public void getMessage(String message) {
        System.out.println("租房者:" + name + ",获得信息：" + message);
    }
}
