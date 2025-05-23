package behavioural.iterator.iterablecollection.concrete;

import behavioural.iterator.entity.MenuItem;
import behavioural.iterator.iterablecollection.TelevisionMenu;
import behavioural.iterator.iterator.Iterator;
import behavioural.iterator.iterator.concrete.TVChannelMenuIterator;
import java.util.ArrayList;
import java.util.List;

public class TVChannelMenu implements TelevisionMenu {

    List<MenuItem> menuItems;

    /**
     * 构造函数完成初始化
     */
    public TVChannelMenu() {
        menuItems = new ArrayList<MenuItem>();
        addItem(1, "CCTV-1", "This is CCTV-1");
        addItem(2, "CCTV-2", "This is CCTV-2");
        addItem(3, "CCTV-3", "This is CCTV-3");
        addItem(4, "CCTV-4", "This is CCTV-4");
        addItem(5, "CCTV-5", "This is CCTV-5");
    }

    /**
     * 将电视频道节目添加菜单集合中
     *
     * @param channe
     * @param name
     * @param description
     */
    @Override
    public void addItem(int channe, String name, String description) {
        MenuItem tvMenuItem = new MenuItem(channe, name, description);
        menuItems.add(tvMenuItem);
    }

    @Override
    public Iterator createIterator() {
        return new TVChannelMenuIterator(menuItems);
    }
}
