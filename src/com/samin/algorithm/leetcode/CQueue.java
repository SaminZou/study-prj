package com.samin.algorithm.leetcode;

import java.util.Stack;

public class CQueue {

    Stack<Integer> datas;
    Stack<Integer> actionTemp;

    public CQueue() {
        datas = new Stack<>();
        actionTemp = new Stack<>();
    }

    public static void main(String[] args) {
        //        CQueue cQueue = new CQueue();
        //        cQueue.appendTail(3);
        //        System.out.println(cQueue.deleteHead());
        //        System.out.println(cQueue.deleteHead());

        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }

    public void appendTail(int value) {
        datas.push(value);
    }

    public int deleteHead() {
        int result = -1;
        if (!this.datas.empty()) {
            while (!datas.empty()) {
                actionTemp.push(datas.pop());
            }

            result = actionTemp.pop();

            while (!actionTemp.empty()) {
                datas.push(actionTemp.pop());
            }
        }

        return result;
    }
}
