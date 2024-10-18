package basic;

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

        // 1 规则检验
        // 返回的 result 就是我们校验的结果，当为 true 的时候，表示校验通过，当为 false 的时候，则表示校验不通过
        check("a1b2c3d", "a1b2c");
        check("a1b2c3d", "a1b2c3d");
        check("a1b2c3d", "a1b2c3d4e");

        // \d 表示一个数字
        // aaa\d：表示验证的字符串后面必须以 aaa 开头，且以一个数字结尾
        check("aaa\\d", "aaaa1");
        check("aaa\\d", "aaa1");
        check("aaa\\d", "aaa5");
        check("aaa\\d", "aab9");
        check("aaa\\d", "aaa");

        // aaa\dbbb：aaa和bbb中间有一个数字
        check("aaa\\dbbb", "aaa1bbb");
        check("aaa\\dbbb", "aaa1bb");
        check("aaa\\dbbb", "aa1bbb");

        // aaa\d\d：aaa后面跟2个数字 等价 aaa\d{2}
        check("aaa\\d\\d", "aaa1");
        check("aaa\\d\\d", "aaa11");
        check("aaa\\d\\d", "aaa111");
        check("aaa\\d{2}", "aaa11");

        // \D 表示一个非数字，它和上面 \d 的意思恰好相反
        // \D\D\D：则表示一个长度为3，不包含数字的字符串 等价 \D{3}
        check("\\D\\D\\D", "aa");
        check("\\D\\D\\D", "abc");
        check("\\D\\D\\D", "a+b");
        check("\\D\\D\\D", "a1b");
        check("\\D\\D\\D", "abcd");
        check("\\D{3}", "abc");
        check("\\D{3}", "a+b");

        // 111\D222：则表示111和222中间，必须包含一个非数字
        check("111\\D222", "1110222");
        check("111\\D222", "111a222");
        check("111\\D222", "111+222");
        check("111\\D222", "111*222");
        check("111\\D222", "111222");

        // \w 表示一个字母（大小写均可）、数字，或下划线
        // 12\w45：则表示12和45中间必须是一个字母，数字，或下划线
        check("12\\w45", "12345");
        check("12\\w45", "12A45");
        check("12\\w45", "12b45");
        check("12\\w45", "12_45");
        check("12\\w45", "12+45");
        check("12\\w45", "1245");

        // \W 与 \w 相反，表示这个位置的字符既不是字母、数字，也不是下划线；也就是特殊符号（除下划线），或者空格等满足
        // 12\W45：则表示12和45中间是一个非字母，非数字，或非下划线
        check("12\\W45", "12345");
        check("12\\W45", "12A45");
        check("12\\W45", "12b45");
        check("12\\W45", "12_45");
        check("12\\W45", "12+45");
        check("12\\W45", "12 45");

        // \s 表示匹配一个看不见的符号，即空格或制表符（Tab键）
        // 88\s99：则表示88和99中间须是一个空格或制表符
        check("88\\s99", "8899");
        check("88\\s99", "88+99");
        check("88\\s99", "88 99");
        check("88\\s99", "88999");
        check("88\\s99", "88s99");

        // \S 与 \s 相反，表示一个可以看得见的符号
        check("88\\S99", "8899");
        check("88\\S99", "88+99");
        check("88\\S99", "88 99");
        check("88\\S99", "88999");
        check("88\\S99", "88s99");

        // . (小数点) 则表示“\n”和"\r"之外的任何单个字符
        // .... ：则表示任意四个字符 等同 .{4}
        check("....", "as2d");
        check("....", "w ss");
        check("....", "12345");
        check(".{4}", "as2d");
        check(".{4}", "w ss");
        check(".{4}", "12345");

        // | (竖线) 则表示或的关系，表示检测的字符串须满足其中一个时，才符合条件
        // aa|bb|cc：则表示输入的字符串须是 aa，或 bb，或 cc 其中的一个
        check("aa|bb|cc", "aa");
        check("aa|bb|cc", "bb");
        check("aa|bb|cc", "cc");
        check("aa|bb|cc", "dd");
        check("aa|bb|cc", "11");

        // 或者关系的前后还有其它字符时，需要用（）将他们包裹起来
        // xx(aa|bb|cc)yy：则表示输入的字符串须是 xx 开头，yy 结尾，且中间是 aa，或 bb，或 cc 其中的一个
        check("xx(aa|bb|cc)yy", "xxaayy");
        check("xx(aa|bb|cc)yy", "xxbbyy");
        check("xx(aa|bb|cc)yy", "xxccyy");
        check("xx(aa|bb|cc)yy", "xxzzyy");

        // [] 表示匹配其中任意一个字符
        // a[bcd]e：则表示 a 和 e 的中间须是 b，或 c，或 d 其中的一个
        check("a[bcd]e", "abe");
        check("a[bcd]e", "ace");
        check("a[bcd]e", "ade");
        check("a[bcd]e", "abc");
        check("a[bcd]e", "ae");

        // [^] 表示不与中括号里的任意字符匹配
        // a[^bcd]e：则表示 a 和 e 的中间除 b，c，d 这三个字符外，其他的字符都满足
        check("a[^bcd]e", "abe");
        check("a[^bcd]e", "ace");
        check("a[^bcd]e", "ade");
        check("a[^bcd]e", "a e");
        check("a[^bcd]e", "a+e");
        check("a[^bcd]e", "a3e");

        // [值1-值2] 则表示值1到值2中间的所有字符都满足（包括值1和值2）。常用该正则来表示大小写字母范围，数字范围
        // a[b-d]e：等同于 a[bcd]e，因为 b-d 其实就是b,c,d三个数
        check("a[b-d]e", "abe");
        check("a[b-d]e", "ace");
        check("a[b-d]e", "ade");
        check("a[b-d]e", "a e");
        check("a[b-d]e", "a+e");
        check("a[b-d]e", "a3e");

        check("a[bcd]e", "abe");
        check("a[bcd]e", "ace");
        check("a[bcd]e", "ade");
        check("a[bcd]e", "a e");
        check("a[bcd]e", "a+e");
        check("a[bcd]e", "a3e");

        // a[0-9]e：则表示a和e中间是一个数字，等同于 a\de（前面说过\d表示一个数字）
        check("a[0-9]e", "abe");
        check("a[0-9]e", "ace");
        check("a[0-9]e", "a0e");
        check("a[0-9]e", "a+e");
        check("a[0-9]e", "a3e");

        check("a\\de", "abe");
        check("a\\de", "ace");
        check("a\\de", "a0e");
        check("a\\de", "a+e");
        check("a\\de", "a3e");

        // [^值1-值2] 则表示除值1和值2之外的所有字符，都可以满足
        // a[^1-3]e：则表示a和e中间的字符，只要不是1，2，3，则都满足
        check("a[^1-3]e", "abe");
        check("a[^1-3]e", "a0e");
        check("a[^1-3]e", "a1e");
        check("a[^1-3]e", "a2e");
        check("a[^1-3]e", "a3e");
        check("a[^1-3]e", "a4e");
        check("a[^1-3]e", "a5e");

        // \num 这里的num指number，也就是数字，当\后面跟数字，表示匹配第几个括号中的结果
        // 现在有 abcd 字符串，当我们用小括号把 c 包裹起来后，然后在字符串后面写上 \1，即 ab(c)d\1，
        // 则这里的 \1 就指 c，因为 \1 表示第1个小括号中的结果
        // ab(c)d\1：等同于 abcdc
        check("ab(c)d\\1", "abcd");
        check("ab(c)d\\1", "abcda");
        check("ab(c)d\\1", "abcdb");
        check("ab(c)d\\1", "abcdc");
        check("ab(c)d\\1", "abcdd");

        // ab(c)(d)\1\2：等同于 abcdcd，也等同于 ab(cd)\1
        check("ab(c)(d)\\1\\2", "abcdc");
        check("ab(c)(d)\\1\\2", "abcdcd");
        check("ab(c)(d)\\1\\2", "abcdcda");

        check("ab(cd)\\1", "abcdc");
        check("ab(cd)\\1", "abcdcd");
        check("ab(cd)\\1", "abcdcda");

        // ? 表示匹配前面的子表达式零次或一次
        // abc?de: 表示可匹配的字符串为 abde (匹配 0 次c) 或 abcde (匹配 1 次c)
        check("abc?de", "abde");
        check("abc?de", "abcde");
        check("abc?de", "abccde");

        // + 匹配前面的子表达式一次或多次 (次数 >= 1，即至少1次）
        // abc+de：ab 和 de 之前至少有一个 c
        check("abc+de", "abde");
        check("abc+de", "abcde");
        check("abc+de", "abccde");
        check("abc+de", "abcccde");

        // {n} 这里的 n 是一个非负整数。匹配确定的前面的子表达式 n 次
        //  abc{3}de：表示 ab 和 de 之间有3个c
        check("abc{3}de", "abde");
        check("abc{3}de", "abcde");
        check("abc{3}de", "abccde");
        check("abc{3}de", "abcccde");
        check("abc{3}de", "abccccde");

        // ab(xx|yy){3}de：表示 ab 和 de 之间有 xx 或 yy 的个数， 一起合计为3个
        check("ab(xx|yy){3}de", "abxxxxxxde");
        check("ab(xx|yy){3}de", "abyyyyyyde");
        check("ab(xx|yy){3}de", "abxxyyxxde");
        check("ab(xx|yy){3}de", "abyyxxyyde");
        check("ab(xx|yy){3}de", "abxyxyxyde");

        // {n,m} m和n均为非负整数，其中 n<=m。最少匹配 n 次且最多匹配 m 次
        // abc{2,3}de：表示 ab 和 de 之间有 2 到 3 个 c
        check("abc{2,3}de", "abde");
        check("abc{2,3}de", "abcde");
        check("abc{2,3}de", "abccde");
        check("abc{2,3}de", "abcccde");
        check("abc{2,3}de", "abccccde");

        // * 表示匹配前面的子表达式任意次
        // abc*de：表示 ab 和 de 之间有任意个数（包括0）c
        check("abc*de", "abde");
        check("abc*de", "abcde");
        check("abc*de", "abccde");
        check("abc*de", "abcccde");
        check("abc*de", "abccccccccccccccccccccccccccccccde");
    }

    private static void check(String regex, String input) {
        System.out.println(input + ":" + Pattern.matches(regex, input));
    }
}
