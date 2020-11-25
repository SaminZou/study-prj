package com.samin.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxQueue {

    private Queue<Integer> queue;
    private Integer max;

    public MaxQueue() {
        queue = new LinkedList<>();
        max = -1;
    }

    public static void main(String[] args) {
        //        MaxQueue maxQueue = new MaxQueue();
        //        maxQueue.push_back(1);
        //        maxQueue.push_back(2);
        //        System.out.println(maxQueue.max_value());
        //        System.out.println(maxQueue.pop_front());
        //        System.out.println(maxQueue.max_value());

        //        MaxQueue maxQueue = new MaxQueue();
        //        System.out.println(maxQueue.pop_front());
        //        System.out.println(maxQueue.max_value());

        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(46);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
    }

    public int max_value() {
        return max;
    }

    public void push_back(int value) {
        queue.add(value);
        max = Math.max(max, value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            max = -1;
            return -1;
        }

        int ele = queue.poll();
        max = -1;
        for (Integer e : queue) {
            max = Math.max(max, e);
        }

        return ele;
    }
}
