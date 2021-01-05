package iterator;

public class FilmMenuIterator implements Iterator {

    MenuItem[] menuItems;
    int position = 0;

    public FilmMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        return position <= menuItems.length - 1 && menuItems[position] != null;
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems[position];
        position++;
        return menuItem;
    }
}
