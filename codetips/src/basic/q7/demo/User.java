package basic.q7.demo;

import basic.q7.annotaion.Column;
import basic.q7.annotaion.Table;

/**
 * 模拟映射实体 User
 *
 * @author samin
 * @date 2020-12-22
 */
@Table(tableName = "t_user")
public class User {

    @Column(column = "id")
    public String id;

    @Column(column = "user_name", require = true)
    public String userName;

    @Column(column = "nick_name")
    public String nickName;

    @Column(column = "phone", require = true)
    public String phone;

    public User(String id, String userName, String nickName, String phone) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", nickName='" + nickName + '\''
                + ", phone='" + phone + '\'' + '}';
    }
}
