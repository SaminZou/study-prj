package behavioural.mediator.mediator;

import behavioural.mediator.component.Person;

public interface Mediator {

    /**
     * 通知组件
     *
     * @param message
     * @param person
     */
    void notify(String message, Person person);
}
