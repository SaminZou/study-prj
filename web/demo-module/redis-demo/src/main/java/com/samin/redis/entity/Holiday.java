package com.samin.redis.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@Entity
@TypeDefs({@TypeDef(name = "string-array", typeClass = StringArrayType.class)})
@Table(name = "holiday")
public class Holiday {

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
    @Comment("假期，yyyy-MM-dd")
    private String[] holidays;

    @Type(type = "string-array")
    @Comment("工作日，yyyy-MM-dd")
    @Column(name = "weekdays", columnDefinition = "text[]")
    private String[] weekdays;

    @Comment("假期类型: 0元旦，1春节，2清明节，3劳动节，4端午节，5中秋节，6国庆节，7其他")
    private Integer holidayType;

    @Comment("备注")
    private String remark;

    @Comment("是否生效")
    private Boolean enabled;
}
