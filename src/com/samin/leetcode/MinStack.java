package com.samin.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinStack {
    int[] arrs;
    int index;
    int min;

    public MinStack() {
        arrs = new int[20000];
        index = -1;
        min = -1;
    }

    public void push(int x) {
        index += 1;
        arrs[index] = x;

        if (index == 0) {
            min = x;
        } else {
            for (int i = 0; i < index + 1; i++) {
                if (arrs[i] < min) {
                    min = arrs[i];
                }
            }
        }
    }

    public void pop() {
        index--;
        if (index == -1) {
            min = -1;
        } else {
            min = arrs[0];
            if (index >= 0) {
                for (int i = index; i >= 0; i--) {
                    if (arrs[i] < min) {
                        min = arrs[i];
                    }
                }
            }
        }
    }

    public int top() {
        return arrs[index];
    }

    public int min() {
        return min;
    }

    public static void main(String[] args) {
//        MinStack minStack = new MinStack();
//        minStack.push(2);
//        minStack.push(0);
//        minStack.push(3);
//        minStack.push(0);
//        System.out.println(minStack.min());
//        minStack.pop();
//        System.out.println(minStack.min());
//        minStack.pop();
//        System.out.println(minStack.min());
//        minStack.pop();
//        System.out.println(minStack.min());
        // 0 0 0 2

//        MinStack minStack = new MinStack();
//        minStack.push(1);
//        minStack.push(2);
//        System.out.println(minStack.top());
//        System.out.println(minStack.min());
//        minStack.pop();
//        System.out.println(minStack.min());
//        System.out.println(minStack.top());
        // 2 1 1 1

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.min());
        // -3 0 -2
    }
}
