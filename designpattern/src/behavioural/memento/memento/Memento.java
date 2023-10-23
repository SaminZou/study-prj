package behavioural.memento.memento;

import behavioural.memento.originator.Originator;

public interface Memento {

    Originator restore();
}
