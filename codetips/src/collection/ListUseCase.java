package collection;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 列表用例
 * <p>
 * Description: 列表用例
 * <p>
 * Created By: Samin Created Date: 2025-06-04
 */
public class ListUseCase {

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        list2.addAll(list1.stream()
                          .collect(Collectors.toList()));

        System.out.println(list2);

        // 会报错
        list2.addAll(null);
        System.out.println(list2);
    }
}
