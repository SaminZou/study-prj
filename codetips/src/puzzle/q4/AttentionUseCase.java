package puzzle.q4;

import java.util.ArrayList;
import java.util.List;

public class AttentionUseCase {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        // 以下代码是正常的，java支持用花括号修饰代码块
        {
            {
                System.out.println("123");
            }
        }

        {
            System.out.println("123");
        }

        System.out.println("-------------------------------------------------");

        // switch 使用的时候，每个 case 下面的代码段都需要加上 break，不然会出现以下怪异现象
        // 高版本 JDK 已解决
        // 期望输出是输出 "0"，实际上是全部输出
        int i = 0;
        switch (i) {
            case 0:
                System.out.println("0");
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
        }

        System.out.println("-------------------------------------------------");

        // 注意逻辑运算的短路现象， "&&" 条件，假设前半部分是 false，那么后面的语句不会执行
        // 以下代码能正常输出 i1 等于 1
        int i1 = 0;
        System.out.println((i1++) == 1 && false);
        System.out.println("i1=" + i1);

        // 以下代码则因为短路现象输出 i1 等于 0
        i1 = 0;
        System.out.println(false && (i1++) == 1);
        System.out.println("i1=" + i1);

        System.out.println("-------------------------------------------------");

        // 小白操作列表会犯的错误
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        int length = list.size();
        // 为了能跑完全部代码，注释了以下代码段，学习的时候可以去掉注释观测到抛错
        //        for (int j = 0; j < length; j++) {
        //            if (list.get(j) >= 0) {
        //                list.remove(j);
        //            }
        //        }

        // 修改为迭代器遍历即可

        System.out.println("-------------------------------------------------");

        // 单字符转换成 int 是根据 ASCII 的规则进行转换，所以转换结果不是 1
        char symbol = '1';
        System.out.println("transform to int :" + (int) symbol);

        System.out.println("-------------------------------------------------");

        // 如果不小心把 += 写成了 =+ 结果会让你傻眼
        int temp = 1;
        temp = +2;
        System.out.println(temp);
    }
}
