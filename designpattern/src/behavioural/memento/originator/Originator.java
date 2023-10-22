package behavioural.memento.originator;

import behavioural.memento.memento.Memento;

public interface Originator {

    Memento createSnapshot();
}
