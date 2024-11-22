package behavioural.iterator.entity;

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
        Iterator tvIterator = tvMenu.createIterator();
        Iterator filmIterator = filmMenu.createIterator();

        System.out.println("电视节目有:");
        printMenuDetails(tvIterator);
        System.out.println("----------------------------------------------------------------");
        System.out.println("电影节目有:");
        printMenuDetails(filmIterator);
    }

    private void printMenuDetails(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.print("channe:" + menuItem.getChannel() + ",  ");
            System.out.print("name:" + menuItem.getName() + ",  ");
            System.out.println("description :" + menuItem.getDescription());
        }
    }
}
