package collection;

/**
 * 模拟 HashMap 的列表节点
 * <p>
 * Description: 模拟 HashMap 的列表节点
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-03
 */
public class EntryUseCase {

    private Object key;
    private Object value;
    private EntryUseCase next;
    private int hash;

    public EntryUseCase(Object key, EntryUseCase next) {
        this.key = key;
        this.next = next;
    }

    public static void main(String[] args) {
        EntryUseCase header = new EntryUseCase(new Object(), null);

        header.next = new EntryUseCase(new Object(), null);

        // 新节点直接当作头部插入最快
        header = new EntryUseCase(new Object(), header);
    }
}