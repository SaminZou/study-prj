package behavioural.iterator;

/**
 * 迭代器模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        TVChanneMenu tvMenu = new TVChanneMenu();
        FilmMenu filmMenu = new FilmMenu();

        MainMenu mainMenu = new MainMenu(tvMenu, filmMenu);
        mainMenu.printMenu();
    }
}
