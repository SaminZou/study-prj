package template.tree;

import java.util.*;

/**
 * 树形结构数据组装
 * <p>
 * Description: 树形结构数据组装
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-01-10
 */
public class TreeDataProcess {

    public static void main(String[] args) {
        TreeDataPrinter.printBeautifulTreeData(getMenuTreeList(TreeDataMocker.mockData()));
    }

    public static List<TreeData> getMenuTreeList(List<TreeData> treeDataList) {
        // 生成 Map 数据集合
        Map<String, TreeData> nodeMap = new HashMap<>();
        for (TreeData ele : treeDataList) {
            nodeMap.put(ele.getCode(), ele);
        }

        List<TreeData> treeDataResultList = new ArrayList<>();
        // 遍历拼凑树形结构
        for (Map.Entry<String, TreeData> entry : nodeMap.entrySet()) {
            TreeData nodeData = entry.getValue();

            // 根数据
            if (Objects.isNull(nodeData.getPCode()) || "".equals(nodeData.getPCode())) {
                treeDataResultList.add(nodeData);
            } else {
                TreeData parentNodeData = nodeMap.get(nodeData.getPCode());
                if (Objects.nonNull(parentNodeData)) {
                    parentNodeData.getChildren()
                            .add(nodeData);
                    parentNodeData.getChildren()
                            .sort(TreeData::compareTo);
                }
            }
        }
        Collections.sort(treeDataResultList);

        return treeDataResultList;
    }
}
