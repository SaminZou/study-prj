package com.samin.usecase.nativetype;

import lombok.Data;

/**
 * 业务对象
 *
 * @author samin
 * @date 2023-07-06
 */
@Data
public class BizClass {

    private int priceNativeType;

    private Integer priceWrapperType;

    private boolean isExistNativeType;

    private Boolean isExistWrapperType;
}
