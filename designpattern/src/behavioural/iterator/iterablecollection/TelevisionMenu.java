package behavioural.iterator.iterablecollection;

import behavioural.iterator.iterator.Iterator;

public interface TelevisionMenu {

    void addItem(int channe, String name, String description);

    Iterator createIrerator();
}
