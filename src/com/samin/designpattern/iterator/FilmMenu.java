package com.samin.designpattern.iterator;

public class FilmMenu implements TelevisionMenu {

    static final int MAX_ITEMS = 5; // 菜单最大长度
    MenuItem[] menuItems;
    int numberOfItems = 0;

    // 构造函数完成初始化
    public FilmMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem(1, "绝世天劫", "这是布鲁斯威利斯主演的...");
        addItem(2, "达芬奇密码", "这是我最喜欢的电影之一...");
        addItem(3, "黑客帝国123", "不知道你能够看懂不???");
        addItem(4, "我的女友是机器人", "一部我不反感的经典爱情电影...");
        addItem(5, "肖申克的救赎", "自由，幸福，离你有多远");
    }

    // 将电影解决添加到菜单项中
    public void addItem(int channe, String name, String description) {
        MenuItem tvmenuiItem = new MenuItem(channe, name, description);
        // 判断数组是否越界
        if (numberOfItems > MAX_ITEMS) {
            System.out.println("不好意思，菜单满了....");
        } else {
            menuItems[numberOfItems] = tvmenuiItem;
            numberOfItems++;
        }
    }

    public Iterator createIrerator() {
        return new FilmMenuIterator(menuItems);
    }
}
