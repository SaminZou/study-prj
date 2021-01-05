package algorithms;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author samin
 * @date 2020-12-23
 */
public class CQueue {

    Stack<Integer> datas;
    Stack<Integer> actionTemp;

    public CQueue() {
        datas = new Stack<>();
        actionTemp = new Stack<>();
    }

    public static void main(String[] args) {
        // CQueue cQueue = new CQueue();
        // cQueue.appendTail(3);
        // System.out.println(cQueue.deleteHead());
        // System.out.println(cQueue.deleteHead());

        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }

    /**
     * 队尾加入队列
     *
     * @param value 需要增加的数值
     */
    public void appendTail(int value) {
        datas.push(value);
    }

    /**
     * 队头删除数值并返回
     *
     * @return 队列头部数值
     */
    public int deleteHead() {
        int result = -1;

        if (!this.datas.empty()) {
            // 暂存栈模拟队列的顺序
            while (!datas.empty()) {
                actionTemp.push(datas.pop());
            }

            // 输出队头数值，其实是栈尾
            result = actionTemp.pop();

            // 还原堆栈
            while (!actionTemp.empty()) {
                datas.push(actionTemp.pop());
            }
        }

        return result;
    }
}
