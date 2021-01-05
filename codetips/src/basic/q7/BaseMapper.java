package basic.q7;

import basic.q7.annotaion.Column;
import basic.q7.annotaion.Table;
import basic.q7.demo.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseMapper {

    private final Map<String, User> database = new HashMap<>();

    private String tableName;
    private List<ColumnNode> columnDetails;

    public User insert(User user) throws Exception {
        columnDetails = new ArrayList<>();

        // 开始解析注解
        // 1. 获取 Class
        Class clazz = user.getClass();

        // 2. 判断关键注解是否存在
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new Exception("@Table 未注解");
        }

        // 3. 获取参数值
        Table t = (Table) clazz.getAnnotation(Table.class);
        this.tableName = t.tableName();

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
