package behavioural.iterator;

import behavioural.iterator.iterablecollection.TelevisionMenu;
import behavioural.iterator.iterablecollection.concrete.FilmMenu;
import behavioural.iterator.iterator.Iterator;

public class MainMenu {

    TelevisionMenu tvMenu;
    FilmMenu filmMenu;

    public MainMenu(TelevisionMenu tvMenu, FilmMenu filmMenu) {
        this.tvMenu = tvMenu;
        this.filmMenu = filmMenu;
    }

    public void printMenu() {
        Iterator tvIterator = tvMenu.createIrerator();
        Iterator filmIterator = filmMenu.createIrerator();

        System.out.println("电视节目有:");
        printMenu(tvIterator);
        System.out.println("----------------------------------------------------------------");
        System.out.println("电影节目有:");
        printMenu(filmIterator);
    }

    private void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.print("channe:" + menuItem.getChanne() + ",  ");
            System.out.print("name:" + menuItem.getName() + ",  ");
            System.out.println("description :" + menuItem.getDescription());
        }
    }
}
