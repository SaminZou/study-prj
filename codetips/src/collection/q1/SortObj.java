package collection.q1;

import java.util.Objects;

/**
 * 对象实现排序方式
 *
 * @author samin
 * @date 2021-01-10
 */
public class SortObj implements Comparable<SortObj> {

    int weight;

    public SortObj(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(SortObj o) {
        // System.out.println("使用 Comparable 接口实现方法排序");
        // 实现升序
        return this.weight - o.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortObj sortObj = (SortObj) o;
        return Objects.equals(weight, sortObj.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public String toString() {
        return "SortObj{" + "weight=" + weight + '}' + "\n";
    }
}
