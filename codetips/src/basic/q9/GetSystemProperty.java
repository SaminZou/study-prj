package basic.q9;

import java.util.Enumeration;
import java.util.Properties;

/**
 * 打印系统参数
 *
 * <p> java.library.path
 * @author samin
 * @date 2023-03-21
 */
public class GetSystemProperty {

    public static void main(String args[]) {
        if (args.length == 0) {
            Properties p = System.getProperties();
            Enumeration keys = p.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String value = (String) p.get(key);
                System.out.println(key + " : " + value);
            }
        } else {
            for (String key : args) {
                System.out.println(System.getProperty(key));
            }
        }
    }
}
