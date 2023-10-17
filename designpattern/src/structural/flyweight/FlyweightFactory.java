package structural.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 树的品种是可以固定的，所以可以复用
 * <p>
 * Description: 树的品种是可以固定的，所以可以复用
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-10-17
 */
public class FlyweightFactory {

    static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String key) {
        TreeType treeType = treeTypeMap.get(key);
        // 如果 shape 为空，表示不存在，则新建，并且保持到共享池中
        if (Objects.isNull(treeType)) {
            treeType = new TreeType(key);
            treeTypeMap.put(key, treeType);
        }
        return treeType;
    }

    public static int getSum() {
        return treeTypeMap.size();
    }
}
