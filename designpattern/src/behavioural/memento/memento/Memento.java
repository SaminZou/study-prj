package behavioural.memento.memento;

import behavioural.memento.originator.Originator;
import behavioural.memento.originator.Player;

public interface Memento {

    Originator restore();
}
