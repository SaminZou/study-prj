package algorithms;

/**
 * 替换空格
 *
 * @author samin
 * @date 2021-01-11
 */
public class ReplaceSpace {

    public static void main(String[] args) {
        // We%20are%20happy.
        System.out.println(new ReplaceSpace().replaceSpace("We are happy."));
    }

    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
}
