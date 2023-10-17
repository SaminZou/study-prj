package structural.flyweight;

/**
 * 这是一棵树
 * <p>
 * Description: 这是一颗树
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-10-17
 */
public class Tree {

    public int x;
    public int y;
    public TreeType treeType;

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void show() {
        System.out.printf("此树品种为：%s，坐标为（%s,%s）%n", this.treeType.getName(), x, y);
    }
}
