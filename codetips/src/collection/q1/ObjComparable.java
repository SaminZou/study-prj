package collection.q1;

/**
 * 对象实现排序方式
 *
 * @author samin
 * @date 2021-01-10
 */
public class ObjComparable implements Comparable<ObjComparable> {

    Integer weight;

    public ObjComparable(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(ObjComparable o) {
        System.out.println("使用Comparable接口实现方法排序");
        return -o.weight;
    }
}
