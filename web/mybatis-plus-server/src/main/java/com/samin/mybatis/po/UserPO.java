package com.samin.mybatis.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("user")
public class UserPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 新增、更新要求值不能为空字符串或者为空
     */
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    @TableField("sex")
    private Integer sex;

    @TableField("mobile")
    private String mobile;

    /**
     * TableLogic 的作用：
     * <p> 删除操作 → 自动把字段更新成 delval
     * <p> 查询操作 → 自动拼接 where deleted = 0
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    /**
     * 忽略持久化
     */
    @TableField(exist = false)
    private String other;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
