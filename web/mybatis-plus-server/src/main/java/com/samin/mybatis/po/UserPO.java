package com.samin.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @TableField("sex")
    private Integer sex;

    @TableField("mobile")
    private String mobile;

    /**
     * 忽略持久化
     */
    @TableField(exist = false)
    private String other;

    @TableField("create_time")
    private String createTime;
}
