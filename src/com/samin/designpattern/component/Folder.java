package com.samin.designpattern.component;

import java.util.ArrayList;
import java.util.List;

public class Folder extends File {

    private List<File> files;

    public Folder(String name) {
        super(name);
        files = new ArrayList<File>();
    }

    // 浏览文件夹中的文件
    public void display() {
        for (File file : files) {
            file.display();
        }
    }

    // 文件夹中添加文件
    public void add(File file) {
        files.add(file);
    }

    // 从文件夹中删除文件
    public void remove(File file) {
        files.remove(file);
    }
}
