package behavioural.iterator;

import behavioural.iterator.iterablecollection.concrete.FilmMenu;
import behavioural.iterator.iterablecollection.concrete.TVChannelMenu;

/**
 * 迭代器模式
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        // 为了代码整洁，数据初始化在构造器中编写
        TVChannelMenu tvMenu = new TVChannelMenu();
        FilmMenu filmMenu = new FilmMenu();

        // 为了代码整洁，MainMenu 充当了 Client 角色
        MainMenu mainMenu = new MainMenu(tvMenu, filmMenu);
        mainMenu.printMenu();
    }
}
