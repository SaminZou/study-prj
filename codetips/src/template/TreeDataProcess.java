package template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 树状数据
 *
 * @author samin
 * @date 2022-07-25
 */
public class TreeDataProcess {

    public static void main(String[] args) {
        System.out.println(getMenuTreeList(mockData()));
    }

    static private List<TreeData> getMenuTreeList(List<TreeData> treeDataList) {
        // 生成 Map 数据集合
        Map<String, TreeData> nodeMap = new HashMap<>();
        for (TreeData ele : treeDataList) {
            ele.setChildren(new ArrayList<>());

            nodeMap.put(ele.getCode(), ele);
        }

        List<TreeData> treeDataResultList = new ArrayList<>();
        // 遍历拼凑树形结构
        for (Map.Entry<String, TreeData> entry : nodeMap.entrySet()) {
            TreeData nodeData = entry.getValue();

            // 根数据
            if (Objects.isNull(nodeData.getpCode()) || "".equals(nodeData.getpCode())) {
                treeDataResultList.add(nodeData);
            } else {
                TreeData parentNodeData = nodeMap.get(nodeData.getpCode());
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


    static class TreeData implements Comparable<TreeData> {

        private String name;
        private String code;
        private String pCode;
        private Integer order;
        private List<TreeData> children;

        public static TreeData getInstance(String name, String code, String pCode, Integer order) {
            TreeData ins = new TreeData();

            ins.setName(name);
            ins.setCode(code);
            ins.setpCode(pCode);
            ins.setOrder(order);

            return ins;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getpCode() {
            return pCode;
        }

        public void setpCode(String pCode) {
            this.pCode = pCode;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public List<TreeData> getChildren() {
            return children;
        }

        public void setChildren(List<TreeData> children) {
            this.children = children;
        }

        @Override
        public int compareTo(TreeData tree) {
            //升序
            return this.order - tree.order;
        }

        @Override
        public String toString() {
            return "TreeData{" + "name='" + name + '\'' + ", code='" + code + '\'' + ", pCode='" + pCode + '\''
                    + ", children=" + children + '}';
        }
    }

    private static List<TreeData> mockData() {
        List<TreeData> list = new ArrayList<>();

        list.add(TreeData.getInstance("d", "d", null, 2));
        list.add(TreeData.getInstance("d1", "d1", "d", 1));
        list.add(TreeData.getInstance("d3", "d3", "d", 3));
        list.add(TreeData.getInstance("d31", "d31", "d3", 1));
        list.add(TreeData.getInstance("d4", "d4", "d", 4));
        list.add(TreeData.getInstance("d2", "d2", "d", 2));

        list.add(TreeData.getInstance("a", "a", null, 1));

        list.add(TreeData.getInstance("c", "c", "", 3));
        list.add(TreeData.getInstance("c1", "c1", "c", 1));
        list.add(TreeData.getInstance("c2", "c2", "c", 2));
        list.add(TreeData.getInstance("c11", "c11", "c1", 1));
        list.add(TreeData.getInstance("c12", "c12", "c1", 3));
        list.add(TreeData.getInstance("c13", "c13", "c1", 2));
        list.add(TreeData.getInstance("c21", "c21", "c2", 1));
        list.add(TreeData.getInstance("c22", "c22", "c2", 2));
        list.add(TreeData.getInstance("c23", "c23", "c2", 3));

        list.add(TreeData.getInstance("b", "b", "", 4));
        list.add(TreeData.getInstance("b1", "b1", "b", 1));
        list.add(TreeData.getInstance("b2", "b2", "b", 2));

        return list;
    }
}
