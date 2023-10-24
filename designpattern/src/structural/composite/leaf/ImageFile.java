package structural.composite.leaf;

import structural.composite.component.File;

public class ImageFile extends File {

    public ImageFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("    这是图像文件，文件名：" + super.getName());
    }
}
