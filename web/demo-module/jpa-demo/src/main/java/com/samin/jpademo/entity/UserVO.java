package com.samin.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserVO {

    private Integer id;

    @Size(min = 1, max = 10, message = "字符串长度在 1-10")
    private String name;

    @NotNull(message = "性别不能为空")
    @Range(min = 1, max = 1, message = "范围错误")
    private Integer sex;

    // 手机号码必填并校验格式
    @Pattern(regexp = "^\\s{0}$|^((1[3-9][0-9])\\d{8})$", message = "手机号格式错误")
    // 手机号码非必填并校验格式
    // @Pattern(regexp = "^((1[3-9][0-9])\\d{8})$", message = "手机号格式错误")
    private String mobile;

    @Email(message = "email 地址错误")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "只能是将来时间")
    // @Past(message = "只能是过去时间")
    private LocalDate date;

    @JsonIgnore
    private String foo;
}
