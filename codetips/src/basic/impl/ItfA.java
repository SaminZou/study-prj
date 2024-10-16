package basic.impl;

public interface ItfA {

    default void print() {
        System.out.println("ItfA");
    }
}