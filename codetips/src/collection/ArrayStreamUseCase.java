package collection;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayStreamUseCase {

    public static void main(String[] args) {
        String[] strs = new String[]{"a", "b"};
        strs = Stream.concat(Arrays.stream(strs), Stream.of("c"))
                .toArray(String[]::new);
        System.out.println(Arrays.toString(strs));
    }
}
