package basic.q5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author samin
 * @date 2021-01-10
 */
public class PatternUseCase {

    public static void main(String[] args) {
        // Pattern(模式类) 和 Matcher(匹配器类)
        String str = "822085977@!qq.(com)";
        String regEx = "[`~!#$%^&*()+=|{}':;',\\[\\]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 输入需要被匹配的字符串
        Matcher matcher = pattern.matcher(str);
        // 输出所有结果
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
