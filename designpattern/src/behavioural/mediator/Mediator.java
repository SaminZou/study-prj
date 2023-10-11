package behavioural.mediator;

public abstract class Mediator {

    /**
     * 申明一个联络方法
     *
     * @param message
     * @param person
     */
    public abstract void constact(String message, Person person);
}
