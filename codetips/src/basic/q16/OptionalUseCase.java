package basic.q16;

import java.util.Map;
import java.util.Optional;

/**
 * Optional用例
 *
 * <p>get() 获取 value，如果是 null 则报 NoSuchElementException，使用之前一定要先调用 isPresent() 判断是否为空
 *
 * <p>isPresent() 判断 value 是否存在
 *
 * <p>orElse(T other) 如果 value 存在（不为 null ）则返回，否则返回 other
 *
 * <p>orElseGet(Supplier<? extends T> other) 如果value存在（不为null）则返回，否则调用other以返回值
 *
 * <p>
 *
 * @author samin
 * @date 2021-03-07
 */
public class OptionalUseCase {

    public static void main(String[] args) {
        // 不建议做的事情
        // 1. 不能被序列化，所以不能用于字段声明
        // 2. 作为入参
        // 3. 不建议在使用isPresent后接着使用get，那将和用 == 判断无异，不能体现 Optional 的优越性，反而麻烦

        // 最佳实践
        // 1. 建议使用于函数的返回值，提醒调用者对null的处理，也使调用者也变得优雅起来
        // 2. 建议使用于连续调用，减少if(obj != null)的判断，也不失可读性，可谓优雅

        /******************************************************************************************/

        // 我们不知道是否为 null
        Map<String, Map<String, Object>> map = null;

        // 不使用 Optional
        Object ret1 = null;

        if (map != null) {
            Map<String, Object> tmp = map.get("k1");
            if (tmp != null) {
                ret1 = tmp.get("kk1");
            }
        }
        if (ret1 == null) {
            ret1 = new Object();
        }

        // 使用 Optional
        Object ret2 =
                Optional.ofNullable(map) // Optional<Map<String, Map<String, Object>>>
                        .map(kkv -> kkv.get("k2")) // Optional<Map<String, Object>>
                        .map(kv -> kv.get("kk2")) // Optional<Object>
                        .orElseGet(Object::new); // 或者 orElse(new Object())
    }
}
