package com.samin.auth.vo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.samin.auth.vo.resp.UserResp;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户 Excel 实体类", description = "用户 Excel 实体类")
public class UserExcel {

    public static UserExcel getInstance(UserResp resp) {
        UserExcel ins = new UserExcel();

        ins.setId(String.valueOf(resp.getId()));
        ins.setMobile(resp.getMobile());

        return ins;
    }

    @ExcelProperty(index = 0, value = {"主键"})
    @ColumnWidth(20)
    private String id;

    @ExcelProperty(index = 1, value = {"用户手机号"})
    @ColumnWidth(20)
    private String mobile;
}
