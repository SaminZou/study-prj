package behavioural.memento.memento;

import behavioural.memento.originator.Player;

public class ConcreteMemento implements Memento {

    private final Player player;

    public ConcreteMemento(Player player) {
        this.player = player;
    }

    @Override
    public Player restore() {
        return this.player;
    }
}
