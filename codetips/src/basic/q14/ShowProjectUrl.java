package basic.q14;

import java.io.File;
import java.net.URL;
import java.util.Objects;

/**
 * Java 路径相关
 *
 * @author samin
 * @date 2021-01-10
 */
public class ShowProjectUrl {

    public static <T> void showUrl(Class<T> t) throws Exception {
        File f = new File(Objects.requireNonNull(t.getResource("/")).getPath());
        System.out.println("获取类加载的根路径");
        System.out.println(f);
        System.out.println("-----------------------------------------");
        System.out.println();

        System.out.println("获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录");
        File f2 = new File(Objects.requireNonNull(t.getResource("")).getPath());
        System.out.println(f2);
        System.out.println("-----------------------------------------");
        System.out.println();

        System.out.println("获取项目路径");
        File directory = new File(""); // 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);
        System.out.println("-----------------------------------------");
        System.out.println();

        URL xmlpath = t.getClassLoader().getResource("");
        System.out.println(xmlpath);
        System.out.println("-----------------------------------------");
        System.out.println();

        System.out.println(System.getProperty("user.dir"));
        System.out.println("-----------------------------------------");
        System.out.println();

        System.out.println("获取所有的类路径，包括jar包的路径，获取当前工程路径");
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("-----------------------------------------");
    }

    public static void main(String[] args) throws Exception {
        ShowProjectUrl.showUrl(ShowProjectUrl.class);
    }
}
