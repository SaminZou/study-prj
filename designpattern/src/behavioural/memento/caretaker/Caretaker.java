package behavioural.memento.caretaker;

import behavioural.memento.memento.ConcreteMemento;
import behavioural.memento.originator.Player;
import java.util.Stack;

public class Caretaker {

    Stack<ConcreteMemento> history;

    public Caretaker() {
        this.history = new Stack<>();
    }

    public void save(Player player) {
        history.push(player.createSnapshot());
    }

    public ConcreteMemento undo() {
        return history.pop();
    }
}
