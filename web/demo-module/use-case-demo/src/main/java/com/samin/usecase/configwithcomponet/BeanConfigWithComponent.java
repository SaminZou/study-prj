package com.samin.usecase.configwithcomponet;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfigWithComponent {

    @Bean
    public D getD() {
        D d = new D();
        // 直接调用 @Bean 注解的方法方法 getC()，IDEA 编辑器会提示抛错，不影响编译运行
        d.setC(getC());
        return d;
    }

    @Bean
    public C getC() {
        return new C();
    }
}
