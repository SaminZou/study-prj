package structural.composite;

/**
 * 文件抽象类
 * <p>
 * Description: 文件抽象类
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2020-09-19
 */
public abstract class File {

    String name;

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void display();
}
