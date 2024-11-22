package behavioural.iterator.iterablecollection;

import behavioural.iterator.iterator.Iterator;

public interface TelevisionMenu {

    void addItem(int channel, String name, String description);

    Iterator createIterator();
}
