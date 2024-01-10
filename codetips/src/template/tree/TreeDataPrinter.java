package template.tree;

import java.util.List;

public class TreeDataPrinter {

    public static void printBeautifulTreeData(List<TreeData> treeDataList) {
        for (TreeData treeData : treeDataList) {
            printTreeData(treeData, 0);
        }
    }

    private static void printTreeData(TreeData treeData, int level) {
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < level; i++) {
            // 每个级别两个空格和一个竖线
            prefix.append("  |");
        }

        System.out.println(prefix + "- " + treeData.getName());
        // System.out.println(prefix + " Code: " + treeData.getCode());
        // System.out.println(prefix + " PCode: " + treeData.getPCode());

        List<TreeData> subTreeDataList = treeData.getChildren();
        if (subTreeDataList != null && !subTreeDataList.isEmpty()) {
            for (TreeData subTreeData : subTreeDataList) {
                printTreeData(subTreeData, level + 1);
            }
        }
    }
}
