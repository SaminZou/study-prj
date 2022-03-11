package algorithms.stack;

/**
 * 三合一
 *
 * @author samin
 * @date 2021-01-12
 */
public class TripleInOne {

    /** 存储栈的数组 */
    private final int[][] tripleStack;
    /** 栈长度 */
    private final int stackSize;
    /** 栈1的指针 */
    private int index0 = -1;
    /** 栈2的指针 */
    private int index1 = -1;
    /** 栈的指针 */
    private int index2 = -1;

    /** 初始化方法，一定要执行 */
    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        this.tripleStack = new int[3][stackSize];
    }

    /** 入栈 */
    public void push(int stackNum, int value) {
        switch (stackNum) {
            case 0:
                if (index0 + 1 != stackSize) {
                    tripleStack[0][++index0] = value;
                }
                break;
            case 1:
                if (index1 + 1 != stackSize) {
                    tripleStack[1][++index1] = value;
                }
                break;
            case 2:
                if (index2 + 1 != stackSize) {
                    tripleStack[2][++index2] = value;
                }
                break;
            default:
        }
    }

    /** 出栈 */
    public int pop(int stackNum) {
        switch (stackNum) {
            case 0:
                if (index0 != -1) {
                    return tripleStack[0][index0--];
                }
                break;
            case 1:
                if (index1 != -1) {
                    return tripleStack[1][index1--];
                }
                break;
            case 2:
                if (index2 != -1) {
                    return tripleStack[2][index2--];
                }
                break;
            default:
        }

        return -1;
    }

    /** 查看栈顶元素 */
    public int peek(int stackNum) {
        switch (stackNum) {
            case 0:
                if (index0 != -1) {
                    return tripleStack[0][index0];
                }
                break;
            case 1:
                if (index1 != -1) {
                    return tripleStack[1][index1];
                }
                break;
            case 2:
                if (index2 != -1) {
                    return tripleStack[2][index2];
                }
                break;
            default:
        }

        return -1;
    }

    /** 是否为空栈 */
    public boolean isEmpty(int stackNum) {
        switch (stackNum) {
            case 0:
                return index0 == -1;
            case 1:
                return index1 == -1;
            case 2:
                return index2 == -1;
            default:
                return false;
        }
    }
}
