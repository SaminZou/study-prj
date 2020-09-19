package com.samin.project.iterator;

public interface TelevisionMenu {
    void addItem(int channe, String name, String description);

    Iterator createIrerator();
}
