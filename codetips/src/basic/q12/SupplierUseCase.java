package basic.q12;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Java8 Supplier 接口的使用
 *
 * @author samin
 * @date 2022-02-21
 */
public class SupplierUseCase {

    public static void main(String[] args) {
        Supplier<Integer> intGenerator = () -> new Random().nextInt();
        System.out.println(intGenerator.get());
    }
}
