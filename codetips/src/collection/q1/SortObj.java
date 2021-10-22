package collection.q1;

/**
 * 对象实现排序方式
 *
 * @author samin
 * @date 2021-01-10
 */
public class SortObj implements Comparable<SortObj> {

    Integer weight;

    public SortObj(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(SortObj o) {
        System.out.println("使用Comparable接口实现方法排序");
        return -o.weight;
    }

    @Override
    public String toString() {
        return "SortObj{" +
                "weight=" + weight +
                '}' + "\n";
    }
}
