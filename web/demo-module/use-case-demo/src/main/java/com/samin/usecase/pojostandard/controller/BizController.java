package com.samin.usecase.pojostandard.controller;

import com.samin.usecase.beanconfig.configwithcomponet.B;
import com.samin.usecase.pojostandard.bo.BizClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 业务对象必须使用包装类的原因
 *
 * @author samin
 * @date 2023-07-06
 */
@Slf4j
@RestController
public class BizController {

    @GetMapping("/biz")
    public String biz(){
        BizClass bizClass = new BizClass();
        // 场景 1：存在价格为 0 的商品，进行告警上报运营人员，实际商品不存在
        if (bizClass.getPriceNativeType() == 0) {
            log.info("记录商品并进行告警，实际上商品不存在，但是原生类型默认值为 0，误触发");
        }

        if (Objects.nonNull(bizClass.getPriceWrapperType()) && bizClass.getPriceWrapperType() == 0) {
            // 真正符合条件的报警
            log.info("记录商品并进行告警");
        }

        // 场景 2：是否存在某对象，不存在则进行业务处理
        if (bizClass.isExistNativeType()) {
            log.info("业务处理中，实际上不需要处理，配置不存在，但是原生类型的默认值为 false，误触发");
        }

        if (Objects.nonNull(bizClass.getPriceWrapperType()) && bizClass.getPriceWrapperType() == 0) {
            // 真正符合条件的业务操作
            log.info("进行业务操作");
        }

        // 并且对库有 null 的可能，业务对象的基本类型解析可能会导致空指针错误

        return "success";
    }
}
