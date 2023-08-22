package basic.q1;

import java.util.Arrays;

/**
 * System 类用例
 *
 * @author samin
 * @date 2020-12-31
 */
public class SystemCallUseCase {

    public static void main(String[] args) {
        // $ java SystemCallUseCase foo
        System.out.println("Your first argument is:" + args[0]);

        // Arrays.copyOf() 底层调用的就是 System.arraycopy()

        // System.arraycopy() 入参分别是源数组，源数组开头，目的数组，目的数组开头，复制长度
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[3];
        int[] arr3 = new int[arr1.length + 1];
        // 模拟从 arr1 复制从 2 开头的 3 个数字到 arr2
        System.arraycopy(arr1, 1, arr2, 0, 3);
        System.out.println(Arrays.toString(arr2));
        // 模拟从 arr1 复制全部数字到 arr3
        System.arraycopy(arr1, 0, arr3, 0, arr1.length);
        arr3[arr3.length - 1] = 6;
        System.out.println(Arrays.toString(arr3));

        // identityHashCode() 获取对象的系统和内部地址相关的 hashCode()
        // 可用于判断是否是同一个实例对象
        // 同时可以验证 hashCode() 方法是否重写过
        // String 类的 hashCode() 是重写过的，所以两者的结果自然不一样
        String foo = "foo";
        int fooHashCode = System.identityHashCode(foo);
        System.out.println(fooHashCode);
        System.out.println(foo.hashCode());
    }
}
