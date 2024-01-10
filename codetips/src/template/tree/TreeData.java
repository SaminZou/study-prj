package template.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeData implements Comparable<TreeData> {

    private String name;
    private String code;
    private String pCode;
    private Integer order;
    private List<TreeData> children = new ArrayList<>();

    public static TreeData getInstance(String name, String code, String pCode, Integer order) {
        TreeData ins = new TreeData();

        ins.setName(name);
        ins.setCode(code);
        ins.setPCode(pCode);
        ins.setOrder(order);

        return ins;
    }

    public static TreeData getInstance(TreeData treeData, List<TreeData> children) {
        TreeData ins = new TreeData();

        ins.setName(treeData.getName());
        ins.setCode(treeData.getCode());
        ins.setPCode(treeData.getPCode());
        ins.setOrder(treeData.getOrder());
        ins.setChildren(children);

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

    public String getPCode() {
        return pCode;
    }

    public void setPCode(String pCode) {
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

    public Integer getOrder() {
        return order;
    }

    @Override
    public int compareTo(TreeData tree) {
        //升序
        return this.order - tree.order;
    }

    @Override
    public String toString() {
        return "{" + "name='" + name + '\'' + ", code='" + code + '\'' + ", pCode='" + pCode + '\'' + ", children=" + children + '}' + "\n";
    }
}
