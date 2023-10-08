package structural.composite;

/**
 * 组合模式
 *
 * <p> 对应有 Component、Leaf（叶子节点）、Composite（非叶子节点） 来构成组合模式
 *
 * <p> 本案例中，File 对应 Component
 * <p> 本案例中，Folder 对应 Composite
 * <p> 本案例中，ImageFile、TextFile、VideoFile 对应 Leaf
 *
 * @author samin
 * @date 2021-01-05
 */
public class Client {

    public static void main(String[] args) {
        // 总文件夹
        Folder zwjj = new Folder("总文件夹");

        // 向总文件夹中放入三个文件：1.txt、2.jpg、1文件夹
        TextFile aText = new TextFile("a.txt");
        ImageFile bImager = new ImageFile("b.jpg");
        Folder cFolder = new Folder("C文件夹");

        zwjj.add(aText);
        zwjj.add(bImager);
        zwjj.add(cFolder);

        // 向C文件夹中添加文件：c_1.txt、c_1.rmvb、c_1.jpg
        TextFile cText = new TextFile("c_1.txt");
        ImageFile cImage = new ImageFile("c_1.jpg");
        VideoFile cVideo = new VideoFile("c_1.rmvb");

        cFolder.add(cText);
        cFolder.add(cImage);
        cFolder.add(cVideo);

        // 遍历C文件夹
        cFolder.display();
        // 将c_1.txt删除
        cFolder.remove(cText);
        System.out.println("-----------------------");
        cFolder.display();
    }
}
