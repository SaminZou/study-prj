package collection.map;

/**
 * 模拟 HashMap 的列表节点
 * <p>
 * Description: 模拟 HashMap 的列表节点
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-03
 */
public class Entry {

    private Object key;
    private Object value;
    private Entry next;
    private int hash;

    public Entry(Object key, Entry next) {
        this.key = key;
        this.next = next;
    }

    public static void main(String[] args) {
        Entry header = new Entry(new Object(), null);

        header.next = new Entry(new Object(), null);

        // 新节点直接当作头部插入最快
        header = new Entry(new Object(), header);
    }
}