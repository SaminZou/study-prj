package collection.q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * List和字符串之间的转换
 *
 * @author samin
 * @date 2021-05-18
 */
public class ListAndStringUseCase {

    /** string to list */
    public static List<String> stringToList(String text) {
        return Arrays.asList(text.split(","));
    }

    /** list to string */
    public static String listToString(List<String> list) {
        return list.toString().replace("[", "").replace("]", "");
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(listToString(list));

        String str = "1,2,3,4,5";
        System.out.println(stringToList(str));
    }
}
