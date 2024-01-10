package template.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构数据过滤
 * <p>
 * Description: 树形结构数据过滤
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-01-10
 */
public class TreeDataFilterProcess {

    public static void main(String[] args) {
        List<TreeData> treeDatas = TreeDataProcess.getMenuTreeList(TreeDataMocker.mockData());
        List<String> codes = List.of("Node4.3.2", "Node3.1", "Node3.2.3", "Node3.2.1", "Node2");
        List<TreeData> treeDataList = filterTreeDatas(treeDatas, codes);
        TreeDataPrinter.printBeautifulTreeData(treeDataList);
    }

    public static List<TreeData> filterTreeDatas(List<TreeData> treeDatas, List<String> codes) {
        List<TreeData> filteredResults = new ArrayList<>();

        for (TreeData item : treeDatas) {
            TreeData filteredItem = filterItem(item, codes);
            if (filteredItem != null) {
                filteredResults.add(filteredItem);
            }
        }

        return filteredResults;
    }

    /**
     * 递归过滤每个节点及其子节点
     *
     * @param item
     * @param codes
     * @return
     */
    private static TreeData filterItem(TreeData item, List<String> codes) {
        if (codes.contains(item.getCode())) {
            // 如果当前节点满足过滤条件，返回当前节点及其子节点
            return TreeData.getInstance(item, filterTreeDatas(item.getChildren(), codes));
        }

        List<TreeData> filteredChildren = new ArrayList<>();

        for (TreeData childrenItem : item.getChildren()) {
            TreeData filteredSubItem = filterItem(childrenItem, codes);
            if (filteredSubItem != null) {
                filteredChildren.add(filteredSubItem);
            }
        }

        if (!filteredChildren.isEmpty()) {
            // 如果当前节点的子节点中有满足过滤条件的节点，返回当前节点及其满足条件的子节点
            return TreeData.getInstance(item, filteredChildren);
        }

        // 如果当前节点及其子节点都不满足过滤条件，返回 null
        return null;
    }
}
