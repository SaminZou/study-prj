package com.samin.redis.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Data
@Entity
@TypeDefs({@TypeDef(name = "string-array", typeClass = StringArrayType.class)})
@Table(name = "holiday")
public class Holiday {

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static Holiday getInstance() {
        Holiday ins = new Holiday();

        ins.setEnabled(true);
        ins.setRemark("");
        ins.setHolidayType(7);

        return ins;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Type(type = "string-array")
    // @Column(name = "holidays", columnDefinition = "varchar(255)[]")
    @Column(name = "holidays", columnDefinition = "text[]")
    @Comment("假期，格式：" + DATE_FORMAT)
    private String[] holidays;

    @Type(type = "string-array")
    @Comment("工作日，格式：" + DATE_FORMAT)
    @Column(name = "workdays", columnDefinition = "text[]")
    private String[] workdays;

    @Comment("假期类型: 0元旦，1春节，2清明节，3劳动节，4端午节，5中秋节，6国庆节，7其他")
    private Integer holidayType;

    @Comment("备注")
    private String remark;

    @Comment("是否生效")
    private Boolean enabled;
}
