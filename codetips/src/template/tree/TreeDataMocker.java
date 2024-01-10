package template.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeDataMocker {

    public static List<TreeData> mockData() {
        List<TreeData> list = new ArrayList<>();

        list.add(TreeData.getInstance("Node4", "Node4", null, 2));
        list.add(TreeData.getInstance("Node4.1", "Node4.1", "Node4", 1));
        list.add(TreeData.getInstance("Node4.3", "Node4.3", "Node4", 3));
        // 验证顺序
        list.add(TreeData.getInstance("Node4.3.1", "Node4.3.1", "Node4.3", 2));
        list.add(TreeData.getInstance("Node4.3.2", "Node4.3.2", "Node4.3", 1));
        list.add(TreeData.getInstance("Node4.4", "Node4.4", "Node4", 4));
        list.add(TreeData.getInstance("Node4.2", "Node4.2", "Node4", 2));

        list.add(TreeData.getInstance("Node1", "Node1", null, 1));

        list.add(TreeData.getInstance("Node3", "Node3", "", 3));
        list.add(TreeData.getInstance("Node3.1", "Node3.1", "Node3", 1));
        list.add(TreeData.getInstance("Node3.2", "Node3.2", "Node3", 2));
        // 验证顺序
        list.add(TreeData.getInstance("Node3.1.1", "Node3.1.1", "Node3.1", 1));
        list.add(TreeData.getInstance("Node3.1.2", "Node3.1.2", "Node3.1", 3));
        list.add(TreeData.getInstance("Node3.1.3", "Node3.1.3", "Node3.1", 2));
        list.add(TreeData.getInstance("Node3.2.1", "Node3.2.1", "Node3.2", 1));
        list.add(TreeData.getInstance("Node3.2.2", "Node3.2.2", "Node3.2", 2));
        list.add(TreeData.getInstance("Node3.2.3", "Node3.2.3", "Node3.2", 3));

        list.add(TreeData.getInstance("Node2", "Node2", "", 4));
        list.add(TreeData.getInstance("Node2.1", "Node2.1", "Node2", 1));
        list.add(TreeData.getInstance("Node2.2", "Node2.2", "Node2", 2));

        return list;
    }
}
