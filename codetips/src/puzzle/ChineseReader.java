package puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Q：读取中文字符乱码问题
 * <p>
 * 认知 IO 和字符串的关系
 *
 * @author samin
 * @date 2021-01-10
 */
public class ChineseReader {

    public static void main(String[] args) throws Exception {
        // 获取文件相对路径
        String uri = Objects.requireNonNull(Thread.currentThread()
                                                  .getContextClassLoader()
                                                  .getResource("./"))
                            .getPath();
        File file = new File(uri + "/puzzle/chinese.txt");

        try (InputStream inputStream = new FileInputStream(file)) {
            System.out.println("方式 1：未完全读取的情况，乱码");
            // 未完全读取的情况，乱码
            int length2 = 2;
            byte[] b2 = new byte[length2];
            while (inputStream.read(b2, 0, length2) != -1) {
                System.out.println(new String(b2, 0, length2));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------------------------------------------------");
        System.out.println();

        // 本解决方案还是有问题，不同编码的中文所占字节数不一样
        try (InputStream inputStream2 = new FileInputStream(file)) {
            System.out.println("方式 2：一个 UTF-8 占 3 个字节，修改每次 3 字节读取，即可完整读出每个中文文字");
            // 一个 UTF-8 占3个字节，以下代码段可以输出完整循环输出结果
            int length = 3;
            byte[] b = new byte[length];
            while (inputStream2.read(b, 0, length) != -1) {
                System.out.println(new String(b, 0, length));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------------------------------------------------");
        System.out.println();

        // 解决方案，按字符流读取
        try (InputStream inputStream3 = new FileInputStream(file)) {
            InputStreamReader io = new InputStreamReader(inputStream3);
            System.out.println("方式 3：按字符流读取");
            int ch;
            while ((ch = io.read()) != -1) {
                System.out.println((char) ch);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------------------------------------------------");
        System.out.println();

        // BufferReader 和 InputStreamReader 的区别在于是否一行行读出，前者可以按行读
        try (InputStream inputStream4 = new FileInputStream(file)) {
            InputStreamReader io2 = new InputStreamReader(inputStream4);
            BufferedReader br = new BufferedReader(io2);
            System.out.println("方式 4：按行读");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------------------------------------------------");
        System.out.println();
    }
}
