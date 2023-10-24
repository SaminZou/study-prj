package structural.composite.leaf;

import structural.composite.component.File;

public class TextFile extends File {

    public TextFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("    这是文本文件，文件名：" + super.getName());
    }
}
