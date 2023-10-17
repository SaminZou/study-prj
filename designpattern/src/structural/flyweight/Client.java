package structural.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * 享元模式
 * <p>
 * Description: 享元模式
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-10-17
 */
public class Client {

    public static void main(String[] args) {
        List<Tree> forest = new ArrayList<>();
        forest.add(new Tree(1, 2, FlyweightFactory.getTreeType("松树")));
        forest.add(new Tree(2, 3, FlyweightFactory.getTreeType("樟树")));
        forest.add(new Tree(1, 4, FlyweightFactory.getTreeType("迎客松")));
        forest.add(new Tree(1, 5, FlyweightFactory.getTreeType("松树")));
        forest.add(new Tree(1, 6, FlyweightFactory.getTreeType("樟树")));
        forest.add(new Tree(9, 8, FlyweightFactory.getTreeType("松树")));

        forest.forEach(Tree::show);

        System.out.printf("森林中一共有 %s 种树", FlyweightFactory.getSum());
    }
}
