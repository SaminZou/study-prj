package basic.q7;

import basic.q7.demo.User;

/**
 * 演示主方法
 *
 * <p>模拟 ORM 操作
 *
 * @author samin
 * @date 2020-12-22
 */
public class AnnotationUseCase {

    public static void main(String[] args) throws Exception {
        BaseMapper baseMapper = new BaseMapper();

        // 新增
        User user1 = new User("1", "samin1", "samin1", "13800000001");
        User user2 = new User("2", "samin2", "samin2", "13800000002");
        User user3 = new User("3", "samin3", "samin3", "13800000003");

        String tempId = baseMapper.insert(user1).id;
        baseMapper.insert(user2);
        baseMapper.insert(user3);

        // 打印数据库
        baseMapper.print();
        System.out.println();

        // 查询
        System.out.println("查询数据结果：" + baseMapper.query(tempId));
    }
}
