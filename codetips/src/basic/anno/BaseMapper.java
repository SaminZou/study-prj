package basic.anno;

import basic.anno.annotaion.Column;
import basic.anno.annotaion.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础操作方法
 *
 * @author samin
 * @date 2021-01-10
 */
public class BaseMapper {

    private final Map<String, User> database = new HashMap<>();

    public User insert(User user) throws Exception {
        List<ColumnNode> columnDetails = new ArrayList<>();

        // 开始解析注解
        // 1. 获取 Class
        Class<? extends User> clazz = user.getClass();

        // 2. 判断关键注解是否存在
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new Exception("@Table 未注解");
        }

        // 3. 获取参数值
        Table t = clazz.getAnnotation(Table.class);
        String tableName = t.tableName();

        Field[] fields = clazz.getDeclaredFields();
        for (Field ele : fields) {
            // Column 注解处理
            Column c = ele.getAnnotation(Column.class);

            String value = (String) ele.get(user);

            if ("".equals(value) && c.require()) {
                throw new Exception(c.column() + "必填");
            }

            if (!"".equals(value)) {
                columnDetails.add(new ColumnNode(c.column(), value));
            }
        }

        // 模拟业务处理
        System.out.println("表名: " + tableName);
        for (ColumnNode ele : columnDetails) {
            System.out.println(ele.columnName + " : " + ele.columnValue);
        }
        System.out.println("------插入表数据成功------");
        System.out.println();

        database.put(user.id, user);

        return user;
    }

    public User query(String id) {
        return database.get(id);
    }

    public void print() {
        System.out.println("------开始打印数据库------");
        for (String ele : database.keySet()) {
            System.out.println(ele + ":" + database.get(ele));
        }
    }

    static class ColumnNode {

        public String columnName;
        public String columnValue;

        public ColumnNode(String columnName, String columnValue) {
            this.columnName = columnName;
            this.columnValue = columnValue;
        }
    }
}
